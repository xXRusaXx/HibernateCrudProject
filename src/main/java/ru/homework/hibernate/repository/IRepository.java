package ru.homework.hibernate.repository;

import java.util.List;

public interface IRepository<T> {
    T getById(long id);
    List<T> getAll();
    T create(T obj);
    T update(T obj);
    boolean delete(long id);
}
