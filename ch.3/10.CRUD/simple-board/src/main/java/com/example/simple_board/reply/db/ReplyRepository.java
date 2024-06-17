package com.example.simple_board.reply.db;

import com.example.simple_board.post.db.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {

    List<ReplyEntity> findAllByPostIdAndStatusOrderByIdDesc(Long postId, String status);

}
