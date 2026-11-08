package com.autoloc.api.service;

/** Contrat CRUD complet = lecture + écriture. */
public interface CrudService<T, ID> extends ReadService<T, ID>, WriteService<T, ID> {
}
