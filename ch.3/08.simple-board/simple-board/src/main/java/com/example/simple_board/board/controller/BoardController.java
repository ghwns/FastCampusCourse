package com.example.simple_board.board.controller;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.board.model.BoardDTO;
import com.example.simple_board.board.model.BoardRequest;
import com.example.simple_board.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("")
    public BoardDTO create(
            @Valid
            @RequestBody
            BoardRequest boardRequest
    ){
        return boardService.create(boardRequest);
    }

    @GetMapping("/id/{id}")
    public BoardDTO view(
            @PathVariable Long id
    ){
        return boardService.view(id);
    }
}
