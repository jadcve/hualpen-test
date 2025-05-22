package com.back.gestiontareas.controller;

import com.back.gestiontareas.dto.CrearTareaRequest;
import com.back.gestiontareas.dto.TareaDTO;
import com.back.gestiontareas.service.TareaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TareaController {

    private static final Logger logger = LoggerFactory.getLogger(TareaController.class);

    private final TareaService tareaService;

    @PostMapping("/tareas")
    public ResponseEntity<TareaDTO> crearTarea(@Valid @RequestBody CrearTareaRequest request) {
        TareaDTO tarea = tareaService.crearTarea(request);
        logger.info("Tarea creada con ID: {}", tarea.getId());
        return ResponseEntity.status(201).body(tarea);
    }

    @GetMapping("/proyectos/{proyectoId}/tareas")
    public ResponseEntity<List<TareaDTO>> listarTareasPorProyecto(@PathVariable Long proyectoId) {
        List<TareaDTO> tareas = tareaService.listarTareasPorProyecto(proyectoId);
        logger.info("Se encontraron {} tareas", tareas.size());
        return ResponseEntity.ok(tareas);
    }

    @PutMapping("/tareas/{id}/completar")
    public ResponseEntity<TareaDTO> completarTarea(@PathVariable Long id) {
        TareaDTO tarea = tareaService.completarTarea(id);
        logger.info("Tarea completada: {}", tarea);
        return ResponseEntity.ok(tarea);
    }
}
