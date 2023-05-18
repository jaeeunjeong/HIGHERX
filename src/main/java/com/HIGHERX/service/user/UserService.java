package com.HIGHERX.service.user;

import com.HIGHERX.controller.request.user.UserJoinRequest;
import com.HIGHERX.controller.response.user.UserResponse;
import com.HIGHERX.dto.user.UserDto;
import com.HIGHERX.entity.user.UserEntity;
import com.HIGHERX.exception.ApplicationException;
import com.HIGHERX.exception.ErrorCode;
import com.HIGHERX.repository.user.UserRepository;
import com.HIGHERX.util.CrnVerifyUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(UserJoinRequest req) {

        userRepository.findByAccount(req.getAccount()).ifPresent(it -> {
            throw new ApplicationException(ErrorCode.DUPLICATED_USER_ACCOUNT, String.format("%s is Duplicated", req.getAccount()));
        });

        userRepository.findByNickname(req.getNickname()).ifPresent(it -> {
            throw new ApplicationException(ErrorCode.DUPLICATED_USER_NICKNAME, String.format("%s is Duplicated", req.getNickname()));
        });

        if (CrnVerifyUtils.crnVerify(req.getCrn()))
            throw new ApplicationException(ErrorCode.INVALID_CRN, String.format("%s is invalid", req.getCrn()));

        userRepository.save(UserEntity.of(req.getAccount(), passwordEncoder.encode(req.getPassword()), req.getNickname(), req.getPhone(), req.getCrn()));
    }

    public UserResponse readUser(String account) {
        // TODO Entity를 바로 리턴해도 되는지 고민해보기
        return UserResponse.fromDto(UserDto.fromEntity(getUser(account)));
    }

    @Transactional
    public void deleteUser(String account) {
        UserEntity userEntity = getUser(account);

        userEntity.deleteUser();

        userRepository.save(userEntity);
    }

    public UserEntity getUser(String account) {
        return userRepository.findByAccount(account).orElseThrow(() ->
            new ApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not founded", account)));
    }
}
