package com.universidad.flota.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.time.LocalDateTime;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "mantenimiento")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime fechaProgramada;

    @Column(nullable = false, length = 200)
    private String tipoSerivicio;

    //Nulos hasta que el vehiculo vuelve a estar disponible
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaInicioReal;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaFinReal;

    @Column
    private String facturaAdjunta;

    /**@Column(nullable = false)
    private Double km;**/



}
