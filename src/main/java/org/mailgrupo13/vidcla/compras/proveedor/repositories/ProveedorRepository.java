package org.mailgrupo13.vidcla.compras.proveedor.repositories;

import org.mailgrupo13.vidcla.compras.proveedor.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProveedorRepository extends JpaRepository<Proveedor, UUID> {

     Optional<Proveedor> findByNombreempresa(String nombreempresa);
}
