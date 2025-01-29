package org.mailgrupo13.vidcla.imagenes;

import jakarta.persistence.*;
import org.mailgrupo13.vidcla.Inventario.vehiculo.entities.Vehiculo;

import java.util.UUID;

@Entity
public class Imagen {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String nombre;


    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;


    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
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
}
