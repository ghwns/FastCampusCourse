package com.example.simple_board.reply.service;

import com.example.simple_board.crud.Converter;
import com.example.simple_board.post.db.PostRepository;
import com.example.simple_board.reply.db.ReplyEntity;
import com.example.simple_board.reply.model.ReplyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReplyConverter implements Converter<ReplyDTO,ReplyEntity> {


    private final PostRepository postRepository;
    @Override
    public ReplyDTO toDTO(ReplyEntity replyEntity) {
        return ReplyDTO.builder()
                .id(replyEntity.getId())
                .postId(replyEntity.getPost().getId())
                .userName(replyEntity.getUserName())
                .password(replyEntity.getPassword())
                .status(replyEntity.getStatus())
                .title(replyEntity.getTitle())
                .content(replyEntity.getContent())
                .repliedAt(replyEntity.getRepliedAt())
                .build();

    }

    @Override
    public ReplyEntity toENTITY(ReplyDTO replyDTO) {

        var postEntity = postRepository.findById(replyDTO.getPostId());

        return ReplyEntity.builder()
                .id(replyDTO.getId())
                .post(postEntity.orElseGet(()->null))
                .userName(replyDTO.getUserName())
                .password(replyDTO.getPassword())
                .status((replyDTO.getStatus() != null) ? replyDTO.getStatus() : "REGISTERED")
                .title(replyDTO.getTitle())
                .content(replyDTO.getContent())
                .repliedAt(
                        (replyDTO.getRepliedAt() != null) ? replyDTO.getRepliedAt() : LocalDateTime.now())
                .build();
    }
}
