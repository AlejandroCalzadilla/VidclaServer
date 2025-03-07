package org.mailgrupo13.vidcla.ventas.notaventa.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mailgrupo13.vidcla.Inventario.almacen.entities.Almacen;
import org.mailgrupo13.vidcla.ventas.cliente.entitites.Cliente;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class NotaVenta {

    @Id
    @GeneratedValue(strategy =GenerationType.UUID)
    private UUID id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numero;

    private BigDecimal total;


    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;


    @ManyToOne
    @JoinColumn(name = "almacen_id", nullable = false)
    private Almacen almacen;


    @OneToMany(mappedBy = "notaVenta")
    private List<DetalleNotaVenta> detalleNotaVenta=  new ArrayList<>();;



    private String estado;

    private LocalDateTime creadoEn;

    private LocalDateTime actualizadoEn;



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
