package org.mailgrupo13.vidcla.Inventario.parabrisa.entities;

import jakarta.persistence.*;
import org.mailgrupo13.vidcla.Inventario.almacen.entities.AlmacenParabrisa;
import org.mailgrupo13.vidcla.Inventario.vehiculo.entities.Vehiculo;
import org.mailgrupo13.vidcla.compras.notacompra.entities.DetalleNotaCompra;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
public class Parabrisa {



    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;
    private  float arriba ;
    private  float abajo ;
    private  float costado;
    private float medio;
    private String observacion;

    private LocalDateTime creadoEn;


    private LocalDateTime actualizadoEn;

    @ManyToOne
    @JoinColumn(name = "categoriap_id", nullable = false)
    private CategoriaP categoria;

    public List<AlmacenParabrisa> getAlmacenParabrisas() {
        return almacenParabrisas;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void setAlmacenParabrisas(List<AlmacenParabrisa> almacenParabrisas) {
        this.almacenParabrisas = almacenParabrisas;
    }

    @ManyToOne
    @JoinColumn(name = "posicionp_id", nullable = false)
    private PosicionP posicion;

    @OneToMany(mappedBy = "parabrisa")
    private List<AlmacenParabrisa> almacenParabrisas;

    @OneToMany(mappedBy = "parabrisa")
    private  List<DetalleNotaCompra> detalleNotaCompras;


    @ManyToOne
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;





    @PrePersist
    protected void onCreate() {
        this.creadoEn = java.time.LocalDateTime.now();
        this.actualizadoEn = java.time.LocalDateTime.now();
    }




    @PreUpdate
    protected void onUpdate() {
        this.actualizadoEn = java.time.LocalDateTime.now();
    }


    public CategoriaP getCategoria() {
        return this.categoria;
    }

    public void setCategoria(CategoriaP categoria) {
        this.categoria = categoria;
    }

    public PosicionP getPosicion() {
        return posicion;
    }

    public void setPosicion(PosicionP posicion) {
        this.posicion = posicion;
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
