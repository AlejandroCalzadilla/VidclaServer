package org.mailgrupo13.vidcla.Inventario.vehiculo.repositories;

import org.mailgrupo13.vidcla.Inventario.vehiculo.entities.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface VehiculoRepository extends JpaRepository<Vehiculo, UUID> {


    @Query("SELECT v FROM Vehiculo v WHERE v.marcav.id = :marcaId ORDER BY v.codigo DESC limit 1")
    Optional<Vehiculo> findTopByMarcaVIdOrderByCodigoDesc(@Param("marcaId") UUID marcaId);

}
