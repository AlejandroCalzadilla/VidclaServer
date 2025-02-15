package org.mailgrupo13.vidcla.imagenes;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.mailgrupo13.vidcla.Inventario.vehiculo.entities.Vehiculo;

import java.util.UUID;

public class ImagenDTO {

    private UUID id;
    private String nombre;
    private UUID vehiculoId;

    public ImagenDTO(UUID id, String nombre, UUID vehiculoId) {
        this.id = id;
        this.nombre = nombre;
        this.vehiculoId = vehiculoId;
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

    public UUID getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(UUID vehiculoId) {
        this.vehiculoId = vehiculoId;
    }
}
