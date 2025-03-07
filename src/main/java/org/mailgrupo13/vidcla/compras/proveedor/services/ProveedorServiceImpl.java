package org.mailgrupo13.vidcla.compras.proveedor.services;

import org.mailgrupo13.vidcla.compras.proveedor.Proveedor;
import org.mailgrupo13.vidcla.compras.proveedor.repositories.ProveedorRepository;
import org.mailgrupo13.vidcla.compras.proveedor.dtos.ProveedorDTO;
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
public class ProveedorServiceImpl implements ProveedorService{

    @Autowired
    private ProveedorRepository proveedorRepository;


    @Override
    public ProveedorDTO create(ProveedorDTO categoriaPDTO) {
        Optional<Proveedor> existingCategoria = proveedorRepository.findByNombreempresa(categoriaPDTO.getNombreempresa());
        if (existingCategoria.isPresent()) {
            throw new ResourceAlreadyExistsException("CategoriaP con nombre " + categoriaPDTO.getNombreempresa() + " ya existe");
        }
        Proveedor categoriaP = convertToEntity(categoriaPDTO);
        categoriaP = proveedorRepository.save(categoriaP);
        return convertToDTO(categoriaP);
    }

    @Override
    public List<ProveedorDTO> findAll() {
       return proveedorRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public ResponseEntity<String> delete(UUID id) {
        Optional<Proveedor> categoriaP = proveedorRepository.findById(id);
        if (categoriaP.isPresent()) {
            proveedorRepository.deleteById(id);
            return ResponseEntity.status(200).body("CategoriaP eliminada exitosamente");
        } else {
            throw new ResourceNotFoundException("CategoriaP con id " + id + " no encontrada");
        }
    }

    @Override
    public ProveedorDTO update(UUID id, ProveedorDTO proveedorDTO) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CategoriaP con id " + id + " no encontrada"));

        Optional<Proveedor> existingAlmacen = proveedorRepository.findByNombreempresa(proveedorDTO.getNombreempresa());
        if (existingAlmacen.isPresent() && !existingAlmacen.get().getId().equals(id)) {
            throw new ResourceAlreadyExistsException("proveedor con nombre_empresa " + proveedorDTO.getNombreempresa() + " ya existe");
        }

        proveedor.setNombreempresa(proveedorDTO.getNombreempresa());
        proveedor.setNombreencargado(proveedorDTO.getNombreencargado());
        proveedor.setDireccion(proveedorDTO.getDireccion());
        proveedor.setNumero(proveedorDTO.getNumero());
        proveedor.setCorreo(proveedorDTO.getCorreo());
        Proveedor p=  proveedorRepository.save(proveedor);
        return convertToDTO(p);
    }


    @Override
    public org.mailgrupo13.vidcla.compras.proveedor.dtos.ProveedorDTO findById(UUID id) {
        Proveedor categoria= proveedorRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Proveedor con id " + id + " no encontrada"));
        return  convertToDTO(categoria);
    }











    public ProveedorDTO convertToDTO(Proveedor categoriaP){
        return new ProveedorDTO(categoriaP.getId(),
                                  categoriaP.getNombreempresa(),
                                  categoriaP.getNombreencargado(),
                                   categoriaP.getDireccion(),
                                   categoriaP.getNumero(),
                                   categoriaP.getCorreo()
                );
    }





    public  Proveedor convertToEntity(ProveedorDTO proveedorDTO){
        Proveedor proveedor=new Proveedor();
        proveedor.setId(proveedorDTO.getId());
        proveedor.setNombreencargado(proveedorDTO.getNombreencargado());
        proveedor.setCorreo(proveedorDTO.getCorreo());
        proveedor.setDireccion(proveedorDTO.getDireccion());
        proveedor.setNumero(proveedorDTO.getNumero());
        proveedor.setNombreempresa(proveedorDTO.getNombreempresa());
        return proveedor;
    }




}
