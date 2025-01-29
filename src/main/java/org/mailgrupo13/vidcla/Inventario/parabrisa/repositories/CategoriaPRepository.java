package org.mailgrupo13.vidcla.Inventario.parabrisa.repositories;

import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.CategoriaP;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.PosicionP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoriaPRepository  extends JpaRepository<CategoriaP, UUID> {

    Optional<CategoriaP> findByNombre(String nombre);



}
