package org.mailgrupo13.vidcla.Inventario.vehiculo.services;

import org.mailgrupo13.vidcla.Inventario.vehiculo.dto.MarcaVDTO;
import org.mailgrupo13.vidcla.Inventario.vehiculo.entities.MarcaV;
import org.mailgrupo13.vidcla.Inventario.vehiculo.repositories.MarcaVRepository;
import org.mailgrupo13.vidcla.validations.ResourceAlreadyExistsException;
import org.mailgrupo13.vidcla.validations.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class MarcaVServiceImpl implements  MarcaVService{

    @Autowired
    private  MarcaVRepository marcaVRepository;


    @Override
    public MarcaVDTO create(MarcaVDTO marcaVDTO) {;

        Optional<MarcaV> existingCategoria = marcaVRepository.findByNombre(marcaVDTO.getNombre());
        if (existingCategoria.isPresent()) {
            throw new ResourceAlreadyExistsException("CategoriaP con nombre " + marcaVDTO.getNombre() + " ya existe");
        }
        MarcaV marcaV = convertToEntity(marcaVDTO);
        marcaV.setCreadoEn(LocalDateTime.now());
        marcaV.setActualizadoEn(LocalDateTime.now());
        marcaV = marcaVRepository.save(marcaV);
        return convertToDTO(marcaV);


    }


    @Override
    public MarcaVDTO findById(UUID id) {
        MarcaV marcaV= marcaVRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Marca con id " + id + " no encontrada"));
        return  convertToDTO(marcaV);
    }



    @Override
    public ResponseEntity<String> delete(UUID id) {
        Optional<MarcaV> categoriaP = marcaVRepository.findById(id);
        if (categoriaP.isPresent()) {
            marcaVRepository.deleteById(id);
            return ResponseEntity.status(200).body("Marca de Vehiculo eliminada exitosamente");
        } else {
            throw new ResourceNotFoundException("Marca con id " + id + " no encontrada");
        }
    }




    private MarcaVDTO convertToDTO(MarcaV categoriaP){
        return new MarcaVDTO(categoriaP.getId(), categoriaP.getNombre(),categoriaP.getPais());
    }





    public  MarcaV convertToEntity(MarcaVDTO marcaVDTO){
        MarcaV categoriaP=new MarcaV();
        categoriaP.setId(marcaVDTO.getId());
        categoriaP.setNombre(marcaVDTO.getNombre());
        categoriaP.setPais(marcaVDTO.getPais());
        return categoriaP;
    }


}
