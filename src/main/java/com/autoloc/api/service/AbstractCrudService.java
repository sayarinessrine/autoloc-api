package com.autoloc.api.service;

import com.autoloc.api.exception.ResourceNotFoundException;
import com.autoloc.api.mapper.EntityMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * CRUD générique. Les réponses sont toujours converties en DTO par le mapper
 * à l'intérieur de la transaction (les relations LAZY sont donc chargées au bon moment).
 *
 * OCP : les sous-classes ajoutent leurs règles via validate().
 * DIP : dépend des abstractions JpaRepository et EntityMapper.
 */
@Transactional(readOnly = true)
public abstract class AbstractCrudService<T, D, ID> implements CrudService<T, D, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    private final JpaRepository<T, ID> repository;
    private final EntityMapper<T, D> mapper;
    private final String entityName;

    protected AbstractCrudService(JpaRepository<T, ID> repository, EntityMapper<T, D> mapper, String entityName) {
        this.repository = repository;
        this.mapper = mapper;
        this.entityName = entityName;
    }

    /** Affecte l'identifiant à l'entité (spécifique à chaque entité). */
    protected abstract void assignId(T entity, ID id);

    /** Point d'extension : règles métier propres à chaque entité (vide par défaut). */
    protected void validate(T entity) {
    }

    /** Conversion Entité -> DTO, déléguée au mapper. */
    protected D toDto(T entity) {
        return mapper.toDto(entity);
    }

    @Override
    public List<D> getAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public D getById(ID id) {
        return toDto(findEntityById(id));
    }

    @Override
    public T findEntityById(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(entityName + " introuvable avec l'id " + id));
    }

    @Override
    @Transactional
    public D create(T entity) {
        validate(entity);
        assignId(entity, null);
        return toDto(saveAndRefresh(entity));
    }

    @Override
    @Transactional
    public D update(ID id, T entity) {
        findEntityById(id);
        validate(entity);
        assignId(entity, id);
        return toDto(saveAndRefresh(entity));
    }

    @Override
    @Transactional
    public void delete(ID id) {
        findEntityById(id);
        repository.deleteById(id);
    }

    /** Enregistre puis recharge l'entité : les relations (agence, client...) sont complètes pour le mapper. */
    private T saveAndRefresh(T entity) {
        T saved = repository.save(entity);
        entityManager.flush();
        entityManager.refresh(saved);
        return saved;
    }
}
