package com.salesianos.triana.dam.clinicflowrls.dto;

import com.salesianos.triana.dam.clinicflowrls.model.Cita;
import com.salesianos.triana.dam.clinicflowrls.model.Estado;

import java.time.LocalDateTime;

public record CreateCitaRequest(
        LocalDateTime fechaHora,
        Long pacienteId,
        Long profesionalId
) { }
