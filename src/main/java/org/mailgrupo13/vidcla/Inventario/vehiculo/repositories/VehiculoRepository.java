package org.mailgrupo13.vidcla.Inventario.vehiculo.repositories;

import org.mailgrupo13.vidcla.Inventario.vehiculo.entities.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VehiculoRepository extends JpaRepository<Vehiculo, UUID> {


}
