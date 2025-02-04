package org.mailgrupo13.vidcla.Inventario.almacen.services;
import org.mailgrupo13.vidcla.Inventario.almacen.repositories.AlmacenRepository;
import org.mailgrupo13.vidcla.Inventario.almacen.dto.AlmacenDTO;
import org.mailgrupo13.vidcla.Inventario.almacen.entities.Almacen;
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
public class AlmacenServiceImpl implements AlmacenService{


    @Autowired
    private AlmacenRepository AlmacenRepository;




    @Override
    public ResponseEntity<List<AlmacenDTO>> findAll() {
        List<Almacen> categorias = AlmacenRepository.findAll();
        if (categorias.isEmpty()) {
            return ResponseEntity.noContent().build(); //204  np content
        }
        List<AlmacenDTO> categoriasDTO = new ArrayList<>();
        for (Almacen categoria : categorias) {
            categoriasDTO.add(convertToDTO(categoria));
        }
        return ResponseEntity.ok(categoriasDTO); //200 ok
    }







    @Override
    public AlmacenDTO findById(UUID id) {
        Almacen Almacen= AlmacenRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Marca con id " + id + " no encontrada"));
        return  convertToDTO(Almacen);
    }







    @Override
    public AlmacenDTO create(AlmacenDTO almacenDTO) {;
        Optional<Almacen> existingCategoria = AlmacenRepository.findByNombre(almacenDTO.getNombre());
        if (existingCategoria.isPresent()) {
            throw new ResourceAlreadyExistsException("Almacen con nombre " + almacenDTO.getNombre() + " ya existe");
        }
        Almacen Almacen = convertToEntity(almacenDTO);
        Almacen = AlmacenRepository.save(Almacen);
        System.out.println("Almacen creado: "+Almacen );
        return convertToDTO(Almacen);
    }




    @Override
    public AlmacenDTO update(UUID id, AlmacenDTO almacenDTO) {
        Almacen almacen = AlmacenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Almacen con id " + id + " no encontrado"));

        Optional<Almacen> existingAlmacen = AlmacenRepository.findByNombre(almacenDTO.getNombre());
        if (existingAlmacen.isPresent() && !existingAlmacen.get().getId().equals(id))
            throw new ResourceAlreadyExistsException("Almacen con nombre " + almacenDTO.getNombre() + " ya existe");

        almacen.setNombre(almacenDTO.getNombre());
        almacen.setDireccion(almacenDTO.getDireccion());
        almacen.setCapacidad(almacenDTO.getCapacidad());
        Almacen updatedAlmacen = AlmacenRepository.save(almacen);
        return convertToDTO(updatedAlmacen);

    }






    @Override
    public ResponseEntity<?> delete(UUID id) {
        Optional<Almacen> categoriaP = AlmacenRepository.findById(id);
        if (categoriaP.isPresent()) {
            AlmacenRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new ResourceNotFoundException("Almacen con id " + id + " no encontrado");//404 not found
        }
    }



    private Almacen findAlmacenById(UUID id) {
        return AlmacenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Almacen con id " + id + " no encontrado"));
    }


    @Override
    public AlmacenDTO convertToDTO(Almacen categoriaP){
        AlmacenDTO almacenDTO=new AlmacenDTO(categoriaP.getId(), categoriaP.getNombre(), categoriaP.getDireccion(),categoriaP.getCapacidad());
        almacenDTO.setCreadoEn(categoriaP.getCreadoEn());
        almacenDTO.setActualizadoEn(categoriaP.getActualizadoEn());
        return almacenDTO;
    }





    public  Almacen convertToEntity(AlmacenDTO AlmacenDTO){
        Almacen categoriaP=new Almacen();
        categoriaP.setId(AlmacenDTO.getId());
        categoriaP.setNombre(AlmacenDTO.getNombre());
        categoriaP.setCapacidad(AlmacenDTO.getCapacidad());
        categoriaP.setDireccion(AlmacenDTO.getDireccion());
        return categoriaP;
    }






}
