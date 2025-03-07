package org.mailgrupo13.vidcla.compras.notacompra.controllers;


import org.mailgrupo13.vidcla.compras.notacompra.dtos.NotaCompraDTO;
import org.mailgrupo13.vidcla.compras.notacompra.entities.NotaCompra;
import org.mailgrupo13.vidcla.compras.notacompra.services.interfaces.DetalleNotaCompraService;
import org.mailgrupo13.vidcla.compras.notacompra.services.interfaces.NotaCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notacompra")
public class NotaCompraController {



    @Autowired
    private NotaCompraService notaCompraService;

    @Autowired
    private DetalleNotaCompraService detalleNotaCompraService;


    @GetMapping
    public ResponseEntity<List<NotaCompraDTO>> findAll(){
        return notaCompraService.findAll();
    }


    @PostMapping
    public NotaCompra create(@RequestBody  NotaCompraDTO notaCompraDTO) {
        System.out.println("NotaCompraDTO: " + notaCompraDTO.toString());
        return notaCompraService.create(notaCompraDTO);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable UUID id , @RequestBody NotaCompraDTO notaCompraDTO){
         notaCompraService.update(id,notaCompraDTO);
    }


    /*@PostMapping("/detalle/{id}")
    public DetalleNotaCompraDTO createdetalle(@PathVariable UUID id, @RequestBody DetalleNotaCompraDTO detalleNotaCompraDTO ) {
        //System.o.println("NotaCompraDTO: " + notaCompraDTO.toString());
        return detalleNotaCompraService.create(id, detalleNotaCompraDTO);
    }*/


    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
         notaCompraService.delete(id);
    }


}
