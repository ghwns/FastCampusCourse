package com.example.session.db;

import com.example.session.model.UserDTO;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.swing.plaf.OptionPaneUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserRepository {
    private List<UserDTO> userList = new ArrayList<>();

    public Optional<UserDTO> findByName(String name){
        return userList.stream().filter(it -> {
            return it.getName().equals(name);
        }).findFirst();
    }

    @PostConstruct //bean이 초기화되면 호출되는 메소드
    public void init(){
        userList.add(
                new UserDTO(
                        "홍길동",
                        "1234"
                )
        );
        userList.add(
                new UserDTO(
                        "김길동",
                        "1234"
                )
        );
        userList.add(
                new UserDTO(
                        "이길동",
                        "1234"
                )
        );

    }
}
