package org.mailgrupo13.vidcla.Inventario.vehiculo.entities;


import jakarta.persistence.*;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.Parabrisa;
import org.mailgrupo13.vidcla.imagenes.Imagen;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class MarcaV {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private  String nombre;
    private String pais;
    private Integer codigo;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    @OneToMany(mappedBy = "marcaV")
    private List<Vehiculo> parabrisas;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public List<Vehiculo> getParabrisas() {
        return parabrisas;
    }

    public void setParabrisas(List<Vehiculo> parabrisas) {
        this.parabrisas = parabrisas;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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
