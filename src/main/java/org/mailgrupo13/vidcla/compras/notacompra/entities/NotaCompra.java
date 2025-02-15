package org.mailgrupo13.vidcla.compras.notacompra.entities;


import jakarta.persistence.*;
import org.mailgrupo13.vidcla.Inventario.almacen.entities.Almacen;
import org.mailgrupo13.vidcla.compras.proveedor.Proveedor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class NotaCompra {


    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false)
    private String fecha;

    private BigDecimal total;

   @Column(nullable = false)
    private String estado;


   private LocalDateTime creadoEn;

   private LocalDateTime actualizadoEn;





    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedor proveedor;


    @ManyToOne
    @JoinColumn(name = "almacen_id", nullable = false)
    private Almacen almacen;




    @OneToMany(mappedBy = "notaCompra")
    private List<DetalleNotaCompra> detalleNotaCompra=  new ArrayList<>();;




    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    @PrePersist
    protected void onCreate() {
        this.creadoEn = java.time.LocalDateTime.now();
        this.actualizadoEn = java.time.LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.actualizadoEn = java.time.LocalDateTime.now();
    }




    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }

    public void setActualizadoEn(LocalDateTime actualizadoEn) {
        this.actualizadoEn = actualizadoEn;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<DetalleNotaCompra> getDetalleNotaCompras() {
        return detalleNotaCompra;
    }

    public void setDetalleNotaCompras(List<DetalleNotaCompra> detalleNotaCompras) {
        this.detalleNotaCompra = detalleNotaCompras;
    }

    @Override
    public String toString() {
        return "NotaCompra{" +
                "id=" + id +
                ", numero=" + numero +
                ", fecha='" + fecha + '\'' +
                ", total=" + total +
                ", estado='" + estado + '\'' +
                ", creadoEn=" + creadoEn +
                ", actualizadoEn=" + actualizadoEn +
                ", proveedor=" + proveedor +
                ", almacen=" + almacen +
                ", detalleNotaCompras=" + detalleNotaCompra +
                '}';
    }
}
