package org.mailgrupo13.vidcla.ventas.notaventa.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mailgrupo13.vidcla.ventas.notaventa.entities.DetalleNotaVenta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotaVentaDTO {

    private UUID id;

    private Integer numero;

    private BigDecimal total;

    private UUID clienteId;

    private UUID almacenId;

    private List<DetalleNotaVenta> detalleNotaVenta=  new ArrayList<>();;

    private String estado;

    private LocalDateTime creadoEn;

    private LocalDateTime actualizadoEn;


}
