package com.example.cookie.controller;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {
    private final UserRepository userRepository;

    @GetMapping("/me")
    public UserDto me(
            HttpServletRequest httpServletRequest,
            @CookieValue(name = "authorization-cookie", required = false)
            String authorizationCookie
    ){
        log.info("authorizationCookie : {}", authorizationCookie);

        var optionalUserDto = userRepository.findById(authorizationCookie); //쿠키를 가지고 확인
        return optionalUserDto.get();
//        var cookies = httpServletRequest.getCookies();
//
//        if(cookies!= null){
//            for (Cookie cookie : cookies){
//                log.info("key : {}, value : {}", cookie.getName(),cookie.getValue());
//            }
//        }
//        return null;
    }

    @GetMapping("/me2")
    public UserDto me2(
            @RequestHeader(name = "authorization", required = false)
            String authorizationHeader
    ){
        log.info("authorizationHeader : {}", authorizationHeader);

        var optionalUserDto = userRepository.findById(authorizationHeader);
        return optionalUserDto.get();
    }
}
