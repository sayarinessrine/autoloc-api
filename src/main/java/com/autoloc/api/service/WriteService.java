package com.autoloc.api.service;

/** Principe ISP : interface réduite aux opérations d'écriture. */
public interface WriteService<T, ID> {

    T create(T entity);

    T update(ID id, T entity);

    void delete(ID id);
}
