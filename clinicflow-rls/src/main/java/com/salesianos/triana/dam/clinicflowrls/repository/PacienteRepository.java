package com.salesianos.triana.dam.clinicflowrls.repository;

import com.salesianos.triana.dam.clinicflowrls.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long> {
}
