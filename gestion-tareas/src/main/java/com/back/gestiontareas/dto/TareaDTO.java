package com.back.gestiontareas.dto;

import com.back.gestiontareas.model.EstadoTarea;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.time.LocalDate;

@Data
public class TareaDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaLimite;
    private EstadoTarea estado;
}
