package com.uter.commons.service;

import org.springframework.data.repository.Repository;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface GenericService<T, D, I> extends Repository<T, I> {

    D findById(I id);

    List<D> findAll();

    List<D> findFree(Date date, String license);

    D create(D entity);

    ResponseEntity<D> update(Long id, D entity);

    void deleteById(I id);
}
