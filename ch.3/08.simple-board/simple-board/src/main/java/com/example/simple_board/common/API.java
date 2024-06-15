package com.example.simple_board.common;


import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class API<T> {

    private T body;

    private Pagination pagination;
}
