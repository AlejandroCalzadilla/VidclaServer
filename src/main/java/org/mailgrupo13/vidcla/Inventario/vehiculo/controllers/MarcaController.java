package org.mailgrupo13.vidcla.Inventario.vehiculo.controllers;


import org.mailgrupo13.vidcla.Inventario.vehiculo.dto.MarcaVDTO;
import org.mailgrupo13.vidcla.Inventario.vehiculo.services.MarcaVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/marca")
public class MarcaController {


    @Autowired
    private MarcaVService marcaService;



    @GetMapping
    public ResponseEntity<List<MarcaVDTO>> findAll(){
        return marcaService.findAll();

    }

    @PostMapping
    public MarcaVDTO create(@RequestBody MarcaVDTO marcaDTO) {
        System.out.println(marcaDTO.getPais()+ "aver llega el pais");
        return marcaService.create(marcaDTO);

    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable  UUID id ) {
        return marcaService.delete(id);
    }


}
