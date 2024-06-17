package com.example.simple_board.crud;

public interface Converter<DTO, ENTITY> {

    DTO toDTO(ENTITY entity);

    ENTITY toENTITY(DTO dto);
}
