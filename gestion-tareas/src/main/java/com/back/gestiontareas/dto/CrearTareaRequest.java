package com.back.gestiontareas.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CrearTareaRequest {
    @NotBlank(message = "El título es obligatorio")
    @Size(max = 50, message = "El título no puede tener más de 50 caracteres")
    private String titulo;

    @Size(max = 250, message = "La descripción no puede tener más de 250 caracteres")
    private String descripcion;

    @FutureOrPresent(message = "La fecha límite no puede ser antes de la fecha actual")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaLimite;

    @NotNull(message = "El ID del proyecto es obligatorio")
    private Long proyectoId;
}
