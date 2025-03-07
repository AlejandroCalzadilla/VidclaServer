package org.mailgrupo13.vidcla.ventas.notaventa.repositories;

import org.mailgrupo13.vidcla.compras.notacompra.entities.NotaCompra;
import org.mailgrupo13.vidcla.ventas.notaventa.entities.NotaVenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface NotaVentaRepository extends JpaRepository<NotaVenta, UUID> {

    Optional<NotaVenta> findByNumero(Integer numero);

}
