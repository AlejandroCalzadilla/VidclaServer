package org.mailgrupo13.vidcla.ventas.cliente.services;

import org.mailgrupo13.vidcla.compras.proveedor.Proveedor;
import org.mailgrupo13.vidcla.ventas.cliente.dtos.ClienteDTO;
import org.mailgrupo13.vidcla.ventas.cliente.entitites.Cliente;
import java.util.List;
import java.util.UUID;

public interface ClienteService {

     ClienteDTO create(ClienteDTO  clienteDTO);
     List<ClienteDTO> findAll();
     ClienteDTO findById(UUID id);
     void delete(UUID id);
     ClienteDTO update(UUID id, ClienteDTO clienteDTO);
     ClienteDTO mapToDTO(Cliente cliente);
     Cliente mapToEntity(ClienteDTO clienteDTO);

}
