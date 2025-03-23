package com.vuelos.reservations.controller;

import com.vuelos.reservations.dto.ErrorDTO;
import com.vuelos.reservations.dto.ReservationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "Algo malo ocurrió al momento de obtener las reservas",
                        content = @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = ErrorDTO.class)
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
                        description = "Identificador de la reserva",
                        required = true,
                        example = "1"
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
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Información de la reserva a crear",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ReservationDTO.class),
                            examples = @ExampleObject(
                                    name = "Reserva",
                                    summary = "Ejemplo de un registro para reserva",
                                    value = "{\n" +
                                            "    \"passengers\": [\n" +
                                            "        {\n" +
                                            "            \"firstName\": \"Andres\",\n" +
                                            "            \"lastName\": \"Sacco\",\n" +
                                            "            \"documentType\": \"DNI\",\n" +
                                            "            \"documentNumber\": \"12345678\",\n" +
                                            "            \"birthday\": \"1985-01-01\"\n" +
                                            "        }\n" +
                                            "    ],\n" +
                                            "    \"itinerary\": {\n" +
                                            "        \"segments\": [\n" +
                                            "            {\n" +
                                            "                \"origin\": \"MEX\",\n" +
                                            "                \"destination\": \"BUE\",\n" +
                                            "                \"departure\": \"2024-12-31\",\n" +
                                            "                \"arrival\": \"2025-01-01\",\n" +
                                            "                \"carrier\": \"AA\"\n" +
                                            "            }\n" +
                                            "        ],\n" +
                                            "        \"price\": {\n" +
                                            "            \"totalPrice\": 1,\n" +
                                            "            \"basePrice\": 1,\n" +
                                            "            \"totalTax\": 0\n" +
                                            "        }\n" +
                                            "    }\n" +
                                            "}"
                            )
                    )
            )
    )
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
                            description = "Identificador de la reserva a actualizar",
                            required = true,
                            example = "1"
                    ),
                    @Parameter(
                            in = ParameterIn.DEFAULT,
                            name = "reservationDTO",
                            description = "Información de la reserva a actualizar",
                            required = true
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Información de la reserva a crear",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ReservationDTO.class),
                            examples = @ExampleObject(
                                    name = "Reserva",
                                    summary = "Ejemplo de un registro para reserva",
                                    value = "{\n" +
                                            "    \"id\": 1,\n" +
                                            "    \"passengers\": [\n" +
                                            "        {\n" +
                                            "            \"firstName\": \"Andres\",\n" +
                                            "            \"lastName\": \"Sacco\",\n" +
                                            "            \"documentType\": \"DNI\",\n" +
                                            "            \"documentNumber\": \"12345678\",\n" +
                                            "            \"birthday\": \"1985-01-01\"\n" +
                                            "        }\n" +
                                            "    ],\n" +
                                            "    \"itinerary\": {\n" +
                                            "        \"segments\": [\n" +
                                            "            {\n" +
                                            "                \"origin\": \"MEX\",\n" +
                                            "                \"destination\": \"BOG\",\n" +
                                            "                \"departure\": \"2024-12-31\",\n" +
                                            "                \"arrival\": \"2025-01-01\",\n" +
                                            "                \"carrier\": \"AA\"\n" +
                                            "            }\n" +
                                            "        ],\n" +
                                            "        \"price\": {\n" +
                                            "            \"totalPrice\": 1,\n" +
                                            "            \"basePrice\": 1,\n" +
                                            "            \"totalTax\": 0\n" +
                                            "        }\n" +
                                            "    }\n" +
                                            "}"
                            )
                    )
            ))
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
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No encuentra la reserva a eliminar o no existe",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Algo malo ocurrió al momento de eliminar la reserva",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDTO.class)
                            )
                    )
            },
            parameters = {
                    @Parameter(
                            in = ParameterIn.PATH,
                            name = "id",
                            description = "Identificador de la reserva a eliminar",
                            required = true,
                            example = "1"
                    )
            })
    public ResponseEntity<Void> deleteReservation(@Min(1) @PathVariable Long id);
}
