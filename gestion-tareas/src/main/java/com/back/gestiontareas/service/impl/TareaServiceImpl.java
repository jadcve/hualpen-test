package com.back.gestiontareas.service.impl;

import com.back.gestiontareas.dto.CrearTareaRequest;
import com.back.gestiontareas.dto.TareaDTO;
import com.back.gestiontareas.exception.NotFoundException;
import com.back.gestiontareas.model.EstadoTarea;
import com.back.gestiontareas.model.Proyecto;
import com.back.gestiontareas.model.Tarea;
import com.back.gestiontareas.repository.ProyectoRepository;
import com.back.gestiontareas.repository.TareaRepository;
import com.back.gestiontareas.service.TareaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TareaServiceImpl implements TareaService {

    private final TareaRepository tareaRepository;
    private final ProyectoRepository proyectoRepository;

    @Override
    public TareaDTO crearTarea(CrearTareaRequest request) {
        Proyecto proyecto = proyectoRepository.findById(request.getProyectoId())
                .orElseThrow(() -> new NotFoundException("El proyecto con ID " + request.getProyectoId() + " no existe"));
        Tarea tarea = Tarea.builder()
                .titulo(request.getTitulo())
                .descripcion(request.getDescripcion())
                .fechaLimite(request.getFechaLimite())
                .estado(EstadoTarea.PENDIENTE)
                .proyecto(proyecto)
                .build();

        Tarea guardada = tareaRepository.save(tarea);
        return mapToDTO(guardada);
    }

    @Override
    public List<TareaDTO> listarTareasPorProyecto(Long proyectoId) {
        return tareaRepository.findByProyectoId(proyectoId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public TareaDTO completarTarea(Long tareaId) {
        Tarea tarea = tareaRepository.findById(tareaId)
                .orElseThrow(() -> new NotFoundException("La tarea con ID " + tareaId + " no existe"));

        tarea.setEstado(EstadoTarea.COMPLETADA);
        return mapToDTO(tareaRepository.save(tarea));
    }

    private TareaDTO mapToDTO(Tarea tarea) {
        TareaDTO dto = new TareaDTO();
        dto.setId(tarea.getId());
        dto.setTitulo(tarea.getTitulo());
        dto.setDescripcion(tarea.getDescripcion());
        dto.setFechaLimite(tarea.getFechaLimite());
        dto.setEstado(tarea.getEstado());
        return dto;
    }
}
