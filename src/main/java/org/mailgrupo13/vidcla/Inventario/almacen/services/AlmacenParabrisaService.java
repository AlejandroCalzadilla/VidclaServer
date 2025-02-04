package org.mailgrupo13.vidcla.Inventario.almacen.services;

import org.mailgrupo13.vidcla.Inventario.almacen.dto.AlmacenDTO;
import org.mailgrupo13.vidcla.Inventario.almacen.dto.AlmacenParabrisaDto;
import org.mailgrupo13.vidcla.Inventario.almacen.entities.Almacen;
import org.mailgrupo13.vidcla.Inventario.almacen.entities.AlmacenParabrisa;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface AlmacenParabrisaService {

    /**
     * crear registro de almacen
     *
     * @param almacenpDTO el AlmacenDTO con los datos del almacén a crear
     *
     * @apiNote
     * -return 200 ok con contenido
     * -return 409 Conflict si ya existe un almacén con el mismo nombre
     * -return 400 si no se puede crear el registro , validaciones
     */

    public AlmacenParabrisaDto create(AlmacenParabrisaDto almacenpDTO);




    /**
     *  encuentra por id
     * @param  id el UUID del almacén a buscar
     *
     * @apiNote
     *  -return 400 si no encuentra el registrro
     *  -return 200 ok con contenido
     * */
    public AlmacenParabrisaDto findById(UUID id);



    /**
     * Actualiza el registro de almacén.
     *
     * @param id el UUID del almacén a actualizar
     * @param almacenPDTO el AlmacenDTO con los datos actualizados
     * @return el AlmacenDTO actualizado
     *
     * @apiNote
     * - 200 OK si se actualiza correctamente
     * - 404 Not Found si no se encuentra el almacén
     * - 409 Conflict si ya existe un almacén con el mismo nombre
     */
    public AlmacenParabrisaDto update(UUID id, AlmacenParabrisaDto almacenPDTO);


    /**
     * elimina registros de almacen por id
     *
     * @param id
     *
     * @return un ResponseEntity vacio
     *
     * @apiNote
     *
     * -return 202 ok si se elimina correctamente
     * -return 404 si no se encuentra el registro
     */
    public ResponseEntity<?> delete(UUID id);



    public AlmacenParabrisaDto convertToDTO(AlmacenParabrisa almacenP);

    public AlmacenParabrisaDto findByParabrisaIdAndAlmacenId(UUID parabrisaId, UUID almacenId);


    public ResponseEntity<List<AlmacenParabrisaDto>> findAll();

}
