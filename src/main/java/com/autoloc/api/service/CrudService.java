package com.autoloc.api.service;

/** CRUD complet : T = entité, D = DTO de réponse, ID = type de l'identifiant. */
public interface CrudService<T, D, ID>
        extends ReadService<D, ID>, WriteService<T, D, ID>, EntityFinder<T, ID> {
}
