package org.mailgrupo13.vidcla.compras.proveedor;


import org.mailgrupo13.vidcla.compras.proveedor.dtos.ProveedorDTO;
import org.mailgrupo13.vidcla.compras.proveedor.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {


    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public List<ProveedorDTO> findAll(){
        return proveedorService.findAll();
    }


    @GetMapping("/{id}")
    public ProveedorDTO FindById(UUID id) {
        return proveedorService.findById(id);
    }



    @PostMapping
    public ProveedorDTO create(@RequestBody  ProveedorDTO proveedorDTO) {
        return proveedorService.create(proveedorDTO);
    }


    @PutMapping("/{id}")
    public ProveedorDTO update(@PathVariable  UUID id,@RequestBody ProveedorDTO proveedorDTO) {
        return proveedorService.update(id, proveedorDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable  UUID id) {
        return proveedorService.delete(id);
    }



}
