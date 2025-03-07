package org.mailgrupo13.vidcla.ventas.notaventa.repositories;

import org.mailgrupo13.vidcla.compras.notacompra.entities.DetalleNotaCompra;
import org.mailgrupo13.vidcla.ventas.notaventa.entities.DetalleNotaVenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DetalleNotaVentaRepository extends JpaRepository<DetalleNotaVenta, UUID> {


    Optional<DetalleNotaCompra> findByParabrisaId(UUID id);
}
