package com.example.session.service;

import com.example.session.db.UserRepository;
import com.example.session.model.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public void login(
            LoginRequest loginRequest,
            HttpSession httpSession
    ){
        var id = loginRequest.getId();
        var pw = loginRequest.getPassword();

        var optionalUser = userRepository.findByName(id);
        if(optionalUser.isPresent()){
            var userDto = optionalUser.get();
            if(userDto.getPassword().equals(pw)){
                // 세션에 정보 저장, 나머지는 클라이언트에 쿠키로
                httpSession.setAttribute("USER", userDto);
            }else {
                throw new RuntimeException("Not a Correct Password");
            }
        }else{
            throw new RuntimeException("Not User Exist");
        }
    }
}
