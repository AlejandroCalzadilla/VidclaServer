package org.mailgrupo13.vidcla.Inventario.almacen;


import org.mailgrupo13.vidcla.Inventario.almacen.dto.AlmacenDTO;
import org.mailgrupo13.vidcla.Inventario.almacen.services.AlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/almacen")
public class AlmacenController {


    @Autowired
    private AlmacenService almacenService;


    @GetMapping
    public ResponseEntity<List<AlmacenDTO>> findAll(){
        return almacenService.findAll();

    }


    @GetMapping("/{id}")
    public AlmacenDTO findById(UUID id) {
        return almacenService.findById(id);

    }



    @PostMapping
    public AlmacenDTO create(@RequestBody  AlmacenDTO almacenDTO) {
        System.out.println("AlmacenDTO: "+almacenDTO.getCapacidad()+almacenDTO.getDireccion()+almacenDTO.getNombre());
        return almacenService.create(almacenDTO);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        return almacenService.delete(id);
    }






    @PutMapping("/{id}")
    public AlmacenDTO update(@PathVariable UUID id,@RequestBody AlmacenDTO almacenDTO) {
        return almacenService.update(id, almacenDTO);
    }



}
