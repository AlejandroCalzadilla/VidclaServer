package org.mailgrupo13.vidcla.compras.proveedor.services;

import org.apache.coyote.Response;
import org.mailgrupo13.vidcla.compras.proveedor.dtos.ProveedorDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ProveedorService {


    public ProveedorDTO create( ProveedorDTO  proveedorDTO);
    public List<ProveedorDTO> findAll();
    public ProveedorDTO findById(UUID id);
    public ResponseEntity<String> delete(UUID id);
    public  ResponseEntity<String> update(UUID id, ProveedorDTO proveedorDTO);


}
