package com.back.gestiontareas.service;

import com.back.gestiontareas.dto.CrearTareaRequest;
import com.back.gestiontareas.dto.TareaDTO;

import java.util.List;

public interface TareaService {
    TareaDTO crearTarea(CrearTareaRequest request);
    List<TareaDTO> listarTareasPorProyecto(Long proyectoId);
    TareaDTO completarTarea(Long tareaId);
}
