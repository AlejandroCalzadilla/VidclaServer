package org.mailgrupo13.vidcla.Inventario.vehiculo.services;

import org.mailgrupo13.vidcla.Inventario.vehiculo.dto.VehiculoDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface VehiculoService {
    public VehiculoDTO FindById(UUID id);
    public VehiculoDTO Create(VehiculoDTO vehiculoDTO);
    public List<VehiculoDTO> FindAll();


    public ResponseEntity<String> Update(UUID id, VehiculoDTO vehiculoDTO);
    public ResponseEntity<String> Delete(UUID id);



}
