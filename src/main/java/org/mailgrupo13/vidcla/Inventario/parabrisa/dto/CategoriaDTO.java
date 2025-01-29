package org.mailgrupo13.vidcla.Inventario.parabrisa.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class CategoriaDTO {
    private UUID id;
    private  String nombre;
    private String codigo;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;


    public CategoriaDTO(UUID id, String nombre, String codigo ) {
        this.id=id;
        this.nombre=nombre;
        this.codigo=codigo;

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "CategoriaPDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }


}
