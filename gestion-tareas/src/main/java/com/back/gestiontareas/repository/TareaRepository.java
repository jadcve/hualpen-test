package com.back.gestiontareas.repository;

import com.back.gestiontareas.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByProyectoId(Long proyectoId);
}
