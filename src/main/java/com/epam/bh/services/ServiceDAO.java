package com.epam.bh.services;

import java.util.List;


public interface ServiceDAO<T> {
    public void add(T t);
    public void update(T t);
    public void delete(long id);
    public T getById(long id);
    public List<T> getAll();
}
