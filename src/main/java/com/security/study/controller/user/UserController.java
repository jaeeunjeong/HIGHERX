package com.security.study.controller.user;

import com.security.study.controller.request.user.UserJoinRequest;
import com.security.study.controller.response.Response;
import com.security.study.controller.response.user.UserResponse;
import com.security.study.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public Response<Void> join(@RequestBody UserJoinRequest req) {
        userService.createUser(req);
        return Response.success();
    }

    @GetMapping
    public Response<UserResponse> readUser(Authentication authentication) {
        return Response.success(userService.readUser(authentication.getName()));
    }

    @DeleteMapping
    public Response<Void> deleteUser(Authentication authentication) {
        userService.deleteUser(authentication.getName());
        return Response.success();
    }

}
