package com.universidad.flota.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncidenteRequest {
    private Long vehiculoId;
    private LocalDateTime fecha;
    private String descripcion;
    private String partePolicial;
    private List<String> fotos;
}
