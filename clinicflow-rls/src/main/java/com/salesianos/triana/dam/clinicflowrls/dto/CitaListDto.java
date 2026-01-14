package com.salesianos.triana.dam.clinicflowrls.dto;

import com.salesianos.triana.dam.clinicflowrls.model.Cita;
import com.salesianos.triana.dam.clinicflowrls.model.Estado;

import java.time.LocalDateTime;

public record CitaListDto(
        Long id,
        LocalDateTime fechaHora,
        Estado estado,
        PacienteSimpleDto pacienteDto,
        ProfesionalSimpleDto profesionalDto
        ) {
    public record PacienteSimpleDto(Long id,String nombre){
    }
    public record ProfesionalSimpleDto(Long id, String nombre){}



    public static CitaListDto of(Cita c){
        return new CitaListDto(
                c.getId(),
                c.getFechaHora(),
                c.getEstado(),
                new PacienteSimpleDto(c.getPaciente().getId(),
                        c.getPaciente().getNombre()),
                new ProfesionalSimpleDto(c.getProfesional().getId(),
                        c.getProfesional().getNombre())
        );
    }

}
