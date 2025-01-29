package org.mailgrupo13.vidcla.Inventario.almacen.dto;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.UUID;

public class AlmacenParabrisaDto {
  private UUID id;

  private Integer stock;

  private String codigo;

    public AlmacenParabrisaDto(UUID id, Integer stock, String codigo) {
        this.id = id;
        this.stock = stock;
        this.codigo = codigo;
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
}
