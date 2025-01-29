package org.mailgrupo13.vidcla.Inventario.parabrisa.entities;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class CategoriaP {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private  String nombre;
    private String codigo;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    @OneToMany(mappedBy = "categoria")
    private List<Parabrisa> parabrisas;



    @PrePersist
    protected void onCreate() {
        this.creadoEn = java.time.LocalDateTime.now();
        this.actualizadoEn = java.time.LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.actualizadoEn = java.time.LocalDateTime.now();
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }



    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }


}
