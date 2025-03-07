package org.mailgrupo13.vidcla.ventas.cliente.controlllers;


import org.mailgrupo13.vidcla.ventas.cliente.dtos.ClienteDTO;
import org.mailgrupo13.vidcla.ventas.cliente.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {


    @Autowired
    ClienteService clienteService;

    @GetMapping("")
    public List<ClienteDTO> findAll(){
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public ClienteDTO findById(UUID id){
        return clienteService.findById(id);
    }

    @PostMapping("")
    public ClienteDTO create(@RequestBody ClienteDTO clienteDTO){
        System.out.println("llega a cliente create"+clienteDTO.toString());
        return clienteService.create(clienteDTO);
    }

    @PutMapping("/{id}")
    public ClienteDTO update(UUID id, ClienteDTO clienteDTO){
        return clienteService.update(id,clienteDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(UUID id){
        clienteService.delete(id);
    }

}
