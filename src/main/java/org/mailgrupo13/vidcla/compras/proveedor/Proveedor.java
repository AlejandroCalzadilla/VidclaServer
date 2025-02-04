package org.mailgrupo13.vidcla.compras.proveedor;


import jakarta.persistence.*;
import org.mailgrupo13.vidcla.compras.notacompra.entities.NotaCompra;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Proveedor {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String nombreempresa;
  private String nombreencargado;
  private String direccion;
  private String  numero;
  private String correo;
 private LocalDateTime creadoEn;
 private LocalDateTime actualizadoEn;

  @OneToMany(mappedBy = "proveedor")
  private List<NotaCompra> notasCompra;


  @PrePersist
  protected void onCreate() {
    this.creadoEn = java.time.LocalDateTime.now();
    this.actualizadoEn = java.time.LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.actualizadoEn = java.time.LocalDateTime.now();
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
