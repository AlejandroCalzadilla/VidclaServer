package org.mailgrupo13.vidcla.ventas.notaventa.services.impl;

import org.mailgrupo13.vidcla.Inventario.almacen.entities.Almacen;
import org.mailgrupo13.vidcla.Inventario.almacen.services.AlmacenService;
import org.mailgrupo13.vidcla.compras.notacompra.dtos.DetalleNotaCompraDTO;
import org.mailgrupo13.vidcla.compras.notacompra.dtos.NotaCompraDTO;
import org.mailgrupo13.vidcla.compras.notacompra.entities.DetalleNotaCompra;
import org.mailgrupo13.vidcla.compras.notacompra.entities.NotaCompra;
import org.mailgrupo13.vidcla.compras.notacompra.services.interfaces.DetalleNotaCompraService;
import org.mailgrupo13.vidcla.compras.proveedor.Proveedor;
import org.mailgrupo13.vidcla.compras.proveedor.services.ProveedorService;
import org.mailgrupo13.vidcla.validations.ResourceNotFoundException;
import org.mailgrupo13.vidcla.ventas.notaventa.dtos.DetalleNotaVentaDTO;
import org.mailgrupo13.vidcla.ventas.notaventa.dtos.NotaVentaDTO;
import org.mailgrupo13.vidcla.ventas.notaventa.entities.DetalleNotaVenta;
import org.mailgrupo13.vidcla.ventas.notaventa.entities.NotaVenta;
import org.mailgrupo13.vidcla.ventas.notaventa.repositories.NotaVentaRepository;
import org.mailgrupo13.vidcla.ventas.notaventa.services.NotaVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

public class NotaVentaServiceImpl implements NotaVentaService {

    @Autowired
    private NotaVentaRepository notaVentaRepository;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private AlmacenService almacenService;

    @Autowired
    private DetalleNotaCompraService detalleNotaCompraService;

    @Override
    public ResponseEntity<List<NotaVentaDTO>> findAll() {
        List<NotaVenta> notaCompras = notaVentaRepository.findAll();
        List<NotaVentaDTO>notaCompraDTO=new ArrayList<>();
        for (NotaVenta notaCompra : notaCompras) {
            notaCompraDTO.add(convertToDTO(notaCompra));
        }
        return ResponseEntity.ok(notaCompraDTO);
    }




    @Override
    @Transactional
    public NotaVenta create(NotaVentaDTO notaCompraDTO) {
        checkIfNotaCompraExistsByNumero(notaCompraDTO.getNumero());
        //Proveedor proveedor = proveedorService.convertToEntity(proveedorService.findById(notaCompraDTO.getProveedorId());
        //Almacen almacen = almacenService.mapToEntity(almacenService.findById(notaCompraDTO.getAlmacenId()));
        NotaVenta notaCompra = convertToEntity(notaCompraDTO);
        notaCompra = notaVentaRepository.save(notaCompra);
        List<DetalleNotaVenta> detalles = new ArrayList<>();
      /*  for (DetalleNotaVentaDTO detalleDTO : notaCompraDTO.getDetalleNotaVenta()) {
            detalleNotaCompraService.create(notaCompra,detalleDTO);
        }*/
        return notaCompra;
    }


