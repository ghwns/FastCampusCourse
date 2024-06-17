package com.example.simple_board.crud;

import com.example.simple_board.common.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public abstract class CRUDAbstractApiController<DTO, ENTITY> implements CRUDInterface<DTO>{

    @Autowired(required = false)
    private CRUDService<DTO, ENTITY> crudService;

    @PostMapping("")
    @Override
    public DTO create(
            @Valid
            @RequestBody
            DTO t) {
        return crudService.create(t);
    }

    @GetMapping("/id/{id}")
    @Override
    public Optional<DTO> read(
            @PathVariable
            Long id) {
        return crudService.read(id);
    }

    @PutMapping("")
    @Override
    public DTO update(
            @Valid
            @RequestBody
            DTO t) {
        return crudService.update(t);
    }

    @DeleteMapping("")
    @Override
    public void delete(
            @PathVariable
            Long id) {
        crudService.delete(id);
    }


    @GetMapping("/all")
    @Override
    public API<List<DTO>> list(
            @PageableDefault
            Pageable pageable) {
        return crudService.list(pageable);
    }
}
