package com.salesianos.triana.dam.clinicflowrls.dto;

import com.salesianos.triana.dam.clinicflowrls.model.Consulta;

import java.time.LocalDateTime;

public record CreateConsultaRequest(
        String observaciones,
        String diagnostico,
        LocalDateTime fecha
) { }
