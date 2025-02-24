package org.mailgrupo13.vidcla.ventas.cliente.mappers;

import org.mailgrupo13.vidcla.ventas.cliente.dtos.ClienteDTO;
import org.mailgrupo13.vidcla.ventas.cliente.entitites.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {


    public ClienteDTO mapToClienteDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getCi(),
                cliente.getTelefono(),
                cliente.getDireccion(),
                cliente.getEmail(),
                cliente.getNit()

        );
    }

    public Cliente mapToCliente(ClienteDTO profesorDTO) {
        return new Cliente(
                profesorDTO.getId(),
                profesorDTO.getNombre(),
                profesorDTO.getApellido(),
                profesorDTO.getCi(),
                profesorDTO.getTelefono(),
                profesorDTO.getDireccion(),
                profesorDTO.getEmail(),
                profesorDTO.getNit()
        );
     }


}
