package com.epam.bh.dao;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DAO<T> {
    @Transactional
    public void add(T t);
    @Transactional
    public void update(T t);
    @Transactional
    public void deleteById(long id);
    @Transactional
    public T getById(long id);
    @Transactional
    public List<T> getAll();

}
