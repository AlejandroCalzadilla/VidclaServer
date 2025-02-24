package org.mailgrupo13.vidcla.Inventario.parabrisa.controllers;


import org.mailgrupo13.vidcla.Inventario.parabrisa.dto.CategoriaDTO;
import org.mailgrupo13.vidcla.Inventario.parabrisa.services.interfaces.CategoriaPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categoriap")
public class CategoriaController {

    @Autowired
    private CategoriaPService categoriaService;



    @GetMapping
    public List<CategoriaDTO> findAll(){
        return categoriaService.findAll();
    }

    @GetMapping("/{id}")
    public CategoriaDTO FindById(@PathVariable  UUID id) {
        return categoriaService.findById(id); }


    @PostMapping
    public CategoriaDTO create(@RequestBody CategoriaDTO categoriaPDTO) {
        return categoriaService.create(categoriaPDTO); }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable  UUID id) {
        return  categoriaService.delete(id);

    }





}
