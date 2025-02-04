package org.mailgrupo13.vidcla.Inventario.parabrisa.services;

import org.mailgrupo13.vidcla.Inventario.parabrisa.dto.ParabrisaDTO;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.CategoriaP;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.Parabrisa;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.PosicionP;
import org.mailgrupo13.vidcla.Inventario.parabrisa.repositories.ParabrisaRepository;
import org.mailgrupo13.vidcla.Inventario.parabrisa.repositories.PosicionPRepository;
import org.mailgrupo13.vidcla.Inventario.vehiculo.services.VehiculoServiceImpl;
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

    @Autowired
    private VehiculoServiceImpl vehiculoService;


    @Override
    public List<ParabrisaDTO> findAll() {
        return parabrisaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }





    @Override
    public ParabrisaDTO findById(UUID id) {
        return parabrisaRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("parabirsa con  id " + id + " no encontrado"));
    }




    @Override
    public ParabrisaDTO create(ParabrisaDTO parabrisaDTO) {
        Parabrisa parabrisa = convertToEntity(parabrisaDTO);
        CategoriaP categoriaP = categoriaPService.convertToEntity(categoriaPService.findById(parabrisaDTO.getCategoriaId()));
        PosicionP posicionP = posicionPService.convertToEntity(posicionPService.findById(parabrisaDTO.getPosicionId()));

        Parabrisa parabrisa2 = parabrisaRepository.save(parabrisa);
        return convertToDTO(parabrisa2);

    }






    @Override
    public ParabrisaDTO update(UUID id, ParabrisaDTO parabrisaDTO) {
        Optional<Parabrisa> existeParabrisa = parabrisaRepository.findById(id);
        if (!existeParabrisa.isPresent()) {
            throw new ResourceNotFoundException("Parabrisa con id " + id + " no encontrado");
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
            Parabrisa p= parabrisaRepository.save(parabrisa);
            return convertToDTO(p);
        }

    }


    @Override
    public ResponseEntity<?> delete(UUID id) {
        Parabrisa parabrisa = parabrisaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Parabrisa con id " + id + " no encontrado"));
        parabrisaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }





    public ParabrisaDTO convertToDTO(Parabrisa parabrisa) {
        ParabrisaDTO p=new ParabrisaDTO(
                parabrisa.getId(),
                parabrisa.getArriba(),
                parabrisa.getAbajo(),
                parabrisa.getMedio(),
                parabrisa.getCostado(),
                parabrisa.getObservacion(),
                parabrisa.getCategoria().getId(),
                parabrisa.getPosicion().getId(),
                parabrisa.getVehiculo().getId()
         );
        p.setActualizadoEn(parabrisa.getActualizadoEn());
        p.setCreadoEn(parabrisa.getCreadoEn());
        return p;
    }


    @Override
    public Parabrisa convertToEntity(ParabrisaDTO windshieldDTO) {
        Parabrisa parabrisa = new Parabrisa();
        parabrisa.setArriba(windshieldDTO.getArriba());
        parabrisa.setAbajo(windshieldDTO.getAbajo());
        parabrisa.setMedio(windshieldDTO.getMedio());
        parabrisa.setCostado(windshieldDTO.getCostado());
        parabrisa.setObservacion(windshieldDTO.getObservacion());
        parabrisa.setCategoria(categoriaPService.convertToEntity(categoriaPService.findById(windshieldDTO.getCategoriaId())));
        parabrisa.setPosicion(posicionPService.convertToEntity(posicionPService.findById(windshieldDTO.getPosicionId())));
        parabrisa.setVehiculo(vehiculoService.convertToEntity(vehiculoService.findById(windshieldDTO.getVehiculoId())));

        return parabrisa;
    }
}