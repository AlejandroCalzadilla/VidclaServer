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
 private Integer cantidad;
 private BigDecimal precio;

 private LocalDateTime creadoEn;
 private  LocalDateTime actualizadoEn;

  @ManyToOne
  @JoinColumn(name = "notacompra_id", nullable = false)
  private NotaCompra notaCompra;

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





    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getCanitdad() {
        return cantidad;
    }

    public void setCanitdad(Integer canitdad) {
        this.cantidad = canitdad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }


    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }



    public NotaCompra getNotaCompra() {
        return notaCompra;
    }

    public void setNotaCompra(NotaCompra notaCompra) {
        this.notaCompra = notaCompra;
    }

    public Parabrisa getParabrisa() {
        return parabrisa;
    }

    public void setParabrisa(Parabrisa parabrisa) {
        this.parabrisa = parabrisa;
    }

    @Override
    public String toString() {
        return "DetalleNotaCompra{" +
                "id=" + id +
                ", canitdad=" + cantidad +
                ", precio=" + precio +
                ", creadoEn=" + creadoEn +
                ", actualizadoEn=" + actualizadoEn +
                ", notaCompra=" + notaCompra +
                ", parabrisa=" + parabrisa +
                '}';
    }
}
