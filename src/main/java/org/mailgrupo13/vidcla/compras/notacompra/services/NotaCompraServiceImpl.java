package org.mailgrupo13.vidcla.compras.notacompra.services;
import org.mailgrupo13.vidcla.Inventario.almacen.entities.*;
import org.mailgrupo13.vidcla.Inventario.almacen.services.*;
import org.mailgrupo13.vidcla.compras.notacompra.dtos.*;
import org.mailgrupo13.vidcla.compras.notacompra.entities.*;
import org.mailgrupo13.vidcla.compras.notacompra.repositories.*;
import org.mailgrupo13.vidcla.compras.notacompra.services.interfaces.DetalleNotaCompraService;
import org.mailgrupo13.vidcla.compras.notacompra.services.interfaces.NotaCompraService;
import org.mailgrupo13.vidcla.compras.proveedor.*;
import org.mailgrupo13.vidcla.compras.proveedor.services.*;
import org.mailgrupo13.vidcla.validations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NotaCompraServiceImpl implements NotaCompraService {

    @Autowired
    private NotaCompraRepository notaCompraRepository;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private AlmacenService almacenService;

    @Autowired
    private DetalleNotaCompraService detalleNotaCompraService;

    @Override
    public ResponseEntity<List<NotaCompraDTO>> findAll() {
        List<NotaCompra> notaCompras = notaCompraRepository.findAll();
        List<NotaCompraDTO>notaCompraDTO=new ArrayList<>();
        for (NotaCompra notaCompra : notaCompras) {
            notaCompraDTO.add(convertToDTO(notaCompra));
        }
        return ResponseEntity.ok(notaCompraDTO);
    }




    @Override
    @Transactional
    public NotaCompra create(NotaCompraDTO notaCompraDTO) {
        checkIfNotaCompraExistsByNumero(notaCompraDTO.getNumero());
        Proveedor proveedor = proveedorService.convertToEntity(proveedorService.findById(notaCompraDTO.getProveedorId()));
        Almacen almacen = almacenService.mapToEntity(almacenService.findById(notaCompraDTO.getAlmacenId()));
        NotaCompra notaCompra = convertToEntity(notaCompraDTO);
        notaCompra = notaCompraRepository.save(notaCompra);
        List<DetalleNotaCompra> detalles = new ArrayList<>();
        for (DetalleNotaCompraDTO detalleDTO : notaCompraDTO.getDetalleNotaCompraDTO()) {
            detalleNotaCompraService.create(notaCompra,detalleDTO);
        }
        return notaCompra;
    }


    @Override
    @Transactional
    public void update(UUID id, NotaCompraDTO notaCompraDTO) {
        NotaCompra notaCompra = notaCompraRepository.findById(id)
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
        notaCompraRepository.save(notaCompra);
        //System.out.println();
        //return updatedNotaCompra;
    }
















    @Override
    public NotaCompraDTO findById(UUID id) {
        return notaCompraRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("NotaCompra con id " + id + " no encontrado"));
    }



    @Override
    public void delete(UUID id) {
        NotaCompra notaCompra = notaCompraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NotaCompra con id " + id + " no encontrado"));
        if ( notaCompra == null) {
          ResponseEntity.status(HttpStatus.NOT_FOUND).body("NotaCompra con id " + id + " no encontrado");
        }
        notaCompraRepository.delete(notaCompra);
    }


    @Override
    public NotaCompraDTO convertToDTO(NotaCompra notaCompra) {
        return new NotaCompraDTO(
                notaCompra.getId(),
                notaCompra.getNumero(),
                notaCompra.getTotal(),
                notaCompra.getEstado(),
                notaCompra.getProveedor().getId(),
                notaCompra.getAlmacen().getId(),
                null
        );
    }



    public NotaCompra convertToEntity(NotaCompraDTO notaCompraDTO) {

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
    }


    private void checkIfNotaCompraExistsByNumero(Integer numero) {
        Optional<NotaCompra> existingNotaCompra = notaCompraRepository.findByNumero(numero);
        if (existingNotaCompra.isPresent()) {
           ResponseEntity.status(HttpStatus.CONFLICT).body("NotaCompra con numero " + numero + " ya existe");
        }
    }




    private void checkIfNotaCompraExistsByNumeroAndDifferentId(Integer numero, UUID id) {
        Optional<NotaCompra> existingNotaCompra = notaCompraRepository.findByNumero(numero);
        if (existingNotaCompra.isPresent() && !existingNotaCompra.get().getId().equals(id)) {
            ResponseEntity.status(HttpStatus.CONFLICT).body("NotaCompra con numero " + numero + " ya existe");
        }
    }
}
