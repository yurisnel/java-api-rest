package bz.nimitz.ybr.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import bz.nimitz.ybr.demo.Utils.IStatusCount;
import bz.nimitz.ybr.demo.model.StateView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Status services", description = "API para la gestión de la disponibilidad de servicios por estado (provincia)")
@RequestMapping("/v1/api")

public interface MyControllerApi {

    @Operation(summary = "Disponibilidad actual", description = "Disponibilidad de todos los servicios por provincia", responses = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = StateView.class))) }),
    })

    @GetMapping("/services/status")
    public ResponseEntity<List<StateView>> statusCurrent();

    @Operation(summary = "Disponibilidad actual de provincia", description = "Disponibilidad actual de servivios de una provincia", responses = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StateView.class)) }),
            @ApiResponse(responseCode = "404", description = "State Not Found") })
    @GetMapping("/services/status/{state}")
    public ResponseEntity<StateView> statusCurrentOfState(
            @Parameter(description = "Nombre de provincia", required = true) @PathVariable String state);

    @Operation(summary = "Disponibilidad actual por fecha", description = "Disponibilidad de servicios por provincia dada una fecha", responses = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = StateView.class))) }),
    })
    @GetMapping("/services/status/date/{dateTime}")
    public ResponseEntity<List<StateView>> statusFromDate(
            @Parameter(description = "Fecha a filtrar", required = true) @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dateTime);

    @Operation(summary = "Provincia más afectada", description = "Provincia que tuvo mas afectaciones de servicio", responses = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = IStatusCount.class)) }),
    })
    @GetMapping("/services/most_unavailable")
    public ResponseEntity<IStatusCount> statusFromDate();

}
