package com.security.study.service.verify;

import com.security.study.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyService {

    private final UserRepository userRepository;

    public boolean validateAccount(String account) {
        return userRepository.findByAccount(account).isPresent();
    }

    public boolean validateNickname(String nickname) {
        return userRepository.findByNickname(nickname).isPresent();
    }
}
