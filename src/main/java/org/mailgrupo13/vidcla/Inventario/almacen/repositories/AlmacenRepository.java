package org.mailgrupo13.vidcla.Inventario.almacen.repositories;

import org.mailgrupo13.vidcla.Inventario.almacen.entities.Almacen;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.CategoriaP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AlmacenRepository extends JpaRepository<Almacen, UUID> {


    Optional<Almacen> findByNombre(String nombre);

}
