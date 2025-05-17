package com.universidad.flota.domain;

import javax.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name ="solicitud_id")
    private SolicitudViaje solicitud;

    private Double kmInicio;
    private Double kmFin;
    private Double combustibleInicio;
    private Double combustibleFin;

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

}
