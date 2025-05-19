package com.universidad.flota.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.universidad.flota.domain.*;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitudViajeRequest {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaSalida;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaRegreso;

    private String destino;
    private String motivo;
    private Prioridad prioridad;
}
