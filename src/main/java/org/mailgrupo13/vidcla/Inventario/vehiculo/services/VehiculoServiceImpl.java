package org.mailgrupo13.vidcla.Inventario.vehiculo.services;
import org.mailgrupo13.vidcla.Inventario.vehiculo.dto.VehiculoDTO;
import org.mailgrupo13.vidcla.Inventario.vehiculo.entities.MarcaV;
import org.mailgrupo13.vidcla.Inventario.vehiculo.entities.Vehiculo;
import org.mailgrupo13.vidcla.Inventario.vehiculo.repositories.VehiculoRepository;
import org.mailgrupo13.vidcla.imagenes.Imagen;
import org.mailgrupo13.vidcla.imagenes.ImagenDTO;
import org.mailgrupo13.vidcla.imagenes.ImagenRepository;
import org.mailgrupo13.vidcla.imagenes.ImagenService;
import org.mailgrupo13.vidcla.validations.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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


    @Autowired
    private ImagenService imagenService;

    @Autowired
    private ImagenRepository imagenRepository;


    @Override
    public VehiculoDTO findById(UUID id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehiculo con id " + id + " no encontrado"));
        return convertToDTO(vehiculo);
    }





    @Override
    public VehiculoDTO create(VehiculoDTO vehiculoDTO, List<MultipartFile> imagenes) {
        MarcaV marca = marcaVService.convertToEntity(marcaVService.findById(vehiculoDTO.getMarcaId()));
        Vehiculo vehiculo = convertToEntity(vehiculoDTO);
        vehiculo.setMarca(marca);
        Optional<Vehiculo> lastVehiculo = vehiculoRepository.findTopByOrderByCodigoDesc();
        int newCodigo = lastVehiculo.map(v -> Integer.parseInt(v.getCodigo()) + 1).orElse(marca.getCodigo());
        vehiculo.setCodigo(String.valueOf(newCodigo));
        vehiculo = vehiculoRepository.save(vehiculo);


        List<ImagenDTO> imagenesVehiculo =new ArrayList<>();
        // Save images if present
        if (imagenes != null) {
            for (MultipartFile file : imagenes) {
                if (!file.isEmpty()) {
                    Imagen imagen = new Imagen();
                    imagen.setNombre(file.getOriginalFilename());
                    imagen.setVehiculo(vehiculo);
                    imagen.setNombre(imagenService.CargarImagen(file));
                    imagenesVehiculo.add(imagenService.create(imagen));
                }
            }
        }

         VehiculoDTO vehiculoDTO1 = convertToDTO(vehiculo);
         vehiculoDTO1.setImagenes(imagenesVehiculo);

         return vehiculoDTO1;
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

        List<ImagenDTO> imagenesDTO = new ArrayList<>();
        for (Imagen imagen : vehiculo.getImagenes()) {
            imagenesDTO.add(imagenService.convertToDTO(imagen));
        }

        return new VehiculoDTO(
                vehiculo.getId(),
                vehiculo.getDescripcion(),
                vehiculo.getYear_inicio(),
                vehiculo.getYear_fin(),
                vehiculo.getMarca().getId(),
                vehiculo.getCreadoEn(),
                vehiculo.getActualizadoEn(),
                vehiculo.getCodigo(),
                imagenesDTO

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
