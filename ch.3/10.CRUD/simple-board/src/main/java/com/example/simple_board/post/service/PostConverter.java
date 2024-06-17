package com.example.simple_board.post.service;

import com.example.simple_board.crud.Converter;
import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.model.PostDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostConverter {

    //데이터의 변환 과정을 Converter 클래스에서만 관리하도록
    public PostDTO toDTO(PostEntity postEntity){
        return PostDTO.builder()
                .id(postEntity.getId())
                .boardId(postEntity.getBoard().getId())
                .userName(postEntity.getUserName())
                .password(postEntity.getPassword())
                .email(postEntity.getEmail())
                .status(postEntity.getStatus())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .postedAt(LocalDateTime.now())
                .build();
    }

}
