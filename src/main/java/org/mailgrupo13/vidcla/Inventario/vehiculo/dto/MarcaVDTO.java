package org.mailgrupo13.vidcla.Inventario.vehiculo.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class MarcaVDTO {

    private UUID id;
    private  String nombre;
    private String pais;
    private Integer codigo;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    public MarcaVDTO(UUID id, String nombre, String pais,Integer codigo) {
        this.id=id;
        this.nombre = nombre;
        this.pais = pais;
        this.codigo=codigo;

    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
