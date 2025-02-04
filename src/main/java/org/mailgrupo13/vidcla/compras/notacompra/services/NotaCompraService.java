package org.mailgrupo13.vidcla.compras.notacompra.services;

import org.mailgrupo13.vidcla.compras.notacompra.dtos.NotaCompraDTO;
import org.mailgrupo13.vidcla.compras.notacompra.entities.NotaCompra;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface NotaCompraService {

    /**
     * crear registro de almacen
     *
     * @param notaCompraDTO el AlmacenDTO con los datos del almacén a crear
     * @apiNote -return 200 ok con contenido
     * -return 409 Conflict si ya existe un almacén con el mismo nombre
     * -return 400 si no se puede crear el registro , validaciones
     */

    public NotaCompra create(NotaCompraDTO notaCompraDTO);




    /**
     *  encuentra por id
     * @param  id el UUID del almacén a buscar
     *
     * @apiNote
     *  -return 400 si no encuentra el registrro
     *  -return 200 ok con contenido
     * */
    public NotaCompraDTO findById(UUID id);



    /**
     * Actualiza el registro de almacén.
     *
     * @param id el UUID del almacén a actualizar
     * @param notaCompraDTO el AlmacenDTO con los datos actualizados
     * @return el AlmacenDTO actualizado
     *
     * @apiNote
     * - 200 OK si se actualiza correctamente
     * - 404 Not Found si no se encuentra el almacén
     * - 409 Conflict si ya existe un almacén con el mismo nombre
     */
    public NotaCompraDTO update(UUID id, NotaCompraDTO notaCompraDTO);


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



    public NotaCompraDTO convertToDTO(NotaCompra notaCompra);

   public NotaCompra convertToEntity(NotaCompraDTO notaCompraDTO);

    public ResponseEntity<List<NotaCompraDTO>> findAll();




}
