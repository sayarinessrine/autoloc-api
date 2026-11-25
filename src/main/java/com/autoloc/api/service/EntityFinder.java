package com.autoloc.api.service;

/** Accès à l'entité elle-même, pour les autres services (jamais exposé par l'API). */
public interface EntityFinder<T, ID> {

    T findEntityById(ID id);
}
