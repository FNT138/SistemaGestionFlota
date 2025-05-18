package com.universidad.flota.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.universidad.flota.domain.Aprobacion;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AprobacionResponse {

    private Long id;
    private Long solicitudId;
    private String comentarios;
    private String estado;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fecha;

    public static AprobacionResponse fromEntity(Aprobacion a) {
        return AprobacionResponse.builder()
                .id(a.getId())
                .solicitudId(a.getSolicitud().getId())
                .comentarios(a.getComentarios())
                .estado(a.getEstado().name())
                .fecha(a.getFecha())
                .build();
    }

}
