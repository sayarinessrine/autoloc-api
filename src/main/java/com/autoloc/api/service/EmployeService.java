package com.autoloc.api.service;

import com.autoloc.api.domain.Employe;
import com.autoloc.api.dto.EmployeDTO;

public interface EmployeService extends CrudService<Employe, EmployeDTO, Long> {

    /** Retire l'employé de son agence actuelle (agence = null). */
    EmployeDTO desaffecterEmploye(Long idEmploye);

    /** Rattache l'employé à une agence. Il ne doit appartenir à aucune agence (désaffectation préalable). */
    EmployeDTO affecterEmploye(Long idEmploye, Long idAgence);
}
