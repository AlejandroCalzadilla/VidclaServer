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
import java.util.ArrayList;
import java.util.List;
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
        System.out.println(marcaV.getPais()+ "aver llega el pais servicio");
        marcaV.setCreadoEn(LocalDateTime.now());
        marcaV.setActualizadoEn(LocalDateTime.now());
       MarcaV marca = marcaVRepository.save(marcaV);
        return convertToDTO(marca);


    }


    @Override
    public MarcaVDTO findById(UUID id) {
        MarcaV marcaV= marcaVRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Marca con id " + id + " no encontrada"));
        return  convertToDTO(marcaV);
    }



    @Override
    public ResponseEntity<List<MarcaVDTO>> findAll(){
        List<MarcaV> categorias=marcaVRepository.findAll();
        if(categorias.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<MarcaVDTO> categoriasDTO=new ArrayList<>();
        for(MarcaV categoriaP:categorias){
            categoriasDTO.add(convertToDTO(categoriaP));
        }
        return ResponseEntity.ok(categoriasDTO);
    }


    @Override
    public ResponseEntity<?> delete(UUID id) {
        Optional<MarcaV> existingCategoria = marcaVRepository.findById(id);
        if (!existingCategoria.isPresent()) {
            throw new ResourceNotFoundException("CategoriaP con id " + id + " no encontrada");
        }
        marcaVRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }




    private MarcaVDTO convertToDTO(MarcaV categoriaP){

        return new MarcaVDTO(categoriaP.getId(),
                categoriaP.getNombre(),
                categoriaP.getPais(),
                categoriaP.getCodigo());
    }





    public  MarcaV convertToEntity(MarcaVDTO marcaVDTO){
        MarcaV categoriaP=new MarcaV();
        categoriaP.setId(marcaVDTO.getId());
        categoriaP.setNombre(marcaVDTO.getNombre());
        categoriaP.setPais(marcaVDTO.getPais());
        categoriaP.setCodigo(marcaVDTO.getCodigo());
        return categoriaP;
    }


}
