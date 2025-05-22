package com.back.gestiontareas.service.impl;

import com.back.gestiontareas.dto.CrearProyectoRequest;
import com.back.gestiontareas.dto.ProyectoDTO;
import com.back.gestiontareas.dto.TareaDTO;
import com.back.gestiontareas.model.Proyecto;
import com.back.gestiontareas.model.Tarea;
import com.back.gestiontareas.repository.ProyectoRepository;
import com.back.gestiontareas.service.ProyectoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProyectoServiceImpl implements ProyectoService {

    private final ProyectoRepository proyectoRepository;

    @Override
    public ProyectoDTO crearProyecto(CrearProyectoRequest request) {
        Proyecto proyecto = Proyecto.builder()
                .nombre(request.getNombre())
                .build();

        Proyecto guardado = proyectoRepository.save(proyecto);
        return mapToDTO(guardado);
    }

    @Override
    public List<ProyectoDTO> listarProyectos() {
        return proyectoRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private ProyectoDTO mapToDTO(Proyecto proyecto) {
        ProyectoDTO dto = new ProyectoDTO();
        dto.setId(proyecto.getId());
        dto.setNombre(proyecto.getNombre());
        dto.setCreadoEn(proyecto.getCreadoEn());
        dto.setActualizadoEn(proyecto.getActualizadoEn());

        if (proyecto.getTareas() != null) {
            dto.setTareas(
                    proyecto.getTareas().stream()
                            .map(this::mapTareaToDTO)
                            .toList()
            );
        }

        return dto;
    }

    private TareaDTO mapTareaToDTO(Tarea tarea) {
        TareaDTO dto = new TareaDTO();
        dto.setId(tarea.getId());
        dto.setTitulo(tarea.getTitulo());
        dto.setDescripcion(tarea.getDescripcion());
        dto.setFechaLimite(tarea.getFechaLimite());
        dto.setEstado(tarea.getEstado());
        return dto;
    }

}
