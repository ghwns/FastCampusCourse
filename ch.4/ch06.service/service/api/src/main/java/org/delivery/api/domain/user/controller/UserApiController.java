package org.delivery.api.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.UserSession;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.user.business.UserBusiness;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserBusiness userBusiness;
    @GetMapping("/me")
    public Api<UserResponse> me(
            @UserSession User user //이 파라미터를 달면 UserResolver 동작
    ){

        // interceptor에서 저장한 context를 불러와서 한 thread 내의 request 정보를 얻을 수 있겠다.
        // Controller에서는 쓰지말자
//        var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
//
//        var userId = requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST);

        var response = userBusiness.me(user.getId());
        return Api.OK(response);
    }
}
