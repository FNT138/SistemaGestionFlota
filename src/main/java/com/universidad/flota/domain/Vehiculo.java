package com.universidad.flota.domain;

import javax.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patente;

    private String modelo;

    @Enumerated(EnumType.STRING)
    private EstadoVehiculo estado;

    private Double kmActual;

}
