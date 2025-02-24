package org.mailgrupo13.vidcla.Inventario.almacen.entities;


import jakarta.persistence.*;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.Parabrisa;

import java.util.UUID;

@Entity
@Table(name = "almacenparabrisa")
public class AlmacenParabrisa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String codigo;
    private Integer stock;


    @ManyToOne
    @JoinColumn(name = "parabrisa_id", nullable = false)
    private Parabrisa parabrisa;

    @ManyToOne
    @JoinColumn(name = "almacen_id", nullable = false)
    private Almacen almacen;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Parabrisa getParabrisa() {
        return parabrisa;
    }

    public void setParabrisa(Parabrisa parabrisa) {
        this.parabrisa = parabrisa;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public String toString() {
        return "AlmacenParabrisa{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", stock=" + stock +
                ", parabrisa=" + parabrisa +
                ", almacen=" + almacen +
                '}';
    }
}
