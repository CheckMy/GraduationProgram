package com.web.pro.service;

import java.util.List;

public interface BaseService<T> {

    T save(T t);

    void delete(T t);

    T getById(Integer id);

    List<T> findAll();

}
