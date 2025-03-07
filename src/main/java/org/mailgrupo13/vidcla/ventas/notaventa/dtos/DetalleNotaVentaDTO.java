package org.mailgrupo13.vidcla.ventas.notaventa.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mailgrupo13.vidcla.ventas.notaventa.entities.NotaVenta;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleNotaVentaDTO {

    private UUID id;
    private Integer cantidad;
    private BigDecimal precio;
    private LocalDateTime creadoEn;
    private  LocalDateTime actualizadoEn;
    private UUID notaVentaId;
    private UUID parabrisaId;

}
