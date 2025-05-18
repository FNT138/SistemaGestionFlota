package com.universidad.flota.domain;

import javax.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Incidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vehiculo vehiculo;

    private LocalDateTime fecha;
    private String descripcion;
    private String partePolicial;

    @ElementCollection
    private List<String> fotos;

}
