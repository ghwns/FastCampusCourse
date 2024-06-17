package com.example.simple_board.crud;

import com.example.simple_board.common.API;
import com.example.simple_board.common.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class CRUDService<DTO, ENTITY> implements CRUDInterface<DTO> {

    @Autowired(required = false)
    private JpaRepository<ENTITY, Long> jpaRepository;

    @Autowired(required = false)
    private Converter<DTO, ENTITY> converter; //Converter를 상속받은 bean이 있으면 컨테이너에 등록, 없으면 null값

    @Override
    public DTO create(DTO dto) {
        var entity = converter.toENTITY(dto);

        jpaRepository.save(entity);

        var returnDTO = converter.toDTO(entity);

        return returnDTO;
    }

    @Override
    public Optional<DTO> read(Long id) {
        var optionalEntity = jpaRepository.findById(id);

        var dto = optionalEntity.map(
                it -> {
                    return converter.toDTO(it);
                }
        ).orElseGet(() -> null);

        return Optional.ofNullable(dto);
    }

    @Override
    public DTO update(DTO dto) {

        var entity = converter.toENTITY(dto);

        jpaRepository.save(entity);

        var returnDTO = converter.toDTO(entity);

        return returnDTO;
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public API<List<DTO>> list(Pageable pageable) {
        var list = jpaRepository.findAll(pageable);

        var pagination = Pagination.builder()
                .page(list.getNumber())
                .size(list.getSize())
                .currentElements(list.getNumberOfElements())
                .totalElements(list.getTotalElements())
                .totalPage(list.getTotalPages())
                .build();

        var dtoList = list.stream()
                .map(it -> {
                    return converter.toDTO(it);
                }).collect(Collectors.toList());

        var response = API.<List<DTO>>builder()
                .body(dtoList)
                .pagination(pagination)
                .build();
        return response;
    }
}
