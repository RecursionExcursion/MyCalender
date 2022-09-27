package com.foofincworks.MyCalender.dao;

import java.util.List;

public interface ApplicationDao<T> {

    T get(long id);

    List<T> getAll();

    void save(T t);

    void update(T s, String[] params);

    void delete(T t);
}
