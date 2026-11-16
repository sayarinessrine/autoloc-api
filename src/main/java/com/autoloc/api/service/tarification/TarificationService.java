package com.autoloc.api.service.tarification;

import com.autoloc.api.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * Contexte du patron Strategy.
 * Spring injecte dans la Map toutes les stratégies : la clé est le nom du bean
 * (STANDARD, WEEKEND, LONGUE_DUREE...). Ajouter une politique ne demande AUCUNE
 * modification de cette classe (principe OCP).
 */
@Service
public class TarificationService {

    private final Map<String, StrategieTarification> strategies;

    public TarificationService(Map<String, StrategieTarification> strategies) {
        this.strategies = strategies;
    }

    public double calculer(String type, double tarifJournalier, int jours) {
        String cle = type == null ? "" : type.trim().toUpperCase();
        StrategieTarification strategie = strategies.get(cle);
        if (strategie == null) {
            throw new BadRequestException("Type de tarif inconnu : '" + type
                    + "'. Types disponibles : " + typesDisponibles());
        }
        double prix = strategie.calculer(tarifJournalier, jours);
        return Math.round(prix * 100.0) / 100.0;
    }

    public List<String> typesDisponibles() {
        return List.copyOf(new TreeSet<>(strategies.keySet()));
    }
}
