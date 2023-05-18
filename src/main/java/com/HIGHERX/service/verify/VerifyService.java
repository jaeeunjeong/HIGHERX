package com.HIGHERX.service.verify;

import com.HIGHERX.repository.user.UserRepository;
import com.HIGHERX.util.CrnVerifyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyService {

    private final UserRepository userRepository;

    public boolean validateAccount(String account) {
        return userRepository.findByAccount(account).isPresent();
    }

    public boolean validateCrn(String crn) {
        return CrnVerifyUtils.crnVerify(crn);
    }

    public boolean validateNickname(String nickname) {
        return userRepository.findByNickname(nickname).isPresent();
    }
}
