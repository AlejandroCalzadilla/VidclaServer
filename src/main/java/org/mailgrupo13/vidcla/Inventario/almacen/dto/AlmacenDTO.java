package org.mailgrupo13.vidcla.Inventario.almacen.dto;

import java.util.UUID;

public class AlmacenDTO {
    private UUID id;
    private String nombre;
    private String direccion;
    private String capacidad;

    public AlmacenDTO(UUID id, String nombre, String direccion, String capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidad = capacidad;
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

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }
}
