package com.salesianos.triana.dam.clinicflowrls.controller;

import com.salesianos.triana.dam.clinicflowrls.dto.CitaDetailDto;
import com.salesianos.triana.dam.clinicflowrls.dto.CitaListDto;
import com.salesianos.triana.dam.clinicflowrls.dto.CreateCitaRequest;
import com.salesianos.triana.dam.clinicflowrls.dto.CreateConsultaRequest;
import com.salesianos.triana.dam.clinicflowrls.model.Cita;
import com.salesianos.triana.dam.clinicflowrls.service.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/citas")
@RequiredArgsConstructor
@RestController
public class CitaController {


    private final CitaService citaService;

    @GetMapping()
    public Page<CitaListDto> obtenerCitas(@PageableDefault(page = 0, size = 20) Pageable pageable){
        return citaService.getAll(pageable).map(CitaListDto::of);
    }

    @GetMapping("/pacientes/{id}")
    public List<CitaListDto> obtenerCitasDePaciente(@PathVariable Long id){
        return citaService.getCistasPorPaciente(id).stream().map(CitaListDto::of).toList();
    }

    @PostMapping()
    public CitaDetailDto CrearCita(@RequestParam CreateCitaRequest citaRequest){
        return CitaDetailDto.of(citaService.crearCita(citaRequest));
    }

    @PostMapping("/{id}/consultas")
    public CitaDetailDto registrarCita(@PathVariable Long id, CreateConsultaRequest consultaRequest){
        return CitaDetailDto.of(citaService.registrarConsulta(id,consultaRequest));
    }

    @PutMapping("/{id}/cancelar")
    public CitaDetailDto cancelarCita (@PathVariable Long id){
        return CitaDetailDto.of(citaService.cancelarCita(id));
    }



}
