package org.mailgrupo13.vidcla.Inventario.almacen.dto;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.UUID;

public class AlmacenParabrisaDto {
  private UUID id;

  private Integer stock;

  private String codigo;

  private UUID parabrisaId;

  private  UUID almacenId;

    public AlmacenParabrisaDto(UUID id, Integer stock, String codigo, UUID parabrisaId, UUID almacenId) {
        this.id = id;
        this.stock = stock;
        this.codigo = codigo;
        this.parabrisaId=parabrisaId;
        this.almacenId=almacenId;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public UUID getParabrisaId() {
        return parabrisaId;
    }

    public void setParabrisaId(UUID parabrisaId) {
        this.parabrisaId = parabrisaId;
    }

    public UUID getAlmacenId() {
        return almacenId;
    }

    public void setAlmacenId(UUID almacenId) {
        this.almacenId = almacenId;
    }
}
