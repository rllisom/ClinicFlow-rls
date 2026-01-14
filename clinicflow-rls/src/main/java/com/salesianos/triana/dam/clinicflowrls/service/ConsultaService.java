package com.salesianos.triana.dam.clinicflowrls.service;

import com.salesianos.triana.dam.clinicflowrls.dto.CreateConsultaRequest;
import com.salesianos.triana.dam.clinicflowrls.model.Cita;
import com.salesianos.triana.dam.clinicflowrls.model.Consulta;
import com.salesianos.triana.dam.clinicflowrls.model.Estado;
import com.salesianos.triana.dam.clinicflowrls.repository.CitaRepository;
import com.salesianos.triana.dam.clinicflowrls.repository.ConsultaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final CitaRepository citaRepository;

    public Consulta registrarConsulta(Long citaId, CreateConsultaRequest consultaRequest){
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(()->new EntityNotFoundException("No se ha encontrado la cita con id %d".formatted(citaId)));

        if(cita.getEstado() != Estado.PROGRAMADA)
            throw new IllegalArgumentException("La cita ya está atendida o ha sido cancelada");

        Consulta consulta = toEntity(consultaRequest,cita);
        cita.setEstado(Estado.ATENDIDA);

        return consultaRepository.save(consulta);

    }

    public static Consulta toEntity(CreateConsultaRequest consulta, Cita cita){
        return Consulta.builder()
                .observaciones(consulta.observaciones())
                .diagnostico(consulta.diagnostico())
                .fecha(consulta.fecha())
                .cita(cita)
                .build();
    }
}
