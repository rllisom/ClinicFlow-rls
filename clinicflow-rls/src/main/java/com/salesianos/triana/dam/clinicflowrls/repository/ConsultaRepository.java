package com.salesianos.triana.dam.clinicflowrls.repository;

import com.salesianos.triana.dam.clinicflowrls.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta,Long> {
}
