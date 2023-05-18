package com.HIGHERX.controller.auth;


import com.HIGHERX.controller.request.auth.AuthLoginRequest;
import com.HIGHERX.controller.response.Response;
import com.HIGHERX.controller.response.auth.AuthLoginResponse;
import com.HIGHERX.controller.response.auth.AuthTokenResponse;
import com.HIGHERX.service.auth.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@RestController("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public Response<Void> login(AuthLoginRequest req, HttpServletResponse responses) {
        AuthLoginResponse res = authService.login(req.getAccount(), req.getPassword());

        Cookie accessCookie = new Cookie("accessToken", res.getAccessToken());
        Cookie refreshCookie = new Cookie("refreshToken", res.getRefreshToken());
        responses.addCookie(accessCookie);
        responses.addCookie(refreshCookie);

        return Response.success();
    }

    @PostMapping("/logout")
    public Response<Void> logout(Authentication authentication) {
        authService.logout(authentication.getName());
        return Response.success();
    }

    @GetMapping("/token")
    public Response<AuthTokenResponse> getToken(HttpServletRequest request) {

        String refreshToken = Arrays.stream(request.getCookies())
            .filter(cookie -> "refreshToken".equals(cookie.getName()))
            .map(Cookie::getValue)
            .findFirst()
            .orElse(null);

        return Response.success(authService.getToken(refreshToken));
    }
}
