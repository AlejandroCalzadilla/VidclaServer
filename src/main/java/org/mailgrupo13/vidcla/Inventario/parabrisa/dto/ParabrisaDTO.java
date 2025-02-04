package org.mailgrupo13.vidcla.Inventario.parabrisa.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;
import java.util.UUID;

public class ParabrisaDTO {

    private UUID id;

    @Min(value = 20, message = "El tamaño  de arriba ser al menos 20 centímetros")
    @Max(value = 300, message = "El tamaño de arriba ser como máximo 300 centímetros")
    private  float arriba ;


    @Min(value = 20, message = "El tamaño de abajo ser al menos 20 centímetros")
    @Max(value = 300, message = "El tamaño de abajo ser como máximo 300 centímetros")
    private  float abajo ;


    @Min(value = 20, message = "El tamaño de costado ser al menos 20 centímetros")
    @Max(value = 300, message = "El tamaño de  costado ser como máximo 300 centímetros")
    private  float costado;


    @Min(value = 20, message = "El tamaño del medio ser al menos 20 centímetros")
    @Max(value = 300, message = "El tamaño del medio ser como máximo 300 centímetros")
    private float medio;


    private String observacion;

    private LocalDateTime creadoEn;

    private LocalDateTime actualizadoEn;


    private UUID categoriaId;

    private  UUID posicionId;

    private  UUID vehiculoId;


    public UUID getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(UUID vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    // Constructor
    public ParabrisaDTO(UUID id, float arriba, float abajo, float costado, float medio, String observacion, UUID categoryw_id, UUID positionw_id, UUID vehiculoId) {
        this.id = id;
        this.arriba = arriba;
        this.abajo = abajo;
        this.costado = costado;
        this.medio = medio;
        this.observacion = observacion;
        this.categoriaId = categoryw_id;
        this.posicionId = positionw_id;
       this.vehiculoId=vehiculoId;


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


    public UUID getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(UUID categoriaId) {
        this.categoriaId = categoriaId;
    }

    public UUID getPosicionId() {
        return posicionId;
    }

    public void setPosicionId(UUID posicionId) {
        this.posicionId = posicionId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public float getArriba() {
        return arriba;
    }

    public void setArriba(float arriba) {
        this.arriba = arriba;
    }

    public float getAbajo() {
        return abajo;
    }

    public void setAbajo(float abajo) {
        this.abajo = abajo;
    }

    public float getCostado() {
        return costado;
    }

    public void setCostado(float costado) {
        this.costado = costado;
    }

    public float getMedio() {
        return medio;
    }

    public void setMedio(float medio) {
        this.medio = medio;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }





    @Override
    public String toString() {
        return "WindshieldDTO{" +
                "id=" + id +
                ", arriba=" + arriba +
                ", abajo=" + abajo +
                ", costado=" + costado +
                ", medio=" + medio +
                ", observacion='" + observacion + '\'' +
                ", creadoEn=" + creadoEn +
                ", actualizadoEn=" + actualizadoEn +
                ", categoriaId=" + categoriaId +
                ", posicionId=" + posicionId +
                ", vehiculoId=" + vehiculoId +
                '}';
    }


}