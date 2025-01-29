package org.mailgrupo13.vidcla.Inventario.parabrisa.services;

import org.mailgrupo13.vidcla.Inventario.parabrisa.dto.CategoriaDTO;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.CategoriaP;
import org.mailgrupo13.vidcla.Inventario.parabrisa.repositories.CategoriaPRepository;
import org.mailgrupo13.vidcla.validations.ResourceAlreadyExistsException;
import org.mailgrupo13.vidcla.validations.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
public class CategoriaPServiceImpl implements  CategoriaPService{


    @Autowired
    private CategoriaPRepository categoriaPRepository;


    @Override
    public CategoriaDTO create(CategoriaDTO categoriaPDTO) {
        Optional<CategoriaP> existingCategoria = categoriaPRepository.findByNombre(categoriaPDTO.getNombre());
        if (existingCategoria.isPresent()) {
            throw new ResourceAlreadyExistsException("CategoriaP con nombre " + categoriaPDTO.getNombre() + " ya existe");
        }
        CategoriaP categoriaP = convertToEntity(categoriaPDTO);

        categoriaP = categoriaPRepository.save(categoriaP);
        return convertToDTO(categoriaP);
    }







    @Override
    public ResponseEntity<String> delete(UUID id) {
        Optional<CategoriaP> categoriaP = categoriaPRepository.findById(id);
        if (categoriaP.isPresent()) {
            categoriaPRepository.deleteById(id);
            return ResponseEntity.status(200).body("CategoriaP eliminada exitosamente");
        } else {
            throw new ResourceNotFoundException("CategoriaP con id " + id + " no encontrada");
        }
    }





    @Override
    public CategoriaDTO findById(UUID id) {
         CategoriaP categoria= categoriaPRepository.findById(id)
                 .orElseThrow(()-> new ResourceNotFoundException("CategoriaP con id " + id + " no encontrada"));
         return  convertToDTO(categoria);
    }





    private CategoriaDTO convertToDTO(CategoriaP categoriaP){
        return new CategoriaDTO(categoriaP.getId(), categoriaP.getNombre(),categoriaP.getCodigo());
    }





    public CategoriaP convertToEntity(CategoriaDTO categoriaPDTO){
       CategoriaP categoriaP=new CategoriaP();
         categoriaP.setId(categoriaPDTO.getId());
            categoriaP.setNombre(categoriaPDTO.getNombre());
        return categoriaP;
    }





}
