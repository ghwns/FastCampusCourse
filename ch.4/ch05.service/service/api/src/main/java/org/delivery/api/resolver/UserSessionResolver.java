package org.delivery.api.resolver;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.UserSession;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.user.service.UserService;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class UserSessionResolver implements HandlerMethodArgumentResolver {

    private final UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //지원하는 파라미터, 어노테이션 체크

        // 어노테이션 존재 체크

        //@UserSession 어노테이션이 있는지,
        var annotation = parameter.hasParameterAnnotation(UserSession.class);

        // 파라미터 타입 체크

        // 파라미터가 User 타입인지 확인
        var parameterType = parameter.getParameterType().equals(User.class);

        return (annotation && parameterType);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 위에서 true로 넘어옴

        // 사용자 정보 셋팅

        // 비즈니스 로직에서 수행하지 않도록
        var requestContext = RequestContextHolder.getRequestAttributes();

        var userId = requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST);

        var userEntity = userService.getUser(Long.parseLong(userId.toString()));

        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .status(userEntity.getStatus())
                .address(userEntity.getAddress())
                .password(userEntity.getPassword())
                .registeredAt(userEntity.getRegisteredAt())
                .unregisteredAt(userEntity.getUnregisteredAt())
                .lastLoginAt(userEntity.getLastLoginAt())
                .build();
    }
}
