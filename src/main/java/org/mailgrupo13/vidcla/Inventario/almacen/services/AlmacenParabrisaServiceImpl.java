package org.mailgrupo13.vidcla.Inventario.almacen.services;

import org.mailgrupo13.vidcla.Inventario.almacen.dto.AlmacenDTO;
import org.mailgrupo13.vidcla.Inventario.almacen.dto.AlmacenParabrisaDto;
import org.mailgrupo13.vidcla.Inventario.almacen.entities.Almacen;
import org.mailgrupo13.vidcla.Inventario.almacen.entities.AlmacenParabrisa;
import org.mailgrupo13.vidcla.Inventario.almacen.repositories.AlmacenParabrisaRepository;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.Parabrisa;
import org.mailgrupo13.vidcla.Inventario.parabrisa.services.ParabrisaService;
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
public class AlmacenParabrisaServiceImpl implements AlmacenParabrisaService{


     @Autowired
        private AlmacenParabrisaRepository AlmacenPRepository;

     @Autowired
       private ParabrisaService parabrisaService;
     @Autowired
         private AlmacenService almacenService;


    @Override
    public AlmacenParabrisaDto create(AlmacenParabrisaDto almacenpDTO) {
        Optional<AlmacenParabrisa> existingAlmacenParabrisa = AlmacenPRepository.findByParabrisaIdAndAlmacenId(
                almacenpDTO.getParabrisaId(), almacenpDTO.getAlmacenId());
        if (existingAlmacenParabrisa.isPresent()) {
            throw new ResourceAlreadyExistsException("AlmacenParabrisa with the same parabrisa and almacen already exists");
        }
        AlmacenParabrisa almacenParabrisa = convertToEntity(almacenpDTO);
        almacenParabrisa = AlmacenPRepository.save(almacenParabrisa);
        return convertToDTO(almacenParabrisa);
    }

    @Override
    public AlmacenParabrisaDto findById(UUID id) {
        return AlmacenPRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("AlmacenParabrisa with id " + id + " not found"));
    }

    @Override
    public AlmacenParabrisaDto update(UUID id, AlmacenParabrisaDto almacenPDTO) {

          AlmacenParabrisa almacenParabrisa= AlmacenPRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AlmacenParabrisa with id " + id + " not found"));


        Optional<AlmacenParabrisa> existingAlmacenParabrisa = AlmacenPRepository.findByParabrisaIdAndAlmacenId(
                almacenPDTO.getParabrisaId(), almacenPDTO.getAlmacenId()
        );
        if (existingAlmacenParabrisa.isPresent()) {
            throw new ResourceAlreadyExistsException("AlmacenParabrisa with the same parabrisa and almacen already exists");
        }

        Almacen almacen=almacenService.convertToEntity(almacenService.findById(almacenPDTO.getAlmacenId()));
        Parabrisa parabrisa=parabrisaService.convertToEntity(parabrisaService.findById(almacenPDTO.getParabrisaId()));


        almacenParabrisa.setAlmacen(almacen);
        almacenParabrisa.setParabrisa(parabrisa);
        almacenParabrisa.setCodigo(almacenPDTO.getCodigo());
        almacenParabrisa.setStock(almacenPDTO.getStock());
        almacenParabrisa = AlmacenPRepository.save(almacenParabrisa);
        return convertToDTO(almacenParabrisa);

    }

    @Override
    public ResponseEntity<?> delete(UUID id) {
        return null;
    }

    @Override
    public AlmacenParabrisaDto convertToDTO(AlmacenParabrisa almacenP){
        return new AlmacenParabrisaDto(
                almacenP.getId(),
                almacenP.getStock(),
                almacenP.getCodigo(),
                almacenP.getParabrisa().getId(),
                almacenP.getAlmacen().getId());
    }

    public  AlmacenParabrisa convertToEntity(AlmacenParabrisaDto almacenPDTO){
       AlmacenParabrisa almacenP = new AlmacenParabrisa();
         almacenP.setId(almacenPDTO.getId());
            almacenP.setStock(almacenPDTO.getStock());
            almacenP.setCodigo(almacenPDTO.getCodigo());
            return almacenP;
    }


    @Override
    public AlmacenParabrisaDto findByParabrisaIdAndAlmacenId(UUID parabrisaId, UUID almacenId) {
        AlmacenParabrisa almacenParabrisa = AlmacenPRepository.findByParabrisaIdAndAlmacenId(parabrisaId, almacenId)
                .orElseThrow(() -> new ResourceNotFoundException("AlmacenParabrisa with parabrisaId " + parabrisaId + " and almacenId " + almacenId + " not found"));
        return convertToDTO(almacenParabrisa);
    }

    @Override
    public ResponseEntity<List<AlmacenParabrisaDto>> findAll() {
        List<AlmacenParabrisa> categorias = AlmacenPRepository.findAll();
        if (categorias.isEmpty()) {
            return ResponseEntity.noContent().build(); //204  np content
        }
        List<AlmacenParabrisaDto> categoriasDTO = new ArrayList<>();
        for (AlmacenParabrisa categoria : categorias) {
            categoriasDTO.add(convertToDTO(categoria));
        }
        return ResponseEntity.ok(categoriasDTO);
    }
}
