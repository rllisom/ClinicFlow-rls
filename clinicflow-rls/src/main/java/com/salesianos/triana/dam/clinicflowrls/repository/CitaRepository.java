package com.salesianos.triana.dam.clinicflowrls.repository;

import com.salesianos.triana.dam.clinicflowrls.model.Cita;
import com.salesianos.triana.dam.clinicflowrls.model.Estado;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita,Long> {


    @EntityGraph(attributePaths = {"paciente"})
    List<Cita> findByPaciente_IdOrderByFechaHoraAsc(Long id);


    List<Cita> findByEstado(Estado estado);

    
    List<Cita> findByFechaHoraBetween(LocalDateTime fechaHoraStart, LocalDateTime fechaHoraEnd);


}
