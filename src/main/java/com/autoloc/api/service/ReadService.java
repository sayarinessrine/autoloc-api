package com.autoloc.api.service;

import java.util.List;

/** Principe ISP : interface réduite aux opérations de lecture. */
public interface ReadService<T, ID> {

    List<T> getAll();

    T getById(ID id);
}
