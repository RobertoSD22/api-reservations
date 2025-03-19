package com.vuelos.reservations.service;

import com.vuelos.reservations.dto.ReservationDTO;
import com.vuelos.reservations.exception.ReservationException;
import com.vuelos.reservations.model.Reservation;
import com.vuelos.reservations.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Clase de servicio para gestionar reservas.
 */
@Service
public class ReservationService {

    // Sirve para convertir de un objeto a otro, es un servicio de Spring
    private final ConversionService conversionService;

    private final ReservationRepository repository;

    /**
     * Constructor de ReservationService.
     *
     * @param conversionService el servicio de conversión
     * @param repository el repositorio de reservas
     */
    @Autowired
    public ReservationService(ConversionService conversionService, ReservationRepository repository) {
        this.conversionService = conversionService;
        this.repository = repository;
    }

    /**
     * Recupera todas las reservas y las convierte a ReservationDTO.
     *
     * @return una lista de ReservationDTO
     */
    @SuppressWarnings("unchecked")
    public List<ReservationDTO> getReservations() {
        return conversionService.convert(repository.getReservations(), List.class);
    }

    /**
     * Recupera una reserva por su ID y la convierte a ReservationDTO.
     *
     * @param id el ID de la reserva
     * @return el ReservationDTO
     * @throws ReservationException si la reserva no existe
     */
    public ReservationDTO getReservationById(Long id) {
        Optional<Reservation> result = repository.getReservationById(id);
        if (result.isEmpty()) {
            throw new ReservationException("No existe");
        }
        return conversionService.convert(result.get(), ReservationDTO.class);
    }

    /**
     * Guarda una nueva reserva.
     *
     * @param reservation el DTO de la reserva a guardar
     * @return el ReservationDTO guardado
     * @throws ReservationException si la reserva ya tiene un ID
     */
    public ReservationDTO save(ReservationDTO reservation) {
        if (Objects.nonNull(reservation.getId())) {
            throw new ReservationException("Duplicado");
        }
        // 1.- Transformamos el DTO a una entidad de tipo Reservation
        Reservation transformed = conversionService.convert(reservation, Reservation.class);
        // 2.- Guardamos la entidad en la base de datos
        Reservation result = repository.save(Objects.requireNonNull(transformed));
        // 3.- Transformamos la entidad a un DTO
        return conversionService.convert(result, ReservationDTO.class);
    }

    /**
     * Actualiza una reserva existente.
     *
     * @param id el ID de la reserva a actualizar
     * @param reservation el DTO de la reserva con los datos actualizados
     * @return el ReservationDTO actualizado
     * @throws ReservationException si la reserva no existe
     */
    public ReservationDTO update(Long id, ReservationDTO reservation) {
        if (getReservationById(id) == null) {
            throw new ReservationException("No existe");
        }
        // 1.- Transformamos el DTO a una entidad de tipo Reservation
        Reservation transformed = conversionService.convert(reservation, Reservation.class);
        // 2.- Guardamos la entidad en la base de datos
        Reservation result = repository.update(id, Objects.requireNonNull(transformed));
        // 3.- Transformamos la entidad a un DTO
        return conversionService.convert(result, ReservationDTO.class);
    }

    /**
     * Elimina una reserva por su ID.
     *
     * @param id el ID de la reserva a eliminar
     * @throws ReservationException si la reserva no existe
     */
    public void delete(Long id) {
        if (getReservationById(id) == null) {
            throw new ReservationException("No existe");
        }
        repository.delete(id);
    }
}
