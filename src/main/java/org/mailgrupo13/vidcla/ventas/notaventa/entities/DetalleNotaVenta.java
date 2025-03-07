package org.mailgrupo13.vidcla.ventas.notaventa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.Parabrisa;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "detalle_nota_venta")
public class DetalleNotaVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Integer cantidad;
    private BigDecimal precio;

    private LocalDateTime creadoEn;
    private  LocalDateTime actualizadoEn;

    @ManyToOne
    @JoinColumn(name = "notaventa_id", nullable = false)
    private NotaVenta notaVenta;

    @ManyToOne
    @JoinColumn(name ="parabrisa_id", nullable = false)
    private Parabrisa parabrisa;


    @PrePersist
    protected void onCreate() {
        this.creadoEn = java.time.LocalDateTime.now();
        this.actualizadoEn = java.time.LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {

        this.actualizadoEn = java.time.LocalDateTime.now();
    }


}
