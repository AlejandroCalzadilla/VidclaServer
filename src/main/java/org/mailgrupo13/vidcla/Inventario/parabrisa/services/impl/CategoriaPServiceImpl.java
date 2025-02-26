package org.mailgrupo13.vidcla.Inventario.parabrisa.services.impl;

import org.mailgrupo13.vidcla.Inventario.parabrisa.dto.CategoriaDTO;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.CategoriaP;
import org.mailgrupo13.vidcla.Inventario.parabrisa.mappers.CategoriaPMapper;
import org.mailgrupo13.vidcla.Inventario.parabrisa.repositories.CategoriaPRepository;
import org.mailgrupo13.vidcla.Inventario.parabrisa.services.CategoriaPService;
import org.mailgrupo13.vidcla.validations.ResourceAlreadyExistsException;
import org.mailgrupo13.vidcla.validations.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class CategoriaPServiceImpl implements CategoriaPService {


    private final CategoriaPRepository repository;
    private final  CategoriaPMapper categoriaPMapper;


    @Autowired
    public CategoriaPServiceImpl(CategoriaPRepository categoriaPRepository, CategoriaPMapper categoriaPMapper) {
        this.repository = categoriaPRepository;
        this.categoriaPMapper = categoriaPMapper;
    }



     @Override
     public List<CategoriaDTO> findAll(){
         List<CategoriaP> categorias= repository.findAll();
         List<CategoriaDTO> categoriasDTO=new ArrayList<>();
         for(CategoriaP categoria:categorias)
             categoriasDTO.add(mapToDTO(categoria));
         return categoriasDTO;
     }


    @Override
    public CategoriaDTO findById(UUID id) {
        CategoriaP categoria= repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("CategoriaP con id " + id + " no encontrada"));
        return  mapToDTO(categoria);
    }




    @Override
    public CategoriaDTO create(CategoriaDTO categoriaPDTO) {
        Optional<CategoriaP> existingCategoria = repository.findByNombre(categoriaPDTO.getNombre());
        if (existingCategoria.isPresent())
            throw new ResourceAlreadyExistsException("CategoriaP con nombre " + categoriaPDTO.getNombre() + " ya existe");
        CategoriaP categoriaP = mapToEntity(categoriaPDTO);
        categoriaP = repository.save(categoriaP);
        return mapToDTO(categoriaP);
    }



    @Override
    public ResponseEntity<Void> delete(UUID id) {
        Optional<CategoriaP> categoriaP = repository.findById(id);
        if (!categoriaP.isPresent())
            throw new ResourceNotFoundException("CategoriaP con id " + id + " no encontrada");
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



    public CategoriaDTO mapToDTO(CategoriaP categoriaP){
       return categoriaPMapper.mapToDTO(categoriaP);
    }

    public CategoriaP mapToEntity(CategoriaDTO categoriaPDTO){
       return categoriaPMapper.mapToEntity(categoriaPDTO);
    }

}
