package com.autoloc.api.service;

import com.autoloc.api.domain.Employe;

public interface EmployeService extends CrudService<Employe, Long> {

    /** Retire l'employé de son agence actuelle (agence = null). */
    Employe desaffecterEmploye(Long idEmploye);

    /** Rattache l'employé à une agence. Il ne doit appartenir à aucune agence (désaffectation préalable). */
    Employe affecterEmploye(Long idEmploye, Long idAgence);
}
