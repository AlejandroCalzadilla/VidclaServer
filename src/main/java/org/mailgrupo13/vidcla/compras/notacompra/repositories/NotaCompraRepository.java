package org.mailgrupo13.vidcla.compras.notacompra.repositories;

import org.mailgrupo13.vidcla.Inventario.almacen.entities.Almacen;
import org.mailgrupo13.vidcla.compras.notacompra.entities.NotaCompra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface NotaCompraRepository extends JpaRepository<NotaCompra, UUID> {
    Optional<NotaCompra> findByNumero(Integer numero);

}
