package com.universidad.flota.domain;


import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitudes_viaje")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudViaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaSalida;

    @Column(nullable = false)
    private LocalDateTime fechaRegreso;

    @Column(nullable = false, length = 200)
    private String destino;

    @Column(nullable = false, length = 500)
    private String motivo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Prioridad prioridad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoSolicitud estado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Constructor de conveniencia sin ID ni estado
    public SolicitudViaje(LocalDateTime fechaSalida,
                          LocalDateTime fechaRegreso,
                          String destino,
                          String motivo,
                          Prioridad prioridad,
                          Usuario usuario) {
        this.fechaSalida = fechaSalida;
        this.fechaRegreso = fechaRegreso;
        this.destino = destino;
        this.motivo = motivo;
        this.prioridad = prioridad;
        this.usuario = usuario;
        this.estado = EstadoSolicitud.PENDIENTE;
    }
}
