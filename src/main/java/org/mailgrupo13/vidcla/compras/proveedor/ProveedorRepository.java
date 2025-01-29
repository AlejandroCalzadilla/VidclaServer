package org.mailgrupo13.vidcla.compras.proveedor;

import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.CategoriaP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProveedorRepository extends JpaRepository<Proveedor, UUID> {

    Optional<Proveedor> findByNombreempresa(String nombreempresa);
}
