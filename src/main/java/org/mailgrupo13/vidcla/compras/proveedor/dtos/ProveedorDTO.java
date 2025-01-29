package org.mailgrupo13.vidcla.compras.proveedor.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProveedorDTO {

    private UUID id;

    private String nombreempresa;
    private String nombreencargado;
    private String direccion;
    private String  numero;
    private String correo;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    public ProveedorDTO(UUID id, String nombreempresa, String nombreencargado, String direccion, String numero, String correo) {
        this.id = id;
        this.nombreempresa = nombreempresa;
        this.nombreencargado = nombreencargado;
        this.direccion = direccion;
        this.numero = numero;
        this.correo = correo;
    }

    public UUID getId() {

        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombreempresa() {
        return nombreempresa;
    }

    public void setNombreempresa(String nombreempresa) {
        this.nombreempresa = nombreempresa;
    }

    public String getNombreencargado() {
        return nombreencargado;
    }

    public void setNombreencargado(String nombreencargado) {
        this.nombreencargado = nombreencargado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }
}
