package org.delivery.api.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.user.business.UserBusiness;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/user")
//open-api 하위 주소는 인증을 수행하지 않음
public class UserOpenApiController {

    private final UserBusiness userBusiness;

    @PostMapping("/register")
    public Api<UserResponse> resgister(
            @Valid
            @RequestBody Api<UserRegisterRequest> request
    ){
        var response = userBusiness.register(request.getBody());

        return Api.OK(response);
    }

    @PostMapping("/login")
    public Api<UserResponse> login(
            @Valid
            @RequestBody Api<UserLoginRequest> request
    )
    {
        var response = userBusiness.login(request.getBody());
        return Api.OK(response);
    }
}
