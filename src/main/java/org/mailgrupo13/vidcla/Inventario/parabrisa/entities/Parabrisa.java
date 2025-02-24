package org.mailgrupo13.vidcla.Inventario.parabrisa.entities;

import jakarta.persistence.*;
import org.mailgrupo13.vidcla.Inventario.almacen.entities.AlmacenParabrisa;
import org.mailgrupo13.vidcla.Inventario.vehiculo.entities.Vehiculo;
import org.mailgrupo13.vidcla.compras.notacompra.entities.DetalleNotaCompra;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Parabrisa {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;
    private  float arriba ;
    private  float abajo ;
    private  float costado;
    private float medio;
    private String observacion;
    private BigDecimal precio;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    @ManyToOne
    @JoinColumn(name = "categoriap_id", nullable = false)
    private CategoriaP categoria;

    @ManyToOne
    @JoinColumn(name = "posicionp_id", nullable = false)
    private PosicionP posicion;

    @OneToMany(mappedBy = "parabrisa")
    private List<AlmacenParabrisa> almacenParabrisas;

    @OneToMany(mappedBy = "parabrisa")
    private  List<DetalleNotaCompra> detalleNotaCompras;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;


    @Column
    private String codigo;


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
