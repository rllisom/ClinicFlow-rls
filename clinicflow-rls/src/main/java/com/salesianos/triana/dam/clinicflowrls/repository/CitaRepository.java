package com.salesianos.triana.dam.clinicflowrls.repository;

import com.salesianos.triana.dam.clinicflowrls.model.Cita;
import com.salesianos.triana.dam.clinicflowrls.model.Estado;
import com.salesianos.triana.dam.clinicflowrls.model.Profesional;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita,Long> {


    @Override
    @Nullable
    @EntityGraph(attributePaths = {"paciente", "profesional"})
    Page<Cita> findAll(Pageable pageable);


    @EntityGraph(attributePaths = {"paciente", "profesional"})
    List<Cita> findByPaciente_IdOrderByFechaHoraAsc(Long id);

    @EntityGraph(attributePaths = {"paciente","profesional"})
    List<Cita> findByEstado(Estado estado);

    @EntityGraph(attributePaths = {"paciente","profesional"})
    List<Cita> findByFechaHoraBetween(LocalDateTime fechaHoraStart, LocalDateTime fechaHoraEnd);

    @Query("select c from Cita c join fetch c.paciente where c.profesional = ?1 and c.fechaHora > ?2 order by c.fechaHora")
    List<Cita> findByProfesionalAndFechaHoraAfterOrderByFechaHoraAsc(Profesional profesional, LocalDateTime fechaHora);


}
