package com.back.gestiontareas.service.imp;

import com.back.gestiontareas.dto.CrearTareaRequest;
import com.back.gestiontareas.dto.TareaDTO;
import com.back.gestiontareas.model.EstadoTarea;
import com.back.gestiontareas.model.Proyecto;
import com.back.gestiontareas.model.Tarea;
import com.back.gestiontareas.repository.ProyectoRepository;
import com.back.gestiontareas.repository.TareaRepository;
import com.back.gestiontareas.service.impl.TareaServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TareaServiceImplTest {

    @Mock
    private TareaRepository tareaRepository;

    @Mock
    private ProyectoRepository proyectoRepository;

    @InjectMocks
    private TareaServiceImpl tareaService;

    @Test
    void crearTarea_deberiaCrearTareaCorrectamente() {
        // Arrange
        CrearTareaRequest request = new CrearTareaRequest();
        request.setTitulo("Tarea 1");
        request.setDescripcion("Descripción");
        request.setFechaLimite(LocalDate.now().plusDays(1));
        request.setProyectoId(1L);

        Proyecto proyecto = Proyecto.builder().id(1L).nombre("Proyecto 1").build();
        Tarea tareaGuardada = Tarea.builder()
                .id(10L)
                .titulo("Tarea 1")
                .descripcion("Descripción")
                .fechaLimite(request.getFechaLimite())
                .estado(EstadoTarea.PENDIENTE)
                .proyecto(proyecto)
                .build();

        when(proyectoRepository.findById(1L)).thenReturn(Optional.of(proyecto));
        when(tareaRepository.save(any())).thenReturn(tareaGuardada);

        // Act
        TareaDTO resultado = tareaService.crearTarea(request);

        // Assert
        assertNotNull(resultado);
        assertEquals("Tarea 1", resultado.getTitulo());
        assertEquals(EstadoTarea.PENDIENTE, resultado.getEstado());

        verify(tareaRepository).save(any());
        verify(proyectoRepository).findById(1L);
    }


    @Test
    void completarTarea_deberiaActualizarEstado() {
        // Arrange
        Proyecto proyecto = Proyecto.builder().id(1L).nombre("Proyecto 1").build();

        Tarea tareaExistente = Tarea.builder()
                .id(20L)
                .titulo("Tarea antigua")
                .estado(EstadoTarea.PENDIENTE)
                .proyecto(proyecto)
                .build();

        Tarea tareaActualizada = Tarea.builder()
                .id(20L)
                .titulo("Tarea antigua")
                .estado(EstadoTarea.COMPLETADA)
                .proyecto(proyecto)
                .build();

        when(tareaRepository.findById(20L)).thenReturn(Optional.of(tareaExistente));
        when(tareaRepository.save(any())).thenReturn(tareaActualizada);

        // Act
        TareaDTO resultado = tareaService.completarTarea(20L);

        // Assert
        assertNotNull(resultado);
        assertEquals(EstadoTarea.COMPLETADA, resultado.getEstado());
        assertEquals(20L, resultado.getId());

        verify(tareaRepository).findById(20L);
        verify(tareaRepository).save(any());
    }

    @Test
    void listarTareasPorProyecto_deberiaRetornarListaCorrecta() {
        // Arrange
        Proyecto proyecto = Proyecto.builder().id(1L).nombre("Proyecto 1").build();

        Tarea tarea1 = Tarea.builder()
                .id(1L)
                .titulo("Tarea 1")
                .estado(EstadoTarea.PENDIENTE)
                .proyecto(proyecto)
                .build();

        Tarea tarea2 = Tarea.builder()
                .id(2L)
                .titulo("Tarea 2")
                .estado(EstadoTarea.COMPLETADA)
                .proyecto(proyecto)
                .build();

        when(tareaRepository.findByProyectoId(1L)).thenReturn(List.of(tarea1, tarea2));

        // Act
        List<TareaDTO> tareas = tareaService.listarTareasPorProyecto(1L);

        // Assert
        assertEquals(2, tareas.size());
        assertEquals("Tarea 1", tareas.get(0).getTitulo());
        assertEquals("Tarea 2", tareas.get(1).getTitulo());

        verify(tareaRepository).findByProyectoId(1L);
    }


}