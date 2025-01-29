package org.mailgrupo13.vidcla.Inventario.parabrisa.services;

import org.mailgrupo13.vidcla.Inventario.parabrisa.dto.ParabrisaDTO;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.CategoriaP;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.Parabrisa;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.PosicionP;
import org.mailgrupo13.vidcla.Inventario.parabrisa.repositories.ParabrisaRepository;
import org.mailgrupo13.vidcla.Inventario.parabrisa.repositories.PosicionPRepository;
import org.mailgrupo13.vidcla.validations.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ParabrisaServiceImpl implements ParabrisaService {



    @Autowired
    private ParabrisaRepository parabrisaRepository;

   @Autowired
   private CategoriaPService categoriaPService;

   @Autowired
   private PosicionPRepository posicionPRepository;
    @Autowired
    private PosicionPServiceImpl posicionPService;


    @Override
    public List<ParabrisaDTO> findAll() {
        return parabrisaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }





    @Override
    public Optional<ParabrisaDTO> findById(UUID id) {
        return Optional.ofNullable(parabrisaRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("parabirsa con  id " + id + " no encontrado")));
    }




    @Override
    public ParabrisaDTO create(ParabrisaDTO parabrisaDTO) {
        Parabrisa parabrisa = convertToEntity(parabrisaDTO);
        CategoriaP categoriaP = categoriaPService.convertToEntity(categoriaPService.findById(parabrisaDTO.getCategoriaId()));
        PosicionP posicionP = posicionPService.convertToEntity(posicionPService.findById(parabrisaDTO.getPosicionId()));
        parabrisa.setCategoria(categoriaP);
        parabrisa.setPosicion(posicionP);
        parabrisa = parabrisaRepository.save(parabrisa);
        return convertToDTO(parabrisa);
    }






    @Override
    public ResponseEntity<String> update(UUID id, ParabrisaDTO parabrisaDTO) {
        Optional<Parabrisa> existeParabrisa = parabrisaRepository.findById(id);
        if (!existeParabrisa.isPresent()) {
            return ResponseEntity.badRequest().body("Parabrisa con id " + id + " no existe");
        }
        else {
            CategoriaP categoriaP=categoriaPService.convertToEntity(categoriaPService.findById(parabrisaDTO.getCategoriaId()));
            PosicionP posicionP = posicionPService.convertToEntity(posicionPService.findById(parabrisaDTO.getPosicionId()));
            Parabrisa parabrisa = existeParabrisa.get();
            parabrisa.setCategoria(categoriaP);
            parabrisa.setPosicion(posicionP);
            parabrisa.setArriba(parabrisaDTO.getArriba());
            parabrisa.setAbajo(parabrisaDTO.getAbajo());
            parabrisa.setMedio(parabrisaDTO.getMedio());
            parabrisa.setCostado(parabrisaDTO.getCostado());
            parabrisa.setObservacion(parabrisaDTO.getObservacion());
            parabrisaRepository.save(parabrisa);
            return ResponseEntity.ok("Parabrisa con  id " + id + " a sido actulizado");
        }
    }


    @Override
    public ResponseEntity<String> delete(UUID id) {
        parabrisaRepository.deleteById(id);
        return ResponseEntity.ok("Parabrisa " + id + "a sido eliminado");
    }






    private ParabrisaDTO convertToDTO(Parabrisa parabrisa) {
        return new ParabrisaDTO(
                parabrisa.getId(),
                parabrisa.getArriba(),
                parabrisa.getAbajo(),
                parabrisa.getMedio(),
                parabrisa.getCostado(),
                parabrisa.getObservacion(),
                parabrisa.getCategoria().getId(),
                parabrisa.getPosicion().getId(),
                parabrisa.getCreadoEn(),
                parabrisa.getActualizadoEn()
        );
    }



    private Parabrisa convertToEntity(ParabrisaDTO windshieldDTO) {
        Parabrisa parabrisa = new Parabrisa();
        parabrisa.setArriba(windshieldDTO.getArriba());
        parabrisa.setAbajo(windshieldDTO.getAbajo());
        parabrisa.setMedio(windshieldDTO.getMedio());
        parabrisa.setCostado(windshieldDTO.getCostado());
        parabrisa.setObservacion(windshieldDTO.getObservacion());
        return parabrisa;
    }
}