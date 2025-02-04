package org.mailgrupo13.vidcla.Inventario.almacen.repositories;

import org.mailgrupo13.vidcla.Inventario.almacen.entities.AlmacenParabrisa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AlmacenParabrisaRepository  extends JpaRepository<AlmacenParabrisa, UUID> {

    Optional<AlmacenParabrisa> findByParabrisaIdAndAlmacenId(UUID parabrisaId, UUID almacenId);

}
