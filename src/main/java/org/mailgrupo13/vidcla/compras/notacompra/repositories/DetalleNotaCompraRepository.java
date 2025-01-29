package org.mailgrupo13.vidcla.compras.notacompra.repositories;

import org.mailgrupo13.vidcla.compras.notacompra.entities.DetalleNotaCompra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DetalleNotaCompraRepository extends JpaRepository<DetalleNotaCompra, UUID> {



}
