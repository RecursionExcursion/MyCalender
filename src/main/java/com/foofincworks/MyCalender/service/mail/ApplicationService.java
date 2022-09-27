package com.foofincworks.MyCalender.service.mail;

import java.util.List;

public interface ApplicationService<T> {
    T get(long id);

    List<T> getAll();

    void save(T t);

    void update(T s, String[] params);

    void delete(T t);
}