    /*@Override
    @Transactional
    public void update(UUID id, NotaCompraDTO notaCompraDTO) {
        NotaCompra notaCompra = notaVentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NotaCompra con id " + id + " no encontrado"));

        // Actualizar campos de NotaCompra
        notaCompra.setNumero(notaCompraDTO.getNumero());
        notaCompra.setTotal(notaCompraDTO.getTotal());
        notaCompra.setEstado(notaCompraDTO.getEstado());
        Proveedor proveedor = proveedorService.convertToEntity(proveedorService.findById(notaCompraDTO.getProveedorId()));
        Almacen almacen = almacenService.mapToEntity(almacenService.findById(notaCompraDTO.getAlmacenId()));
        notaCompra.setProveedor(proveedor);
        notaCompra.setAlmacen(almacen);

        // Actualizar detalles
        List<DetalleNotaCompra> detallesExistentes = notaCompra.getDetalleNotaCompras();
        List<DetalleNotaCompraDTO> nuevosDetallesDTO = notaCompraDTO.getDetalleNotaCompraDTO();

        // Eliminar detalles que ya no están presentes
        Iterator<DetalleNotaCompra> iterator = detallesExistentes.iterator();
        while (iterator.hasNext()) {
            DetalleNotaCompra detalleExistente = iterator.next();
            boolean existe = nuevosDetallesDTO.stream()
                    .filter(nuevoDetalle -> nuevoDetalle.getId() != null)
                    .anyMatch(nuevoDetalle -> nuevoDetalle.getId().equals(detalleExistente.getId()));
            if (!existe) {
                System.out.println("llega por aca borrar el detlla que borre");
                detalleNotaCompraService.delete(detalleExistente.getId());
                iterator.remove();
            }
        }

        // Agregar o actualizar detalles
        for (DetalleNotaCompraDTO nuevoDetalleDTO : nuevosDetallesDTO) {
            if (nuevoDetalleDTO.getId() == null) {
                System.out.println("llega aca al create");
                try {
                    detalleNotaCompraService.create(notaCompra, nuevoDetalleDTO);
                } catch (Exception e) {
                    System.out.println("llega por aca al create error"+ e);
                }
                // detalleNotaCompraService.create(notaCompra, nuevoDetalleDTO);

            } else {
                System.out.println("seguro entra por aca al update");
                detalleNotaCompraService.update(nuevoDetalleDTO.getId(), nuevoDetalleDTO);
            }
        }

        System.out.println("llega aca a lo ultimo");
        notaVentaRepository.save(notaCompra);
        //System.out.println();
        //return updatedNotaCompra;
    }*/
















    @Override
    public NotaVentaDTO findById(UUID id) {
        return notaVentaRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("NotaCompra con id " + id + " no encontrado"));
    }

    @Override
    public void update(UUID id, NotaVentaDTO notaCompraDTO) {

    }


    @Override
    public void delete(UUID id) {
        NotaVenta notaCompra = notaVentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NotaCompra con id " + id + " no encontrado"));
        if ( notaCompra == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("NotaCompra con id " + id + " no encontrado");
        }
        notaVentaRepository.delete(notaCompra);
    }


    @Override
    public NotaVentaDTO convertToDTO(NotaVenta notaCompra) {
        return new NotaVentaDTO(
                );
    }

    @Override
    public NotaVenta convertToEntity(NotaVentaDTO notaCompraDTO) {
        return null;
    }



    /*public NotaVenta convertToEntity(NotaVentaDTO notaCompraDTO) {

        Proveedor proveedor = proveedorService.convertToEntity(proveedorService.findById(notaCompraDTO.getProveedorId()));
        Almacen almacen= almacenService.mapToEntity(almacenService.findById(notaCompraDTO.getAlmacenId()));
        NotaCompra notaCompra = new NotaCompra();
        notaCompra.setId(notaCompraDTO.getId());
        notaCompra.setNumero(notaCompraDTO.getNumero());

        notaCompra.setTotal(notaCompraDTO.getTotal());
        notaCompra.setEstado(notaCompraDTO.getEstado());
        notaCompra.setProveedor(proveedor);
        notaCompra.setAlmacen(almacen);
        notaCompra.setDetalleNotaCompras(notaCompraDTO.getDetalleNotaCompraDTO().stream()
                .map(detalleNotaCompraService::convertToEntity)
                .collect(Collectors.toList())
        );

        return notaCompra;
    }*/


    private void checkIfNotaCompraExistsByNumero(Integer numero) {
        Optional<NotaVenta> existingNotaCompra = notaVentaRepository.findByNumero(numero);
        if (existingNotaCompra.isPresent()) {
            ResponseEntity.status(HttpStatus.CONFLICT).body("NotaCompra con numero " + numero + " ya existe");
        }
    }




    private void checkIfNotaCompraExistsByNumeroAndDifferentId(Integer numero, UUID id) {
        Optional<NotaVenta> existingNotaCompra = notaVentaRepository.findByNumero(numero);
        if (existingNotaCompra.isPresent() && !existingNotaCompra.get().getId().equals(id)) {
            ResponseEntity.status(HttpStatus.CONFLICT).body("NotaCompra con numero " + numero + " ya existe");
        }
    }
}
