package com.example.simple_board.reply.controller;

import com.example.simple_board.crud.CRUDAbstractApiController;
import com.example.simple_board.post.model.PostViewRequest;
import com.example.simple_board.reply.db.ReplyEntity;
import com.example.simple_board.reply.model.ReplyDTO;
import com.example.simple_board.reply.model.ReplyRequest;
import com.example.simple_board.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reply")
public class ReplyApiController extends CRUDAbstractApiController<ReplyDTO, ReplyEntity> {

//    private final ReplyService replyService;
//
//    @PostMapping("")
//    public ReplyEntity create(
//            @Valid
//            @RequestBody ReplyRequest replyRequest
//            ){
//        return replyService.create(replyRequest);
//    }
}
