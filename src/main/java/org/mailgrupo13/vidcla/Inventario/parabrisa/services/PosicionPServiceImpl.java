package org.mailgrupo13.vidcla.Inventario.parabrisa.services;

import org.mailgrupo13.vidcla.Inventario.parabrisa.dto.PosicionPDTO;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.PosicionP;
import org.mailgrupo13.vidcla.Inventario.parabrisa.repositories.PosicionPRepository;
import org.mailgrupo13.vidcla.validations.ResourceAlreadyExistsException;
import org.mailgrupo13.vidcla.validations.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PosicionPServiceImpl implements  PosicionPService {

    @Autowired
    private PosicionPRepository posicionPRepository;


    @Override
    public PosicionPDTO create(PosicionPDTO posicionPDTO) {
        Optional<PosicionP> existingCategoria = posicionPRepository.findByNombre(posicionPDTO.getNombre());
        if (existingCategoria.isPresent())
            throw new ResourceAlreadyExistsException("Posicion con nombre " + posicionPDTO.getNombre() + " ya existe");

        PosicionP posicionP = convertToEntity(posicionPDTO);
        posicionP.setCreadoEn(LocalDateTime.now());
        posicionP.setActualizadoEn(LocalDateTime.now());
        posicionP = posicionPRepository.save(posicionP);
        return convertToDTO(posicionP);
    }





    @Override
    public ResponseEntity<String> delete(UUID id) {
        Optional<PosicionP> categoriaP = posicionPRepository.findById(id);
        if (categoriaP.isPresent())
            return ResponseEntity.status(200).body("CategoriaP eliminada exitosamente");
         else
            throw new ResourceNotFoundException("CategoriaP con id " + id + " no encontrada");
    }


    @Override
    public PosicionPDTO findById(UUID id) {
        PosicionP categoria = posicionPRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Posicion con id " + id + " no encontrada"));
        return convertToDTO(categoria);
    }

    private PosicionPDTO convertToDTO(PosicionP posicionP) {
        return new PosicionPDTO(
                posicionP.getId(),
                posicionP.getNombre(),
                posicionP.getCodigo(),
                posicionP.getCreadoEn(),
                posicionP.getActualizadoEn()
        );
    }





    @Override
    public  PosicionP convertToEntity(PosicionPDTO posicionPDTO){
        PosicionP categoriaP=new PosicionP();
        categoriaP.setId(posicionPDTO.getId());
        categoriaP.setNombre(posicionPDTO.getNombre());
        categoriaP.setCodigo(posicionPDTO.getCodigo());
        return categoriaP;
    }


}
