package com.salesianos.triana.dam.clinicflowrls.service;

import com.salesianos.triana.dam.clinicflowrls.dto.CreateCitaRequest;
import com.salesianos.triana.dam.clinicflowrls.model.Cita;
import com.salesianos.triana.dam.clinicflowrls.model.Estado;
import com.salesianos.triana.dam.clinicflowrls.model.Paciente;
import com.salesianos.triana.dam.clinicflowrls.model.Profesional;
import com.salesianos.triana.dam.clinicflowrls.repository.CitaRepository;
import com.salesianos.triana.dam.clinicflowrls.repository.ConsultaRepository;
import com.salesianos.triana.dam.clinicflowrls.repository.PacienteRepository;
import com.salesianos.triana.dam.clinicflowrls.repository.ProfesionalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CitaService {

    private final CitaRepository citaRepository;
    private final PacienteRepository pacienteRepository;
    private final ProfesionalRepository profesionalRepository;
    private final ConsultaRepository consultaRepository;

    public List<Cita> getCistasPorPaciente(Long pacienteId){

        List<Cita> citasPorPaciente = citaRepository.findByPaciente_IdOrderByFechaHoraAsc(pacienteId);

        if(citasPorPaciente.isEmpty()) throw new IllegalArgumentException("No hay citas del paciente");

        return citasPorPaciente;

    }

    public List<Cita> getCistasPorEstado(Estado estado){

        List<Cita> citasPorEstado = citaRepository.findByEstado(estado);
        if(citasPorEstado.isEmpty()) throw new IllegalArgumentException("No hay citas con ese estado");
        return citasPorEstado;

    }

    public List<Cita> getCitasPorRangoFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        List<Cita> citasPorRango = citaRepository.findByFechaHoraBetween(fechaInicio,fechaFin);
        if(citasPorRango.isEmpty()) throw new IllegalArgumentException("No hay citas en ese rango");
        return citasPorRango;
    }



    public Cita crearCita(CreateCitaRequest citaRequest){

        Paciente paciente = pacienteRepository.findById(citaRequest.pacienteId())
                .orElseThrow(()-> new EntityNotFoundException("No se ha encontrado al paciente con id %d".formatted(citaRequest.pacienteId())));

        Profesional profesional = profesionalRepository.findById(citaRequest.profesionalId())
                .orElseThrow(()-> new EntityNotFoundException("No se ha encontrado al profesional con id %d".formatted(citaRequest.profesionalId())));

        profesional.getCitas().forEach(cita ->{
            if(cita.getFechaHora().isEqual(citaRequest.fechaHora()))
                throw new IllegalArgumentException("El profesional ya tiene una cita en esa hora");
        });

        paciente.getCitas().forEach(cita->{
            if(cita.getFechaHora().getDayOfMonth() == citaRequest.fechaHora().getDayOfMonth())
                throw new IllegalArgumentException("Un paciente no puede tener más de una cita el mismo día");
        });

        if(citaRequest.fechaHora().isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("No se pueden crear citas en el pasado");

        Cita cita = toEntity(citaRequest,paciente,profesional);

        paciente.addCita(cita);
        profesional.addCita(cita);

        return citaRepository.save(cita);

    }

    public Cita cancelarCita(Long citaId){
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(()->new EntityNotFoundException("Cita con id %d no encontrada".formatted(citaId)));

        if(cita.getEstado() == Estado.ATENDIDA || cita.getEstado() == Estado.CANCELADA)
            throw new IllegalArgumentException("Esta cita ya ha sido atendida o cancelada");

        cita.setEstado(Estado.CANCELADA);

        return citaRepository.save(cita);

    }

    public Cita toEntity(CreateCitaRequest requestCreate, Paciente paciente,Profesional profesional){
        return Cita.builder()
                .fechaHora(requestCreate.fechaHora())
                .estado((requestCreate.estado()))
                .paciente(paciente)
                .profesional(profesional)
                .build();
    }

}
