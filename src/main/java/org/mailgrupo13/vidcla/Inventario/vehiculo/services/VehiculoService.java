package org.mailgrupo13.vidcla.Inventario.vehiculo.services;
import org.mailgrupo13.vidcla.Inventario.vehiculo.dto.VehiculoDTO;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.UUID;

public interface VehiculoService {
         VehiculoDTO findById(UUID id);
         VehiculoDTO create(VehiculoDTO vehiculoDTO);
         ResponseEntity<List<VehiculoDTO>> FindAll();
         VehiculoDTO update(UUID id, VehiculoDTO vehiculoDTO);
         ResponseEntity<?> delete(UUID id);
         ResponseEntity<List<VehiculoDTO>> findAll();


}
