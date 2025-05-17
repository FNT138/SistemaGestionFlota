package com.universidad.flota.domain;

import javax.persistence.*;
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
    private SolicitudViaje solicitud;

    private String comentarios;

    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estado;

    private LocalDateTime fecha;
}
