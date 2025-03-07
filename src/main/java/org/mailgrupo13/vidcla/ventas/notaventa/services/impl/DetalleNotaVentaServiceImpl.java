package org.mailgrupo13.vidcla.ventas.notaventa.services.impl;

import org.mailgrupo13.vidcla.Inventario.almacen.dto.AlmacenParabrisaDto;
import org.mailgrupo13.vidcla.Inventario.almacen.services.AlmacenParabrisaService;
import org.mailgrupo13.vidcla.Inventario.almacen.services.AlmacenService;
import org.mailgrupo13.vidcla.Inventario.parabrisa.dto.ParabrisaDTO;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.Parabrisa;
import org.mailgrupo13.vidcla.Inventario.parabrisa.services.ParabrisaService;
import org.mailgrupo13.vidcla.compras.notacompra.repositories.NotaCompraRepository;
import org.mailgrupo13.vidcla.validations.ResourceNotFoundException;
import org.mailgrupo13.vidcla.ventas.notaventa.dtos.DetalleNotaVentaDTO;
import org.mailgrupo13.vidcla.ventas.notaventa.entities.DetalleNotaVenta;
import org.mailgrupo13.vidcla.ventas.notaventa.entities.NotaVenta;
import org.mailgrupo13.vidcla.ventas.notaventa.repositories.DetalleNotaVentaRepository;
import org.mailgrupo13.vidcla.ventas.notaventa.services.DetalleNotaVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class DetalleNotaVentaServiceImpl implements DetalleNotaVentaService {


    private DetalleNotaVentaRepository dettallerepo;
    private AlmacenService almacenService;
    private ParabrisaService parabrisaService;
    private NotaCompraRepository notaCompraRepository;
    private AlmacenParabrisaService almacenParabrisaService;


    @Autowired
    public DetalleNotaVentaServiceImpl(DetalleNotaVentaRepository dettallerepo,
                                  AlmacenService almacenService, ParabrisaService parabrisaService,
                                  NotaCompraRepository notaCompraRepository,
                                  AlmacenParabrisaService almacenParabrisaService)
    {
        this.dettallerepo = dettallerepo;
        this.almacenService = almacenService;
        this.parabrisaService = parabrisaService;
        this.notaCompraRepository = notaCompraRepository;
        this.almacenParabrisaService = almacenParabrisaService;

    }



    @Override
    public List<DetalleNotaVentaDTO> findAll() {
        List<DetalleNotaVenta> categorias = dettallerepo.findAll();
        List<DetalleNotaVentaDTO> categoriasDTO = new ArrayList<>();
        for (DetalleNotaVenta categoria : categorias) {
            categoriasDTO.add(mapToDTO(categoria));
        }
        return categoriasDTO;
    }


    @Override
    public DetalleNotaVentaDTO findById(UUID id) {
        return dettallerepo.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Almacen con id " + id + " no encontrado"));
    }






    @Override
    public void create(NotaVenta notaCompra, DetalleNotaVentaDTO detalleNotaCompraDTO) {
        DetalleNotaVenta detalle = mapToEntity(detalleNotaCompraDTO);
        AlmacenParabrisaDto almacenParabrisa = almacenParabrisaService.findByParabrisaIdAndAlmacenId(
                detalleNotaCompraDTO.getParabrisaId() ,
                notaCompra.getAlmacen().getId()
        );
             if(almacenParabrisa.getStock() < detalleNotaCompraDTO.getCantidad()){
                 throw new ResourceNotFoundException("No hay suficiente stock para el parabrisa con id " + detalleNotaCompraDTO.getParabrisaId());
             }
            almacenParabrisa.setStock(almacenParabrisa.getStock() - detalleNotaCompraDTO.getCantidad());

            almacenParabrisaService.update(almacenParabrisa.getId(), almacenParabrisa);
            detalle.setNotaVenta(notaCompra);
            detalle = dettallerepo.save(detalle);

    }








    @Override
    public void update(UUID id, DetalleNotaVentaDTO detalleNotaCompraDTO) {
        DetalleNotaVenta detalleNotaCompra = dettallerepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DetalleNotaCompra con id " + id + " no encontrado"));
        // Actualizar campos de DetalleNotaCompra
        detalleNotaCompra.setCantidad(detalleNotaCompraDTO.getCantidad());
        detalleNotaCompra.setPrecio(detalleNotaCompraDTO.getPrecio());
        Parabrisa parabrisa = parabrisaService.mapToEntity(parabrisaService.findById(detalleNotaCompraDTO.getParabrisaId()));
        detalleNotaCompra.setParabrisa(parabrisa);

        // Actualizar stock en el almacén
        AlmacenParabrisaDto almacenParabrisa = almacenParabrisaService.findByParabrisaIdAndAlmacenId(
                detalleNotaCompraDTO.getParabrisaId(),
                detalleNotaCompra.getNotaVenta().getAlmacen().getId()
        );
        if (almacenParabrisa != null) {
            if(( (almacenParabrisa.getStock() + detalleNotaCompra.getCantidad() )- detalleNotaCompraDTO.getCantidad()) >0){
                almacenParabrisa.setStock( (almacenParabrisa.getStock() + detalleNotaCompra.getCantidad())- detalleNotaCompraDTO.getCantidad());
            }
            almacenParabrisaService.update(almacenParabrisa.getId(), almacenParabrisa);
        }
        dettallerepo.save(detalleNotaCompra);
    }




    @Override
    public ResponseEntity<?> delete(UUID id) {
        Optional<DetalleNotaVenta> detalle = dettallerepo.findById(id);
        if (detalle.isPresent()) {
            DetalleNotaVenta detalleNotaCompra = detalle.get();

            // Actualizar stock en el almacén
            AlmacenParabrisaDto almacenParabrisa = almacenParabrisaService.findByParabrisaIdAndAlmacenId(
                    detalleNotaCompra.getParabrisa().getId(),
                    detalleNotaCompra.getNotaVenta().getAlmacen().getId()
            );
            if (almacenParabrisa != null) {
                almacenParabrisa.setStock(almacenParabrisa.getStock() - detalleNotaCompra.getCantidad());
                almacenParabrisaService.update(almacenParabrisa.getId(), almacenParabrisa);
            }
            dettallerepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new ResourceNotFoundException("detalle con id " + id + " no encontrado");//404 not found
        }
    }








    @Override
    public DetalleNotaVentaDTO mapToDTO(DetalleNotaVenta detalleNotaCompra) {
        ParabrisaDTO parabrisaDTO=parabrisaService.findById(detalleNotaCompra.getParabrisa().getId());
        System.out.println(detalleNotaCompra.toString());
        return new DetalleNotaVentaDTO(
                detalleNotaCompra.getId(),
                detalleNotaCompra.getCantidad(),
                detalleNotaCompra.getPrecio(),
                detalleNotaCompra.getCreadoEn(),
                detalleNotaCompra.getActualizadoEn(),
                detalleNotaCompra.getParabrisa().getId(),
                detalleNotaCompra.getNotaVenta().getId()
        );

    }



    @Override
    public DetalleNotaVenta mapToEntity(DetalleNotaVentaDTO detalleNotaCompraDTO) {
        DetalleNotaVenta detalleNotaVenta = new DetalleNotaVenta();
        //detalleNotaCompra.setId(detalleNotaCompraDTO.getId());
        detalleNotaVenta.setCantidad(detalleNotaCompraDTO.getCantidad());
        //detalleNotaCompra.setNotaCompra(notaCompraRepository.findById(detalleNotaCompraDTO.getNotaCompraId()).get());
        detalleNotaVenta.setPrecio(detalleNotaCompraDTO.getPrecio());
        detalleNotaVenta.setParabrisa(parabrisaService.mapToEntity(parabrisaService.findById(detalleNotaCompraDTO.getParabrisaId())));
        return detalleNotaVenta;
    }
}
