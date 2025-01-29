package org.mailgrupo13.vidcla.Inventario.parabrisa.repositories;

import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.Parabrisa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParabrisaRepository extends JpaRepository<Parabrisa, UUID> {
}
