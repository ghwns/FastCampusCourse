package com.example.session.controller;

import com.example.session.model.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
public class UserAPIController {

    @GetMapping("/me")
    public UserDTO me(
            HttpSession httpSession
    ){
        var userObject = httpSession.getAttribute("USER"); //저장하고 있는 세션 불러옴
        if(userObject != null){
            var userDto = (UserDTO) userObject;
            return userDto;
        }else {
            return null;
        }
    }
}
