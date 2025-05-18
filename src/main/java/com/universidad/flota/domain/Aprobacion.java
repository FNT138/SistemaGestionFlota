package com.universidad.flota.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Aprobacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@JoinColumn(name = "solicitud_id")
    @ManyToOne
    @JoinColumn(name = "solicitud_id",nullable = false)
    private SolicitudViaje solicitud;

    @Column(name = "comentarios",nullable = false)
    private String comentarios;

    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estado;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fecha;
}
