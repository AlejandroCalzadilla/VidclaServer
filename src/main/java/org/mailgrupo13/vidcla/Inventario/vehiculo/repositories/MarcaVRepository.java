package org.mailgrupo13.vidcla.Inventario.vehiculo.repositories;

import org.mailgrupo13.vidcla.Inventario.vehiculo.entities.MarcaV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MarcaVRepository extends JpaRepository<MarcaV, UUID> {

    Optional<MarcaV> findByNombre(String nombre);

}
