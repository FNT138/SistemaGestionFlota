// src/main/java/com/universidad/flota/dto/VehiculoResponse.java
package com.universidad.flota.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehiculoResponse {
    private Long id;
    private String patente;
    private String modelo;
    private String estado;   // DISPONIBLE, EN_MANTENIMIENTO, FUERA_SERVICIO, etc.
}
