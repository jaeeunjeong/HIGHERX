package com.security.study.controller.verify;

import com.security.study.controller.response.Response;
import com.security.study.controller.response.verify.VerifyAccountResponse;
import com.security.study.controller.response.verify.VerifyCrnResponse;
import com.security.study.controller.response.verify.VerifyNicknameResponse;
import com.security.study.service.verify.VerifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/verify")
public class VerifyController {

    private final VerifyService verifyService;

    @GetMapping("/account")
    public Response<VerifyAccountResponse> getAccount(@RequestParam(name = "account", required = true) String account) {
        return Response.success(new VerifyAccountResponse(verifyService.validateAccount(account)));
    }

    @GetMapping("/nickname")
    public Response<VerifyNicknameResponse> getNickname(@RequestParam(name = "nickname", required = true) String nickname) {
        return Response.success(new VerifyNicknameResponse(verifyService.validateNickname(nickname)));
    }
}
