package com.salesianos.triana.dam.clinicflowrls.repository;

import com.salesianos.triana.dam.clinicflowrls.model.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional,Long> {
}
