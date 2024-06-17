package com.example.simple_board.crud;

import com.example.simple_board.common.API;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CRUDInterface<DTO> {
    DTO create(DTO t);

    Optional<DTO> read(Long id);

    DTO update(DTO t);

    void delete(Long id);

    API<List<DTO>> list(Pageable pageable);
}
