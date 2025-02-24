package org.mailgrupo13.vidcla.Inventario.parabrisa.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import  lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParabrisaDTO {

    private UUID id;

    @Min(value = 20, message = "El tamaño  de arriba ser al menos 20 centímetros")
    @Max(value = 300, message = "El tamaño de arriba ser como máximo 300 centímetros")
    private  float arriba ;

    @Min(value = 20, message = "El tamaño de abajo ser al menos 20 centímetros")
    @Max(value = 300, message = "El tamaño de abajo ser como máximo 300 centímetros")
    private  float abajo ;


    @Min(value = 20, message = "El tamaño de costado ser al menos 20 centímetros")
    @Max(value = 300, message = "El tamaño de  costado ser como máximo 300 centímetros")
    private  float costado;


    @Min(value = 20, message = "El tamaño del medio ser al menos 20 centímetros")
    @Max(value = 300, message = "El tamaño del medio ser como máximo 300 centímetros")
    private float medio;

    private String observacion;

    private UUID categoriaId;

    private  UUID posicionId;

    private  UUID vehiculoId;

    private BigDecimal precio;

    private String codigo;

    private LocalDateTime creadoEn;

    private LocalDateTime actualizadoEn;




}