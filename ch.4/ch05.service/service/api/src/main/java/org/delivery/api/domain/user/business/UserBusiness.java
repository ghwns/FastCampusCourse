package org.delivery.api.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.service.UserService;
import org.delivery.db.user.UserEntity;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Business
public class UserBusiness {

    private final UserService userService;

    private final UserConverter userConverter;

    private final TokenBusiness tokenBusiness;

    public UserResponse register(UserRegisterRequest request){

        var entity = userConverter.toEntity(request);

        var newEntity = userService.register(entity);

        var response = userConverter.toResponse(newEntity);

        return response;

        //완전히 같은 코드

//        return Optional.ofNullable(request)
//                .map(userConverter::toEntity)
//                .map(userService::register)
//                .map(userConverter::toResponse)
//                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "Request Null"));
    }

    /*
    * 1. email, password 사용자 체크
    * 2. user entity 로그인 확인
    *
    * TODO
    * 3. 토큰 발행
    * 4. 토큰 response
    * */
    public TokenResponse login(UserLoginRequest request) {

        var userEntity = userService.login(request.getEmail(), request.getPassword());

        //TODO Token 발행하기

        var tokenResponse = tokenBusiness.issueToken(userEntity);

        return tokenResponse;
    }

    public UserResponse me(Long userId) {
        var userEntity = userService.getUser(userId);
        var response = userConverter.toResponse(userEntity);
        return response;
    }
}
