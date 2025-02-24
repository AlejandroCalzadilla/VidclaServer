package org.mailgrupo13.vidcla.Inventario.parabrisa.services;

import org.mailgrupo13.vidcla.Inventario.parabrisa.dto.CategoriaDTO;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.CategoriaP;
import org.mailgrupo13.vidcla.Inventario.parabrisa.repositories.CategoriaPRepository;
import org.mailgrupo13.vidcla.Inventario.parabrisa.services.interfaces.CategoriaPService;
import org.mailgrupo13.vidcla.validations.ResourceAlreadyExistsException;
import org.mailgrupo13.vidcla.validations.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class CategoriaPServiceImpl implements CategoriaPService {


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
     public List<CategoriaDTO> findAll(){
         List<CategoriaP> categorias=categoriaPRepository.findAll();
         List<CategoriaDTO> categoriasDTO=new ArrayList<>();
         for(CategoriaP categoria:categorias){
             categoriasDTO.add(convertToDTO(categoria));
         }
         return categoriasDTO;
     }




    @Override
    public ResponseEntity<?> delete(UUID id) {
        Optional<CategoriaP> categoriaP = categoriaPRepository.findById(id);
        if (categoriaP.isPresent()) {
            categoriaPRepository.deleteById(id);
            return ResponseEntity.ok("CategoriaP eliminada exitosamente");
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
        return new CategoriaDTO(categoriaP.getId(), categoriaP.getNombre(),categoriaP.getCodigo(),categoriaP.getCreadoEn(),categoriaP.getActualizadoEn());
    }





    public CategoriaP convertToEntity(CategoriaDTO categoriaPDTO){
       CategoriaP categoriaP=new CategoriaP();
         categoriaP.setId(categoriaPDTO.getId());
            categoriaP.setNombre(categoriaPDTO.getNombre());
            categoriaP.setCodigo(categoriaPDTO.getCodigo());
        return categoriaP;
    }





}
