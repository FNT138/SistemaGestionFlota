package com.universidad.flota.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "solicitudes_viaje")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitudViaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaSalida;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="vehiculo_id")
    private Vehiculo vehiculo;

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

    // en SolicitudViaje.java
    @OneToMany(
            mappedBy = "solicitud",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<Aprobacion> aprobaciones = new ArrayList<>();

}
