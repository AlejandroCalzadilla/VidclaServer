package org.mailgrupo13.vidcla.Inventario.parabrisa.services.impl;

import org.mailgrupo13.vidcla.Inventario.parabrisa.dto.ParabrisaDTO;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.*;
import org.mailgrupo13.vidcla.Inventario.parabrisa.mappers.ParabrisaMappeer;
import org.mailgrupo13.vidcla.Inventario.parabrisa.repositories.ParabrisaRepository;
import org.mailgrupo13.vidcla.Inventario.parabrisa.services.CategoriaPService;
import org.mailgrupo13.vidcla.Inventario.parabrisa.services.ParabrisaService;
import org.mailgrupo13.vidcla.Inventario.vehiculo.entities.Vehiculo;
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


    private final ParabrisaRepository pRepository;
    private final CategoriaPService categoriaPService;
    private final PosicionPServiceImpl posicionPService;
    private final VehiculoServiceImpl vehiculoService;
    private final ParabrisaMappeer parabrisaMappeer;


    @Autowired
     public ParabrisaServiceImpl(ParabrisaRepository parabrisaRepository, CategoriaPService categoriaPService, PosicionPServiceImpl posicionPService, VehiculoServiceImpl vehiculoService, ParabrisaMappeer parabrisaMappeer) {
        this.pRepository = parabrisaRepository;
        this.categoriaPService = categoriaPService;
        this.posicionPService = posicionPService;
        this.vehiculoService = vehiculoService;
        this.parabrisaMappeer = parabrisaMappeer;
       }



    @Override
    public List<ParabrisaDTO> findAll() {
        return pRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public ParabrisaDTO findById(UUID id) {
        return pRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("parabirsa con  id " + id + " no encontrado"));
    }


    @Override
    public ParabrisaDTO create(ParabrisaDTO parabrisaDTO) {
        Parabrisa parabrisa = mapToEntity(parabrisaDTO);
        CategoriaP categoriaP = categoriaPService.mapToEntity(categoriaPService.findById(parabrisaDTO.getCategoriaId()));
        PosicionP posicionP = posicionPService.mapToEntity(posicionPService.findById(parabrisaDTO.getPosicionId()));
        Vehiculo vehiculo = vehiculoService.convertToEntity(vehiculoService.findById(parabrisaDTO.getVehiculoId()));
        parabrisa.setCodigo(posicionP.getCodigo() + "-" +
                vehiculo.getMarca().getCodigo() +
                categoriaP.getCodigo());
        Parabrisa parabrisa2 = pRepository.save(parabrisa);
        return mapToDTO(parabrisa2);

    }


    @Override
    public ParabrisaDTO update(UUID id, ParabrisaDTO parabrisaDTO) {
        Optional<Parabrisa> existeParabrisa = pRepository.findById(id);
        if (!existeParabrisa.isPresent()) {
            throw new ResourceNotFoundException("Parabrisa con id " + id + " no encontrado");
        } else {
            CategoriaP categoriaP = categoriaPService.mapToEntity(categoriaPService.findById(parabrisaDTO.getCategoriaId()));
            PosicionP posicionP = posicionPService.mapToEntity(posicionPService.findById(parabrisaDTO.getPosicionId()));
            Parabrisa parabrisa = existeParabrisa.get();
            Vehiculo vehiculo = vehiculoService.convertToEntity(vehiculoService.findById(parabrisaDTO.getVehiculoId()));
            parabrisa.setCategoria(categoriaP);
            parabrisa.setPosicion(posicionP);
            parabrisa.setArriba(parabrisaDTO.getArriba());
            parabrisa.setAbajo(parabrisaDTO.getAbajo());
            parabrisa.setMedio(parabrisaDTO.getMedio());
            parabrisa.setCostado(parabrisaDTO.getCostado());
            parabrisa.setObservacion(parabrisaDTO.getObservacion());
            parabrisa.setCodigo(parabrisa.getPosicion().getCodigo() + "-" +
                    parabrisa.getVehiculo().getMarca().getCodigo() +
                    parabrisa.getCategoria().getCodigo());
            Parabrisa p = pRepository.save(parabrisa);
            return mapToDTO(p);
        }

    }


    @Override
    public ResponseEntity<?> delete(UUID id) {
        Parabrisa parabrisa = pRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Parabrisa con id " + id + " no encontrado"));
        pRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ParabrisaDTO mapToDTO(Parabrisa parabrisa) {
        return parabrisaMappeer.mapToDTO(parabrisa);
    }


    @Override
    public Parabrisa mapToEntity(ParabrisaDTO windshieldDTO) {
        return  parabrisaMappeer.mapToEntity(windshieldDTO);
    }



}