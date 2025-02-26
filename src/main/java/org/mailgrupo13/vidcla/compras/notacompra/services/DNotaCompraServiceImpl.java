package org.mailgrupo13.vidcla.compras.notacompra.services;


import org.mailgrupo13.vidcla.Inventario.almacen.dto.AlmacenParabrisaDto;
import org.mailgrupo13.vidcla.Inventario.almacen.entities.AlmacenParabrisa;
import org.mailgrupo13.vidcla.Inventario.almacen.services.*;
import org.mailgrupo13.vidcla.Inventario.parabrisa.dto.ParabrisaDTO;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.Parabrisa;
import org.mailgrupo13.vidcla.Inventario.parabrisa.services.ParabrisaService;
import org.mailgrupo13.vidcla.compras.notacompra.dtos.DetalleNotaCompraDTO;
import org.mailgrupo13.vidcla.compras.notacompra.entities.*;
import org.mailgrupo13.vidcla.compras.notacompra.repositories.*;
import org.mailgrupo13.vidcla.compras.notacompra.services.interfaces.DetalleNotaCompraService;
import org.mailgrupo13.vidcla.validations.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class DNotaCompraServiceImpl implements DetalleNotaCompraService {



    private DetalleNotaCompraRepository dettallerepo;
    private AlmacenService almacenService;
    private ParabrisaService parabrisaService;
    private NotaCompraRepository notaCompraRepository;
    private AlmacenParabrisaService almacenParabrisaService;


    @Autowired
    public DNotaCompraServiceImpl(DetalleNotaCompraRepository dettallerepo,
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
    public ResponseEntity<List<DetalleNotaCompraDTO>> findAll() {
        List<DetalleNotaCompra> categorias = dettallerepo.findAll();
        if (categorias.isEmpty())
            return ResponseEntity.noContent().build(); //204  np content
        List<DetalleNotaCompraDTO> categoriasDTO = new ArrayList<>();
        for (DetalleNotaCompra categoria : categorias) {
            categoriasDTO.add(convertToDTO(categoria));
        }
        return ResponseEntity.ok(categoriasDTO);
    }


    @Override
    public DetalleNotaCompraDTO findById(UUID id) {
        return dettallerepo.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Almacen con id " + id + " no encontrado"));
    }






    @Override
    public void create(NotaCompra notaCompra,DetalleNotaCompraDTO detalleNotaCompraDTO) {
        DetalleNotaCompra detalle = convertToEntity(detalleNotaCompraDTO);
        AlmacenParabrisaDto almacenParabrisa = almacenParabrisaService.findByParabrisaIdAndAlmacenId(
                detalleNotaCompraDTO.getParabrisadId() ,
                notaCompra.getAlmacen().getId()
        );
        if (almacenParabrisa == null) {
            AlmacenParabrisa almacennew = new AlmacenParabrisa();
            Parabrisa parabrisa = parabrisaService.mapToEntity(parabrisaService.findById(detalleNotaCompraDTO.getParabrisadId()));
                almacennew.setParabrisa(parabrisa);
                almacennew.setAlmacen(almacenService.mapToEntity(almacenService.findById(notaCompra.getAlmacen().getId())));
                almacennew.setStock(detalleNotaCompraDTO.getCantidad());
                almacennew.setCodigo(
                        parabrisa.getPosicion().getCodigo() + "-" +
                        notaCompra.getProveedor().getNumero() +
                        parabrisa.getVehiculo().getMarca().getCodigo() +
                        parabrisa.getCategoria().getCodigo());
                almacenParabrisaService.create(almacenParabrisaService.convertToDTO(almacennew));
                detalle.setNotaCompra(notaCompra);
                detalle = dettallerepo.save(detalle);
        }
        else {
            almacenParabrisa.setStock(almacenParabrisa.getStock() + detalleNotaCompraDTO.getCantidad());
            almacenParabrisaService.update(almacenParabrisa.getId(), almacenParabrisa);
            detalle.setNotaCompra(notaCompra);
            detalle = dettallerepo.save(detalle);

        }
    }








    @Override
    public void update(UUID id, DetalleNotaCompraDTO detalleNotaCompraDTO) {
        DetalleNotaCompra detalleNotaCompra = dettallerepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DetalleNotaCompra con id " + id + " no encontrado"));

        // Actualizar campos de DetalleNotaCompra
        detalleNotaCompra.setCanitdad(detalleNotaCompraDTO.getCantidad());
        detalleNotaCompra.setPrecio(detalleNotaCompraDTO.getPrecio());
        Parabrisa parabrisa = parabrisaService.mapToEntity(parabrisaService.findById(detalleNotaCompraDTO.getParabrisadId()));
        detalleNotaCompra.setParabrisa(parabrisa);

        // Actualizar stock en el almacén
        AlmacenParabrisaDto almacenParabrisa = almacenParabrisaService.findByParabrisaIdAndAlmacenId(
                detalleNotaCompraDTO.getParabrisadId(),
                detalleNotaCompra.getNotaCompra().getAlmacen().getId()
        );
        if (almacenParabrisa != null) {

            if(( (almacenParabrisa.getStock() - detalleNotaCompra.getCanitdad() )+ detalleNotaCompraDTO.getCantidad()) >0){
                almacenParabrisa.setStock(almacenParabrisa.getStock() - detalleNotaCompraDTO.getCantidad());
            }
            almacenParabrisaService.update(almacenParabrisa.getId(), almacenParabrisa);
        }

         dettallerepo.save(detalleNotaCompra);
        // updatedDetalleNotaCompra;
    }




    @Override
    public ResponseEntity<?> delete(UUID id) {
        Optional<DetalleNotaCompra> detalle = dettallerepo.findById(id);
        if (detalle.isPresent()) {
            DetalleNotaCompra detalleNotaCompra = detalle.get();

            // Actualizar stock en el almacén
            AlmacenParabrisaDto almacenParabrisa = almacenParabrisaService.findByParabrisaIdAndAlmacenId(
                    detalleNotaCompra.getParabrisa().getId(),
                    detalleNotaCompra.getNotaCompra().getAlmacen().getId()
            );
            if (almacenParabrisa != null) {
                almacenParabrisa.setStock(almacenParabrisa.getStock() - detalleNotaCompra.getCanitdad());
                almacenParabrisaService.update(almacenParabrisa.getId(), almacenParabrisa);
            }
            dettallerepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new ResourceNotFoundException("detalle con id " + id + " no encontrado");//404 not found
        }
    }








    @Override
    public DetalleNotaCompraDTO convertToDTO(DetalleNotaCompra detalleNotaCompra) {
        ParabrisaDTO parabrisaDTO=parabrisaService.findById(detalleNotaCompra.getParabrisa().getId());
        System.out.println(detalleNotaCompra.toString());
        return new DetalleNotaCompraDTO(
                detalleNotaCompra.getId(),
                detalleNotaCompra.getCanitdad(),
                detalleNotaCompra.getPrecio(),
                parabrisaDTO.getId(),
                detalleNotaCompra.getNotaCompra().getId()
        );
    }



    @Override
    public DetalleNotaCompra convertToEntity(DetalleNotaCompraDTO detalleNotaCompraDTO) {
        DetalleNotaCompra detalleNotaCompra = new DetalleNotaCompra();
        //detalleNotaCompra.setId(detalleNotaCompraDTO.getId());
        detalleNotaCompra.setCanitdad(detalleNotaCompraDTO.getCantidad());
        //detalleNotaCompra.setNotaCompra(notaCompraRepository.findById(detalleNotaCompraDTO.getNotaCompraId()).get());
        detalleNotaCompra.setPrecio(detalleNotaCompraDTO.getPrecio());
        detalleNotaCompra.setParabrisa(parabrisaService.mapToEntity(parabrisaService.findById(detalleNotaCompraDTO.getParabrisadId())));
        return detalleNotaCompra;
    }

}
