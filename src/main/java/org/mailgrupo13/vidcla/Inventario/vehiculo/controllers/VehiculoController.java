package org.mailgrupo13.vidcla.Inventario.vehiculo.controllers;


import org.mailgrupo13.vidcla.Inventario.vehiculo.dto.VehiculoDTO;
import org.mailgrupo13.vidcla.Inventario.vehiculo.services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/vehiculo")
public class VehiculoController {

     @Autowired
    private VehiculoService vehiculoService;

    @GetMapping
    public ResponseEntity<List<VehiculoDTO>> findAll(){
        return vehiculoService.findAll();

    }

    @GetMapping("/{id}")
    public VehiculoDTO findById(UUID id) {
        return vehiculoService.findById(id);

    }

    @PostMapping
    public VehiculoDTO create(@RequestBody  VehiculoDTO vehiculoDTO) {
        return vehiculoService.create(vehiculoDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        return vehiculoService.delete(id);
    }

    @PutMapping("/{id}")
    public VehiculoDTO update(@PathVariable UUID id, @RequestBody VehiculoDTO vehiculoDTO) {
        return vehiculoService.update(id, vehiculoDTO);
    }




}
