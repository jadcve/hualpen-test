package com.back.gestiontareas.service;

import com.back.gestiontareas.dto.CrearProyectoRequest;
import com.back.gestiontareas.dto.ProyectoDTO;

import java.util.List;

public interface ProyectoService {
    ProyectoDTO crearProyecto(CrearProyectoRequest request);
    List<ProyectoDTO> listarProyectos();
}