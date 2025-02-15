package org.mailgrupo13.vidcla.compras.notacompra.dtos;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class NotaCompraDTO {

    private UUID id;

    private Integer numero;

    private String fecha;

    private BigDecimal total;

    private String estado;

    private UUID proveedorId;

    private UUID almacenId;

    private String actualizadoEn;

    private String creadoEn;

    private List<DetalleNotaCompraDTO> detalleNotaCompraDTO;

    public NotaCompraDTO(UUID id, Integer numero, String fecha, BigDecimal total, String estado, UUID proveedorId, UUID almacenId, List<DetalleNotaCompraDTO> detalleNotaCompraDTO) {
        this.id = id;
        this.numero = numero;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.proveedorId = proveedorId;
        this.almacenId = almacenId;
        this.detalleNotaCompraDTO = detalleNotaCompraDTO;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public UUID getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(UUID proveedorId) {
        this.proveedorId = proveedorId;
    }

    public UUID getAlmacenId() {
        return almacenId;
    }

    public void setAlmacenId(String almacenId) {
        almacenId = almacenId;
    }

    public String getActualizadoEn() {
        return actualizadoEn;
    }



    public String getCreadoEn() {
        return creadoEn;
    }

    public void setAlmacenId(UUID almacenId) {
        this.almacenId = almacenId;
    }

    public void setActualizadoEn(String actualizadoEn) {
        this.actualizadoEn = actualizadoEn;
    }

    public void setCreadoEn(String creadoEn) {
        this.creadoEn = creadoEn;
    }

    public List<DetalleNotaCompraDTO> getDetalleNotaCompraDTO() {
        return detalleNotaCompraDTO;
    }

    public void setDetalleNotaCompraDTO(List<DetalleNotaCompraDTO> detalleNotaCompraDTO) {
        this.detalleNotaCompraDTO = detalleNotaCompraDTO;
    }

    @Override
    public String toString() {
        return "NotaCompraDTO{" +
                "id=" + id +
                ", numero=" + numero +
                ", fecha='" + fecha + '\'' +
                ", total=" + total +
                ", estado='" + estado + '\'' +
                ", proveedorId=" + proveedorId +
                ", almacenId=" + almacenId +
                ", actualizadoEn='" + actualizadoEn + '\'' +
                ", creadoEn='" + creadoEn + '\'' +
                ", detalleNotaCompraDTO=" + detalleNotaCompraDTO +
                '}';
    }
}
