package com.back.gestiontareas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CrearProyectoRequest {
    @NotBlank(message = "El nombre del proyecto es obligatorio")
    @Size(max = 250, message = "El título no puede tener más de 250 caracteres")
    private String nombre;
}
