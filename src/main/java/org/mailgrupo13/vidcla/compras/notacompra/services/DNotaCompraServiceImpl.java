package org.mailgrupo13.vidcla.compras.notacompra.services;

import org.mailgrupo13.vidcla.Inventario.almacen.dto.AlmacenParabrisaDto;
import org.mailgrupo13.vidcla.Inventario.almacen.services.AlmacenParabrisaService;
import org.mailgrupo13.vidcla.Inventario.almacen.services.AlmacenParabrisaServiceImpl;
import org.mailgrupo13.vidcla.Inventario.almacen.services.AlmacenService;
import org.mailgrupo13.vidcla.Inventario.parabrisa.dto.ParabrisaDTO;
import org.mailgrupo13.vidcla.Inventario.parabrisa.services.ParabrisaService;
import org.mailgrupo13.vidcla.compras.notacompra.dtos.DetalleNotaCompraDTO;
import org.mailgrupo13.vidcla.compras.notacompra.entities.DetalleNotaCompra;
import org.mailgrupo13.vidcla.compras.notacompra.entities.NotaCompra;
import org.mailgrupo13.vidcla.compras.notacompra.repositories.DetalleNotaCompraRepository;
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
public class DNotaCompraServiceImpl implements DetalleNotaCompraService{


    @Autowired
    private DetalleNotaCompraRepository dettallerepo;

    @Autowired
    private AlmacenService almacenService;

    @Autowired
    private ParabrisaService parabrisaService;


   /* @Autowired
    private NotaCompraService notaCompraService;*/


    @Autowired
    private AlmacenParabrisaService almacenParabrisaService;



    @Override
    public DetalleNotaCompraDTO create(DetalleNotaCompraDTO detalleNotaCompraDTO) {
        Optional<DetalleNotaCompra> existingCategoria =dettallerepo.findByParabrisaId(detalleNotaCompraDTO.getParabrisadId());
        if (existingCategoria.isPresent())
            throw new ResourceAlreadyExistsException("CategoriaP con nombre " + detalleNotaCompraDTO.getParabrisadId() + " ya existe");

        DetalleNotaCompra categoriaP = convertToEntity(detalleNotaCompraDTO);
        categoriaP = dettallerepo.save(categoriaP);
        return convertToDTO(categoriaP);
    }






    @Override
    public DetalleNotaCompraDTO update(UUID id, DetalleNotaCompraDTO detalleNotaCompraDTO) {
       /* DetalleNotaCompra detalleNotaCompra = dettallerepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Almacen con id " + id + " no encontrado"));

        Optional<DetalleNotaCompra> existingCategoria = dettallerepo.findByParabrisaId(detalleNotaCompraDTO.getParabrisadId());
        if (existingCategoria.isPresent())
            throw new ResourceAlreadyExistsException("CategoriaP con nombre " + detalleNotaCompraDTO.getParabrisadId() + " ya existe");


        AlmacenParabrisaDto almacenParabrisaDto = almacenParabrisaService.findByParabrisaIdAndAlmacenId(detalleNotaCompraDTO.getParabrisadId(), detalleNotaCompraDTO.getNotaCompraId());
        if (almacenParabrisaDto.getStock() < detalleNotaCompraDTO.getCanitdad())
            throw new ResourceNotFoundException("No hay suficiente stock para la compra");//404 not found


        detalleNotaCompra.setCanitdad(detalleNotaCompraDTO.getCanitdad());
        detalleNotaCompra.setPrecio(detalleNotaCompraDTO.getPrecio());
        detalleNotaCompra.setParabrisa(parabrisaService.convertToEntity(parabrisaService.findById(detalleNotaCompraDTO.getParabrisadId())));
        detalleNotaCompra.setNotaCompra(notaCompraService.convertToEntity(notaCompraService.findById(detalleNotaCompraDTO.getNotaCompraId())));
        detalleNotaCompra = dettallerepo.save(detalleNotaCompra);
        return convertToDTO(detalleNotaCompra);*/
        return null;
    }





    @Override
    public ResponseEntity<?> delete(UUID id) {
        Optional<DetalleNotaCompra> categoriaP = dettallerepo.findById(id);
        if (categoriaP.isPresent()) {
            dettallerepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new ResourceNotFoundException("Almacen con id " + id + " no encontrado");//404 not found
        }
    }




    @Override
    public DetalleNotaCompraDTO findById(UUID id) {
        return dettallerepo.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Almacen con id " + id + " no encontrado"));
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
    public DetalleNotaCompraDTO convertToDTO(DetalleNotaCompra detalleNotaCompra) {
        ParabrisaDTO parabrisaDTO=parabrisaService.findById(detalleNotaCompra.getParabrisa().getId());
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
      /*  NotaCompra notaCompra =notaCompraService.convertToEntity(notaCompraService.findById(detalleNotaCompraDTO.getNotaCompraId()));
        DetalleNotaCompra categoriaP = new DetalleNotaCompra();
        categoriaP.setId(detalleNotaCompraDTO.getId());
        categoriaP.setCanitdad(detalleNotaCompraDTO.getCanitdad());
        categoriaP.setPrecio(detalleNotaCompraDTO.getPrecio());
        categoriaP.setNotaCompra(notaCompra);
        categoriaP.setParabrisa(parabrisaService.convertToEntity(parabrisaService.findById(detalleNotaCompraDTO.getParabrisadId())));
        return categoriaP;*/
        return null;
    }

}
