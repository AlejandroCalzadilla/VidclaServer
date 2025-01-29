package org.mailgrupo13.vidcla.Inventario.vehiculo.dto;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.mailgrupo13.vidcla.imagenes.Imagen;
import org.mailgrupo13.vidcla.Inventario.vehiculo.enums.Tipo;
import org.mailgrupo13.vidcla.Inventario.vehiculo.enums.Marca;
public class VehiculoDTO {

    private UUID id;
    private String Descripcion;
    private String year_inicio;
    private String year_fin;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;
    private UUID marcaId;

    //private List<Imagen> imagenes;


    public VehiculoDTO(UUID id, String descripcion, String year_inicio, String year_fin, UUID marca, LocalDateTime creadoEn, LocalDateTime actualizadoEn) {
        this.id = id;
        Descripcion = descripcion;
        this.year_inicio = year_inicio;
        this.year_fin = year_fin;
       this.marcaId=marca;
        this.creadoEn = creadoEn;
        this.actualizadoEn = actualizadoEn;
        //this.imagenes = imagenes;
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

    public UUID getMarcaId() {
        return this.marcaId;
    }

    public void setMaracaId(UUID maracaId) {
        this.marcaId = maracaId;
    }
}
