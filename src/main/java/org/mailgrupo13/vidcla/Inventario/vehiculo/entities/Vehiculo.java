package org.mailgrupo13.vidcla.Inventario.vehiculo.entities;


import jakarta.persistence.*;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.Parabrisa;
import org.mailgrupo13.vidcla.imagenes.Imagen;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
public class Vehiculo {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String Descripcion;
    private String year_inicio;
    private String year_fin;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;
    private String codigo;



    @ManyToOne
    @JoinColumn(name = "marcaV_id", nullable = false)
    private MarcaV marcaV;


     @OneToMany(mappedBy = "vehiculo")
    private List<Parabrisa> parabrisas;


    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagen> imagenes =new ArrayList<>();


    @PrePersist
    protected void onCreate() {
        this.creadoEn = java.time.LocalDateTime.now();
        this.actualizadoEn = java.time.LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.actualizadoEn = java.time.LocalDateTime.now();
    }


    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public MarcaV getMarcaV() {
        return marcaV;
    }

    public void setMarcaV(MarcaV marcaV) {
        this.marcaV = marcaV;
    }

    public List<Parabrisa> getParabrisas() {
        return parabrisas;
    }

    public void setParabrisas(List<Parabrisa> parabrisas) {
        this.parabrisas = parabrisas;
    }

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
