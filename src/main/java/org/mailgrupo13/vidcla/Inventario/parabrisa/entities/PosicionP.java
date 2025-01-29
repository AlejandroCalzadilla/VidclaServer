package org.mailgrupo13.vidcla.Inventario.parabrisa.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class PosicionP {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nombre;
    private String codigo;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    @OneToMany(mappedBy = "posicion")
    private List<Parabrisa> parabrisas;

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
}
