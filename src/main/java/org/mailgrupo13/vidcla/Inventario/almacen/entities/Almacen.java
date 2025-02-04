package org.mailgrupo13.vidcla.Inventario.almacen.entities;

import jakarta.persistence.*;
import org.mailgrupo13.vidcla.compras.notacompra.entities.NotaCompra;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Almacen {

 @Id
 @GeneratedValue(strategy = GenerationType.UUID)
 private UUID id;

 @Column(nullable = false)
 private String nombre;

  private String direccion;

  @Column(nullable = false)
  private Integer capacidad;

   private LocalDateTime creadoEn;

   private  LocalDateTime actualizadoEn;

    @OneToMany(mappedBy = "almacen")
    private List<NotaCompra> notasCompra;





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


    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
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
