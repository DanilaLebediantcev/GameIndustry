package com.epam.bh.dao;

import java.util.List;

public interface DAO<T> {
    public void add(T t);

    public void update(T t);

    public void deleteById(long id);

    public T getById(long id);

    public List<T> getAll();

}
