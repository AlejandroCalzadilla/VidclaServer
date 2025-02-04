package org.mailgrupo13.vidcla.compras.notacompra.services;

import org.mailgrupo13.vidcla.Inventario.almacen.entities.Almacen;
import org.mailgrupo13.vidcla.Inventario.almacen.services.AlmacenService;
import org.mailgrupo13.vidcla.Inventario.parabrisa.services.ParabrisaService;
import org.mailgrupo13.vidcla.compras.notacompra.dtos.NotaCompraDTO;
import org.mailgrupo13.vidcla.compras.notacompra.entities.DetalleNotaCompra;
import org.mailgrupo13.vidcla.compras.notacompra.entities.NotaCompra;
import org.mailgrupo13.vidcla.compras.notacompra.repositories.NotaCompraRepository;
import org.mailgrupo13.vidcla.compras.proveedor.Proveedor;
import org.mailgrupo13.vidcla.compras.proveedor.services.ProveedorService;
import org.mailgrupo13.vidcla.validations.ResourceAlreadyExistsException;
import org.mailgrupo13.vidcla.validations.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NotaCompraServiceImpl implements NotaCompraService{

    @Autowired
    private NotaCompraRepository notaCompraRepository;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private AlmacenService almacenService;

    @Autowired
    private ParabrisaService parabrisaService;

    @Autowired
    private DetalleNotaCompraService dNCompraService;



    @Override
    public ResponseEntity<List<NotaCompraDTO>> findAll() {
        // Implementation here
        return null;
    }




    @Override
    public NotaCompra create(NotaCompraDTO notaCompraDTO) {
        checkIfNotaCompraExistsByNumero(notaCompraDTO.getNumero());
        NotaCompra notaCompra = convertToEntity(notaCompraDTO);
        notaCompra = notaCompraRepository.save(notaCompra);

        // Guarda cada DetalleNotaCompra
        NotaCompra finalNotaCompra = notaCompra;
        notaCompraDTO.getDetalleNotaCompraDTO().forEach(detalleDTO -> {
            DetalleNotaCompra detalle = dNCompraService.convertToEntity(detalleDTO);
            detalle.setNotaCompra(finalNotaCompra);
            dNCompraService.create(detalleDTO);
        });

        return notaCompra;
    }

    @Override
    public NotaCompraDTO update(UUID id, NotaCompraDTO notaCompraDTO) {
        NotaCompra notaCompra = findNotaCompraById(id);
        checkIfNotaCompraExistsByNumeroAndDifferentId(notaCompraDTO.getNumero(), id);

        notaCompra.setNumero(notaCompraDTO.getNumero());
        notaCompra.setFecha(notaCompraDTO.getFecha());
        notaCompra.setTotal(notaCompraDTO.getTotal());
        notaCompra.setEstado(notaCompraDTO.getEstado());

        Proveedor proveedor=proveedorService.convertToEntity( proveedorService.findById(notaCompraDTO.getProveedorId()));
        Almacen almacen=almacenService.convertToEntity(almacenService.findById(notaCompraDTO.getAlmacenId()));

        notaCompra.setProveedor(proveedor);
        notaCompra.setAlmacen(almacen);

        NotaCompra updatedNotaCompra = notaCompraRepository.save(notaCompra);
        return convertToDTO(updatedNotaCompra);
    }




    private NotaCompra findNotaCompraById(UUID id) {
        return notaCompraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NotaCompra con id " + id + " no encontrado"));
    }



    @Override
    public NotaCompraDTO findById(UUID id) {

        return notaCompraRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("NotaCompra con id " + id + " no encontrado"));
    }

    @Override
    public ResponseEntity<?> delete(UUID id) {

        return null;
    }













    @Override
    public NotaCompraDTO convertToDTO(NotaCompra notaCompra) {
        return new NotaCompraDTO(
                notaCompra.getId(),
                notaCompra.getNumero(),
                notaCompra.getFecha(),
                notaCompra.getTotal(),
                notaCompra.getEstado(),
                notaCompra.getProveedor().getId(),
                notaCompra.getAlmacen().getId(),
                notaCompra.getDetalleNotaCompras().stream()
                        .map(dNCompraService::convertToDTO)
                        .collect(Collectors.toList())
        );
    }



    public NotaCompra convertToEntity(NotaCompraDTO notaCompraDTO) {

        Proveedor proveedor = proveedorService.convertToEntity(proveedorService.findById(notaCompraDTO.getProveedorId()));
        Almacen almacen= almacenService.convertToEntity(almacenService.findById(notaCompraDTO.getAlmacenId()));
        NotaCompra notaCompra = new NotaCompra();
        notaCompra.setId(notaCompraDTO.getId());
        notaCompra.setNumero(notaCompraDTO.getNumero());
        notaCompra.setFecha(notaCompraDTO.getFecha());
        notaCompra.setTotal(notaCompraDTO.getTotal());
        notaCompra.setEstado(notaCompraDTO.getEstado());
        notaCompra.setProveedor(proveedor);
        notaCompra.setAlmacen(almacen);

        return notaCompra;
    }


    private void checkIfNotaCompraExistsByNumero(Integer numero) {
        Optional<NotaCompra> existingNotaCompra = notaCompraRepository.findByNumero(numero);
        if (existingNotaCompra.isPresent()) {
            throw new ResourceAlreadyExistsException("NotaCompra con numero " + numero + " ya existe");
        }
    }

    private void checkIfNotaCompraExistsByNumeroAndDifferentId(Integer numero, UUID id) {
        Optional<NotaCompra> existingNotaCompra = notaCompraRepository.findByNumero(numero);
        if (existingNotaCompra.isPresent() && !existingNotaCompra.get().getId().equals(id)) {
            throw new ResourceAlreadyExistsException("NotaCompra con numero " + numero + " ya existe");
        }
    }





}
