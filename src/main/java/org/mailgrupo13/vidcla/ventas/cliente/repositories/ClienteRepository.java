package org.mailgrupo13.vidcla.ventas.cliente.repositories;

import org.mailgrupo13.vidcla.ventas.cliente.entitites.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {


}
