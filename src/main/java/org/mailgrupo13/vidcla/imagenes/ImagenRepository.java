package org.mailgrupo13.vidcla.imagenes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImagenRepository extends JpaRepository<Imagen, UUID> {
}
