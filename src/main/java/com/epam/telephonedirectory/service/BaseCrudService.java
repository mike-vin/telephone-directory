package com.epam.telephonedirectory.service;

import java.util.Collection;

public interface BaseCrudService<T, ID> {

    T insert(T value);

    T update(T value);

    void delete(ID id);

    T findById(ID id);

    Collection<T> findAll();
}
