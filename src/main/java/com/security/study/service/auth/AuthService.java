package com.security.study.service.auth;

import com.security.study.controller.response.auth.AuthLoginResponse;
import com.security.study.controller.response.auth.AuthTokenResponse;
import com.security.study.entity.user.UserEntity;
import com.security.study.exception.ApplicationException;
import com.security.study.exception.ErrorCode;
import com.security.study.repository.user.UserRepository;
import com.security.study.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.key.access}")
    private String secretAccessKey;

    @Value("${jwt.token.key.refresh}")
    private String secretRefreshKey;

    @Value("${jwt.token.expired-time-ms.access}")
    private Long expiredAccessTimeMs;

    @Value("${jwt.token.expired-time-ms.refresh}")
    private Long expiredRefreshTimeMs;

    public AuthLoginResponse login(String account, String password){
        UserEntity userEntity = userRepository.findByAccount(account).orElseThrow(()->
            new ApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not founded", account)));

        if(encoder.matches(password, userEntity.getPassword()))
            throw new ApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not founded", account));

        String accessToken = JwtTokenUtils.generateToken(account, secretAccessKey, expiredAccessTimeMs);
        String refreshToken = JwtTokenUtils.generateToken(account, secretRefreshKey, expiredRefreshTimeMs);

        return new AuthLoginResponse(accessToken,refreshToken);
    }

    public void logout(String account){
        // TODO 클라이언트에서 token을 제거하여 로그아웃 합니다. 좀더 생각해보기

        JwtTokenUtils.expiredToken(account);
    }

    public AuthTokenResponse getToken(String token){
        if(JwtTokenUtils.isExpired(token, secretRefreshKey))
            throw new ApplicationException(ErrorCode.INVALID_TOKEN, String.format("token is invalidate"));

        String account = JwtTokenUtils.getAccount(token, secretRefreshKey);
        String newToken = JwtTokenUtils.generateToken(account, secretAccessKey, expiredAccessTimeMs);

        // TODO accessToken이 갱신되었으니, refreshToken도 갱신하면 좋을 것 같음 -> 사용성 고려

        return new AuthTokenResponse(newToken);
    }

}
