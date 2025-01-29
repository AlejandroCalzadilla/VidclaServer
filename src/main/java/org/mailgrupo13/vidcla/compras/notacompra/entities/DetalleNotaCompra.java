package org.mailgrupo13.vidcla.compras.notacompra.entities;


import jakarta.persistence.*;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.Parabrisa;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "detalle_nota_compra")
public class DetalleNotaCompra {
 @Id
 @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
 private Integer canitdad;
 private BigDecimal precio;

 private LocalDateTime creadoEn;
 private  LocalDateTime actualizadoEn;

  @ManyToOne
  @JoinColumn(name = "notacompra_id", nullable = false)
    private NotaCompra notaCompra;

  @ManyToOne
    @JoinColumn(name ="parabrisa_id", nullable = false)
    private Parabrisa parabrisa;



}
