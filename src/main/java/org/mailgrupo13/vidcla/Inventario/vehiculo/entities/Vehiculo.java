package org.mailgrupo13.vidcla.Inventario.vehiculo.entities;


import jakarta.persistence.*;
import org.mailgrupo13.vidcla.Inventario.vehiculo.enums.Tipo;
import org.mailgrupo13.vidcla.Inventario.vehiculo.enums.Marca;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
public class Vehiculo {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String Descripcion;
    private String year_inicio;
    private String year_fin;
    private Tipo Tipo;
    private Marca Marca;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;




    @ManyToOne
    @JoinColumn(name = "marcaV_id", nullable = false)
    private MarcaV marcaV;




    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getYear_inicio() {
        return year_inicio;
    }

    public void setYear_inicio(String year_inicio) {
        this.year_inicio = year_inicio;
    }

    public String getYear_fin() {
        return year_fin;
    }

    public void setYear_fin(String year_fin) {
        this.year_fin = year_fin;
    }



    public MarcaV getMarca() {
        return this.marcaV;
    }

    public void setMarca(MarcaV marca) {
        this.marcaV = marca;
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


}
