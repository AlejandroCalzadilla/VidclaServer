package org.mailgrupo13.vidcla.Inventario.parabrisa;

import org.mailgrupo13.vidcla.Inventario.parabrisa.dto.PosicionPDTO;
import org.mailgrupo13.vidcla.Inventario.parabrisa.services.PosicionPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/posicionp")
@Validated
public class PosicionController {


    @Autowired
    private PosicionPService posicionPService;



    @GetMapping("/{id}")
    public PosicionPDTO FindById(@PathVariable UUID id) {
        return posicionPService.findById(id);

    }


    @PostMapping
    public PosicionPDTO create(@RequestBody PosicionPDTO categoriaPDTO) {
        return posicionPService.create(categoriaPDTO);
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable  UUID id) {
        return posicionPService.delete(id).getBody();
    }

}
