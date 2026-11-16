package com.autoloc.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * SRP : centralise la journalisation. Si on change d'outil (fichier, ELK...),
 * seule cette classe est modifiée. Remplace System.out.println.
 */
@Service
public class JournalisationService {

    private static final Logger LOGGER = LoggerFactory.getLogger("autoloc.journal");

    public void info(String message, Object... args) {
        LOGGER.info(message, args);
    }

    public void erreur(String message, Object... args) {
        LOGGER.error(message, args);
    }
}
