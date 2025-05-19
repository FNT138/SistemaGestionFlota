package com.universidad.flota.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.universidad.flota.domain.EstadoSolicitud;
import com.universidad.flota.domain.Prioridad;
import com.universidad.flota.domain.Vehiculo;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitudViajeResponse {
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaSalida;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaRegreso;

    private String destino;
    private String motivo;
    private Prioridad prioridad;
    private EstadoSolicitud estado;
    private Long usuarioId;
    private String usuarioEmail;
}
