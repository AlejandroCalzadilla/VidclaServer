package org.mailgrupo13.vidcla.Inventario.parabrisa.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import  lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaDTO {
    private UUID id;
    private  String nombre;
    private String codigo;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;



}
