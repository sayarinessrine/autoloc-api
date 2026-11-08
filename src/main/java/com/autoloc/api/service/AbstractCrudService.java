package com.autoloc.api.service;

import com.autoloc.api.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implémentation générique du CRUD (principe DRY).
 *
 * OCP : le code est fermé à la modification mais ouvert à l'extension :
 * une sous-classe ajoute ses règles métier en redéfinissant validate().
 * LSP : toutes les sous-classes respectent le contrat de CrudService.
 * DIP : dépend de l'abstraction JpaRepository, pas d'une classe concrète.
 */
@Transactional(readOnly = true)
public abstract class AbstractCrudService<T, ID> implements CrudService<T, ID> {

    private final JpaRepository<T, ID> repository;
    private final String entityName;

    protected AbstractCrudService(JpaRepository<T, ID> repository, String entityName) {
        this.repository = repository;
        this.entityName = entityName;
    }

    /** Affecte l'identifiant à l'entité (spécifique à chaque entité). */
    protected abstract void assignId(T entity, ID id);

    /** Point d'extension : règles métier propres à chaque entité (vide par défaut). */
    protected void validate(T entity) {
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public T getById(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(entityName + " introuvable avec l'id " + id));
    }

    @Override
    @Transactional
    public T create(T entity) {
        validate(entity);
        assignId(entity, null);
        return repository.save(entity);
    }

    @Override
    @Transactional
    public T update(ID id, T entity) {
        getById(id);
        validate(entity);
        assignId(entity, id);
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(ID id) {
        getById(id);
        repository.deleteById(id);
    }
}
