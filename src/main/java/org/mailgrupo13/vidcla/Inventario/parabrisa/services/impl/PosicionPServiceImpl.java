package org.mailgrupo13.vidcla.Inventario.parabrisa.services.impl;

import org.mailgrupo13.vidcla.Inventario.parabrisa.dto.PosicionPDTO;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.PosicionP;
import org.mailgrupo13.vidcla.Inventario.parabrisa.mappers.PosicionPMapper;
import org.mailgrupo13.vidcla.Inventario.parabrisa.repositories.PosicionPRepository;
import org.mailgrupo13.vidcla.Inventario.parabrisa.services.PosicionPService;
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
public class PosicionPServiceImpl implements PosicionPService {

    private final PosicionPRepository posicionPRepository;

    private  final PosicionPMapper posicionPMapper;



    @Autowired
    public PosicionPServiceImpl(PosicionPRepository posicionPRepository, PosicionPMapper posicionPMapper) {
        this.posicionPRepository = posicionPRepository;
        this.posicionPMapper = posicionPMapper;
    }

    @Override
    public List<PosicionPDTO> findAll(){
        List<PosicionP> categorias=posicionPRepository.findAll();
        List<PosicionPDTO> posicionPDTO=new ArrayList<>();
        for(PosicionP posicionP:categorias){
            posicionPDTO.add(mapToDTO(posicionP));
        }
        return posicionPDTO;
    }


    @Override
    public PosicionPDTO findById(UUID id) {
        PosicionP categoria = posicionPRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Posicion con id " + id + " no encontrada"));
        return mapToDTO(categoria);
    }


    @Override
    public PosicionPDTO create(PosicionPDTO posicionPDTO) {
        Optional<PosicionP> existingCategoria = posicionPRepository.findByNombre(posicionPDTO.getNombre());
        if (existingCategoria.isPresent())
            throw new ResourceAlreadyExistsException("Posicion con nombre " + posicionPDTO.getNombre() + " ya existe");

        PosicionP posicionP = mapToEntity(posicionPDTO);
        posicionP.setCreadoEn(LocalDateTime.now());
        posicionP.setActualizadoEn(LocalDateTime.now());
        posicionP = posicionPRepository.save(posicionP);
        return mapToDTO(posicionP);
    }





    @Override
    public ResponseEntity<?> delete(UUID id) {
        Optional<PosicionP> categoriaP = posicionPRepository.findById(id);
        if (categoriaP.isPresent())
            return ResponseEntity.noContent().build();
         else
            throw new ResourceNotFoundException("CategoriaP con id " + id + " no encontrada");
    }








    @Override
    public PosicionPDTO mapToDTO(PosicionP posicionP) {
        return new PosicionPDTO(
                posicionP.getId(),
                posicionP.getNombre(),
                posicionP.getCodigo(),
                posicionP.getCreadoEn(),
                posicionP.getActualizadoEn()
        );
    }





    @Override
    public  PosicionP mapToEntity(PosicionPDTO posicionPDTO){
        PosicionP categoriaP=new PosicionP();
        categoriaP.setId(posicionPDTO.getId());
        categoriaP.setNombre(posicionPDTO.getNombre());
        categoriaP.setCodigo(posicionPDTO.getCodigo());
        return categoriaP;
    }


}
