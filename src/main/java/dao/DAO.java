package dao;

import java.util.List;

public interface DAO<T> {
    public void add(T t);

    public void update(T t);

    public T getById(int id);

    public List<T> getAll();

    public void delete(T t);
}
