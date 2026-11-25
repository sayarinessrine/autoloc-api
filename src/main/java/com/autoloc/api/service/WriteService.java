package com.autoloc.api.service;

/** Principe ISP : opérations d'écriture. L'entrée est l'entité, la sortie est un DTO. */
public interface WriteService<T, D, ID> {

    D create(T entity);

    D update(ID id, T entity);

    void delete(ID id);
}
