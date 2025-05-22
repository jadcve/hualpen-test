package com.back.gestiontareas.controller;

import com.back.gestiontareas.dto.CrearProyectoRequest;
import com.back.gestiontareas.dto.ProyectoDTO;
import com.back.gestiontareas.service.ProyectoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proyectos")
@RequiredArgsConstructor
public class ProyectoController {

    private static final Logger logger = LoggerFactory.getLogger(ProyectoController.class);

    private final ProyectoService proyectoService;

    @PostMapping
    public ResponseEntity<ProyectoDTO> crearProyecto(@Valid @RequestBody CrearProyectoRequest request) {
        ProyectoDTO creado = proyectoService.crearProyecto(request);
        logger.info("Proyecto creado con ID: {}", creado.getId());
        return ResponseEntity.status(201).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<ProyectoDTO>> listarProyectos() {
        List<ProyectoDTO> proyectos = proyectoService.listarProyectos();
        logger.info("Cantidad de proyectos encontrados: {}", proyectos.size());
        return ResponseEntity.ok(proyectos);
    }
}
