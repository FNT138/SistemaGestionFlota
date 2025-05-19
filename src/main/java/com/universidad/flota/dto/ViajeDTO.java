package com.universidad.flota.dto;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Data
@AllArgsConstructor
public class ViajeDTO {
    private Long id;
    private Long solicitudId;
    private Double kmInicio;
    private Double kmFin;
    private Double combustibleInicio;
    private Double combustibleFin;


}
