package org.mailgrupo13.vidcla.Inventario.almacen.dto;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

public class AlmacenDTO {
    private UUID id;
    private String nombre;
    private String direccion;
    private  Integer capacidad;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;
    public AlmacenDTO(UUID id, String nombre, String direccion, Integer capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidad = capacidad;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }


}
