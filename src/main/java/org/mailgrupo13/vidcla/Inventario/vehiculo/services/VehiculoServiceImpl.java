package org.mailgrupo13.vidcla.Inventario.vehiculo.services;
import org.mailgrupo13.vidcla.Inventario.vehiculo.dto.VehiculoDTO;
import org.mailgrupo13.vidcla.Inventario.vehiculo.entities.MarcaV;
import org.mailgrupo13.vidcla.Inventario.vehiculo.entities.Vehiculo;
import org.mailgrupo13.vidcla.Inventario.vehiculo.repositories.VehiculoRepository;
import org.mailgrupo13.vidcla.validations.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private  MarcaVService marcaVService;


    @Override
    public VehiculoDTO findById(UUID id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehiculo con id " + id + " no encontrado"));
        return convertToDTO(vehiculo);
    }





    @Override
    public VehiculoDTO create(VehiculoDTO vehiculoDTO) {
        MarcaV marca=marcaVService.convertToEntity(marcaVService.findById(vehiculoDTO.getMarcaId()));
        Vehiculo vehiculo = convertToEntity(vehiculoDTO);
        vehiculo.setMarca(marca);
        vehiculo.setCreadoEn(LocalDateTime.now());
        vehiculo.setActualizadoEn(LocalDateTime.now());
        vehiculo = vehiculoRepository.save(vehiculo);
        return convertToDTO(vehiculo);
    }

    @Override
    public ResponseEntity<List<VehiculoDTO>> FindAll() {
        return null;
    }


    @Override
    public VehiculoDTO update(UUID id, VehiculoDTO vehiculoDTO) {
        Optional<Vehiculo> existeVehiculo = vehiculoRepository.findById(id);
        if (!existeVehiculo.isPresent()) {
                 throw   new ResourceNotFoundException("Almacen con id " + id + " no encontrado");
        } else {
            MarcaV marca=marcaVService.convertToEntity(marcaVService.findById(vehiculoDTO.getMarcaId()));
            Vehiculo vehiculo = existeVehiculo.get();
            vehiculo.setDescripcion(vehiculoDTO.getDescripcion());
            vehiculo.setYear_inicio(vehiculoDTO.getYear_inicio());
            vehiculo.setYear_fin(vehiculoDTO.getYear_fin());
            vehiculo.setMarca(marca);
            vehiculo.setCreadoEn(existeVehiculo.get().getCreadoEn());
            vehiculo.setActualizadoEn(LocalDateTime.now());
            vehiculo =   vehiculoRepository.save(vehiculo);
            return convertToDTO(vehiculo);
        }
    }




    @Override
    public ResponseEntity<?> delete(UUID id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehiculo con id " + id + " no encontrado"));
        vehiculoRepository.deleteById(id);
        return  ResponseEntity.ok("Vehiculo: "+vehiculo.getDescripcion() + "eliminado");
    }

    @Override
    public ResponseEntity<List<VehiculoDTO>> findAll() {
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();
        if (vehiculos.isEmpty())
            return ResponseEntity.noContent().build(); //204  np content

        List<VehiculoDTO> vehiculosDTO = new ArrayList<>();
        for (Vehiculo vehiculo : vehiculos) {
            vehiculosDTO.add(convertToDTO(vehiculo));
        }
        return ResponseEntity.ok(vehiculosDTO);
    }








    private VehiculoDTO convertToDTO(Vehiculo vehiculo) {

        return new VehiculoDTO(
                vehiculo.getId(),
                vehiculo.getDescripcion(),
                vehiculo.getYear_inicio(),
                vehiculo.getYear_fin(),
                vehiculo.getMarca().getId(),
                vehiculo.getCreadoEn(),
                vehiculo.getActualizadoEn()
                );
    }



    public Vehiculo convertToEntity(VehiculoDTO vehiculoDTO) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(vehiculoDTO.getId());
        vehiculo.setDescripcion(vehiculoDTO.getDescripcion());
        vehiculo.setYear_inicio(vehiculoDTO.getYear_inicio());
        vehiculo.setYear_fin(vehiculoDTO.getYear_fin());
        vehiculo.setCreadoEn(vehiculoDTO.getCreadoEn());
        vehiculo.setActualizadoEn(vehiculoDTO.getActualizadoEn());
        return vehiculo;
    }





}
