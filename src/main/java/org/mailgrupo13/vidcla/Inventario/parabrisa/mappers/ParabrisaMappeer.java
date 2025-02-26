package org.mailgrupo13.vidcla.Inventario.parabrisa.mappers;


import org.mailgrupo13.vidcla.Inventario.parabrisa.dto.ParabrisaDTO;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.Parabrisa;
import org.mailgrupo13.vidcla.Inventario.parabrisa.services.impl.PosicionPServiceImpl;
import org.mailgrupo13.vidcla.Inventario.parabrisa.services.CategoriaPService;
import org.mailgrupo13.vidcla.Inventario.vehiculo.services.VehiculoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParabrisaMappeer {

    @Autowired
    private CategoriaPService categoriaPService;

    @Autowired
    private PosicionPServiceImpl posicionPService;

    @Autowired
    private VehiculoServiceImpl vehiculoService;





    public ParabrisaDTO mapToDTO(Parabrisa parabrisa) {
        return new ParabrisaDTO(parabrisa.getId(),
                parabrisa.getArriba(),
                parabrisa.getAbajo(),
                parabrisa.getCostado(),
                parabrisa.getMedio(),
                parabrisa.getObservacion(),
                parabrisa.getCategoria().getId(),
                parabrisa.getPosicion().getId(),
                parabrisa.getVehiculo().getId(),
                parabrisa.getPrecio(),
                parabrisa.getCodigo(),
                parabrisa.getCreadoEn(),
                parabrisa.getActualizadoEn());
    }


    public Parabrisa mapToEntity(ParabrisaDTO windshieldDTO) {
        Parabrisa parabrisa = new Parabrisa();
        parabrisa.setId(windshieldDTO.getId());
        parabrisa.setArriba(windshieldDTO.getArriba());
        parabrisa.setAbajo(windshieldDTO.getAbajo());
        parabrisa.setMedio(windshieldDTO.getMedio());
        parabrisa.setCostado(windshieldDTO.getCostado());
        parabrisa.setObservacion(windshieldDTO.getObservacion());
        parabrisa.setCategoria(categoriaPService.mapToEntity(categoriaPService.findById(windshieldDTO.getCategoriaId())));
        parabrisa.setPosicion(posicionPService.mapToEntity(posicionPService.findById(windshieldDTO.getPosicionId())));
        parabrisa.setVehiculo(vehiculoService.convertToEntity(vehiculoService.findById(windshieldDTO.getVehiculoId())));
        parabrisa.setPrecio(windshieldDTO.getPrecio());
        return parabrisa;
    }

}
