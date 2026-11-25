package com.autoloc.api.service;

import java.util.List;

/** Principe ISP : opérations de lecture, qui renvoient des DTO. */
public interface ReadService<D, ID> {

    List<D> getAll();

    D getById(ID id);
}
