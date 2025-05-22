package com.back.gestiontareas.service.imp;

import com.back.gestiontareas.dto.CrearProyectoRequest;
import com.back.gestiontareas.dto.ProyectoDTO;
import com.back.gestiontareas.model.Proyecto;
import com.back.gestiontareas.repository.ProyectoRepository;
import com.back.gestiontareas.service.impl.ProyectoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProyectoServiceImplTest {

    @Mock
    private ProyectoRepository proyectoRepository;

    @InjectMocks
    private ProyectoServiceImpl proyectoService;

    @Test
    void crearProyecto_deberiaCrearProyectoCorrectamente() {
        // Arrange
        CrearProyectoRequest request = new CrearProyectoRequest();
        request.setNombre("Nuevo Proyecto");

        Proyecto proyectoGuardado = Proyecto.builder()
                .id(1L)
                .nombre("Nuevo Proyecto")
                .build();

        when(proyectoRepository.save(any())).thenReturn(proyectoGuardado);

        // Act
        ProyectoDTO resultado = proyectoService.crearProyecto(request);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Nuevo Proyecto", resultado.getNombre());

        verify(proyectoRepository).save(any());
    }

    @Test
    void listarProyectos_deberiaRetornarListaDeProyectos() {
        // Arrange
        Proyecto proyecto1 = Proyecto.builder().id(1L).nombre("Proyecto 1").build();
        Proyecto proyecto2 = Proyecto.builder().id(2L).nombre("Proyecto 2").build();

        when(proyectoRepository.findAll()).thenReturn(List.of(proyecto1, proyecto2));

        // Act
        List<ProyectoDTO> resultado = proyectoService.listarProyectos();

        // Assert
        assertEquals(2, resultado.size());
        assertEquals("Proyecto 1", resultado.get(0).getNombre());
        assertEquals("Proyecto 2", resultado.get(1).getNombre());

        verify(proyectoRepository).findAll();
    }


}
