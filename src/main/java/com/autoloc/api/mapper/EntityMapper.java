package com.autoloc.api.mapper;

/**
 * Contrat commun des mappers (patron Adapter) : convertit une entité T en DTO D.
 * Chaque mapper est généré automatiquement par MapStruct à la compilation.
 */
public interface EntityMapper<T, D> {

    D toDto(T entity);
}
