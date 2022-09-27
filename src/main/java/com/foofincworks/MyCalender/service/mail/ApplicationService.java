package com.foofincworks.MyCalender.service.mail;

import java.util.List;

public interface ApplicationService<T> {
    T get(long id);

    List<T> getAll();

    void save(T t);

    void update(int id, T s);

    void delete(T t);
}
