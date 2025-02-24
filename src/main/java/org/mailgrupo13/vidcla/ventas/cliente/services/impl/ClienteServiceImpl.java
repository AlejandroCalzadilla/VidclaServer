package org.mailgrupo13.vidcla.ventas.cliente.services.impl;
import org.mailgrupo13.vidcla.validations.ResourceNotFoundException;
import org.mailgrupo13.vidcla.ventas.cliente.dtos.ClienteDTO;
import org.mailgrupo13.vidcla.ventas.cliente.entitites.Cliente;
import org.mailgrupo13.vidcla.ventas.cliente.mappers.ClienteMapper;
import org.mailgrupo13.vidcla.ventas.cliente.repositories.ClienteRepository;
import org.mailgrupo13.vidcla.ventas.cliente.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private  final ClienteMapper clienteMapper;;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper=clienteMapper;
    }

    @Override
    public ClienteDTO create(ClienteDTO clienteDTO) {
      Cliente cliente = new Cliente();
      cliente=mapToEntity(clienteDTO);
      cliente=clienteRepository.save(cliente);
      return mapToDTO(cliente);
    }

    @Override
    public List<ClienteDTO> findAll() {
       List<Cliente> clientes= clienteRepository.findAll();
       List<ClienteDTO> clienteDTOS = null;
       for (Cliente cliente:clientes){
           clienteDTOS.add(mapToDTO(cliente));
       }
       return clienteDTOS;
    }

    @Override
    public ClienteDTO findById(UUID id) {
        return clienteRepository.findById(id).map(this::mapToDTO)
                .orElseThrow(()-> new ResourceNotFoundException("Cliente con id "+id+" no encontrado"));
    }

    @Override
    public void delete(UUID id) {
        if(!clienteRepository.existsById(id)){
            throw new ResourceNotFoundException("Cliente con id "+id+" no encontrado");
        }
        clienteRepository.deleteById(id);
    }

    @Override
    public ClienteDTO update(UUID id, ClienteDTO clienteDTO) {
        return null;
    }

    @Override
    public ClienteDTO mapToDTO(Cliente cliente) {
        return null;
    }

    @Override
    public Cliente mapToEntity(ClienteDTO clienteDTO) {
        return null;
    }
}
