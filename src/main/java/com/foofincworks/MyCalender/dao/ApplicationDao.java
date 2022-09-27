package com.foofincworks.MyCalender.dao;

import java.util.List;

public interface ApplicationDao<T> {

    T get(long id);

    List<T> getAll();

    void save(T t);

    void update(int id, T s);

    void delete(T t);
}
