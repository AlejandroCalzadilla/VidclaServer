package org.mailgrupo13.vidcla.Inventario.almacen.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "almacen")
public class Almacen {

 @Id
 @GeneratedValue(strategy = GenerationType.UUID)
 private UUID id;

 @Column(nullable = false)
 private String nombre;

  private String direccion;

   private LocalDateTime creadoEn;

   private  LocalDateTime actualizadoEn;




    @PrePersist
    protected void onCreate() {
        this.creadoEn = java.time.LocalDateTime.now();
        this.actualizadoEn = java.time.LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.actualizadoEn = java.time.LocalDateTime.now();
    }

    @OneToMany(mappedBy = "almacen")
    private List<AlmacenParabrisa> almacenParabrisas;



  @Column(nullable = false)
    private String capacidad;

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
