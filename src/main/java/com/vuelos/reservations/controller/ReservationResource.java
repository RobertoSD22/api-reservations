package com.vuelos.reservations.controller;

import com.vuelos.reservations.dto.ReservationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(description = "Operaciones relacionadas con las reservas", name = "Reservas")
public interface ReservationResource {

    @Operation(
            description = "Obtiene todas las reservas",
            summary = "Endpoint para obtener todos los registros de las reservas generadas al momento",
            responses = {
                @ApiResponse(
                    responseCode = "200",
                    description = "Regresa la información de todas las reservas",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = List.class)
                    )
                )
            })
    public ResponseEntity<List<ReservationDTO>> getReservations();

    @Operation(
            description = "Obtiene la información de una reserva",
            summary = "Endpoint para obtener la información de una reserva en específico",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Regresa la reservación que se busca",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ReservationDTO.class)
                            )
                    )
            },
            parameters = {
                @Parameter(
                        in = ParameterIn.PATH,
                        name = "id",
                        description = "Identificador de la reserva"
                )
            })
    public ResponseEntity<ReservationDTO> getReservationById(@Min(1) @PathVariable Long id);

    @Operation(
            description = "Crea un registro de reserva",
            summary = "Endpoint para crear el registro de una reserva",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Regresa la reserva creada",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ReservationDTO.class)
                            )
                    )
            })
    public ResponseEntity<ReservationDTO> createReservation(@Valid @RequestBody ReservationDTO reservationDTO);

    @Operation(
            description = "Actualiza un registro de reserva",
            summary = "Endpoint para actualizar un registro de una reserva",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Regresa la reserva actualizada",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ReservationDTO.class)
                            )
                    )
            },
            parameters = {
                    @Parameter(
                            in = ParameterIn.PATH,
                            name = "id",
                            description = "Identificador de la reserva a actualizar"
                    ),
                    @Parameter(
                            in = ParameterIn.DEFAULT,
                            name = "reservationDTO",
                            description = "Información de la reserva a actualizar"
                    )
            })
    public ResponseEntity<ReservationDTO> updateReservation(
            @Min(1) @PathVariable Long id, @Valid @RequestBody ReservationDTO reservationDTO);

    @Operation(
            description = "Elimina un registro de reserva",
            summary = "Endpoint para eliminar un registro de una reserva",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "No regresas valor",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Void.class)
                            )
                    )
            },
            parameters = {
                    @Parameter(
                            in = ParameterIn.PATH,
                            name = "id",
                            description = "Identificador de la reserva a eliminar"
                    )
            })
    public ResponseEntity<Void> deleteReservation(@Min(1) @PathVariable Long id);
}
