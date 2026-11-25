package com.autoloc.api.service.impl;

import com.autoloc.api.domain.Reservation;
import com.autoloc.api.dto.ReservationDTO;
import com.autoloc.api.exception.BadRequestException;
import com.autoloc.api.mapper.ReservationMapper;
import com.autoloc.api.repository.ReservationRepository;
import com.autoloc.api.service.AbstractCrudService;
import com.autoloc.api.service.ReservationService;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl extends AbstractCrudService<Reservation, ReservationDTO, Long> implements ReservationService {

    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
        super(reservationRepository, reservationMapper, "Réservation");
    }

    @Override
    protected void assignId(Reservation reservation, Long id) {
        reservation.setIdReservation(id);
    }

    @Override
    protected void validate(Reservation reservation) {
        if (reservation.getDateDebut() == null || reservation.getDateFin() == null) {
            throw new BadRequestException("Les dates de début et de fin sont obligatoires");
        }
        if (reservation.getDateFin().isBefore(reservation.getDateDebut())) {
            throw new BadRequestException("La date de fin ne peut pas précéder la date de début");
        }
    }
}
