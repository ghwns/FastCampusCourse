package com.example.memorydb.db;

import java.util.List;
import java.util.Optional;

public interface DataRepository<T, ID> extends Repository<T, ID> {

    // Create, Update
    T save(T data);

    // Read
    //데이터가 없을 수 있으므로 Optional
    Optional<T> findById(ID id);

    List<T> findAll();

    //delete
    void delete(ID id);
}