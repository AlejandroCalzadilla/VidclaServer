package org.mailgrupo13.vidcla.compras.notacompra.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class DetalleNotaCompraDTO {


    private UUID id;
    private Integer canitdad;
    private BigDecimal precio;

    private LocalDateTime creadoEn;
    private  LocalDateTime actualizadoEn;

    private UUID parabrisaId;
    private UUID notaCompraId;

    public DetalleNotaCompraDTO(UUID id, Integer canitdad, BigDecimal precio, UUID parabrisaId, UUID notaCompraId) {
        this.id = id;
        this.canitdad = canitdad;
        this.precio = precio;
        this.parabrisaId = parabrisaId;
        this.notaCompraId = notaCompraId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getCanitdad() {
        return canitdad;
    }

    public void setCanitdad(Integer canitdad) {
        this.canitdad = canitdad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
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

    public UUID getParabrisadId() {
        return parabrisaId;
    }

    public void setProductoId(UUID productoId) {
        this.parabrisaId = productoId;
    }

    public UUID getNotaCompraId() {
        return notaCompraId;
    }

    public void setNotaCompraId(UUID notaCompraId) {
        this.notaCompraId = notaCompraId;
    }
}
