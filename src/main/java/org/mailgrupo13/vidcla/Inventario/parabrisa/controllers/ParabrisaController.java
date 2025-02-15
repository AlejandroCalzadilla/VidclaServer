package org.mailgrupo13.vidcla.Inventario.parabrisa.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.mailgrupo13.vidcla.Inventario.parabrisa.documentacion.ParabrisaApiDoc;
import org.mailgrupo13.vidcla.imagenes.ImagenService;
import org.mailgrupo13.vidcla.Inventario.parabrisa.dto.ParabrisaDTO;
import org.mailgrupo13.vidcla.Inventario.parabrisa.services.ParabrisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/parabrisa")
@Validated
public class ParabrisaController implements ParabrisaApiDoc {

    @Autowired
    private ParabrisaService windshieldService;

    @Autowired
    private ImagenService imagenesService;


    @GetMapping
    public List<ParabrisaDTO> FindAllWindshields() {
        return windshieldService.findAll();
    }






    /*@Operation(summary = "encontrar un parabrisa por id", description = "Retorna un parabrisa  si lo encuentra, otherwise returns 404")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parabrisa Econtrado"),
            @ApiResponse(responseCode = "404", description = "Parabrisa no Encontrado", content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"status\": 404, \"message\": \"Parabrisa con  id 1 not encontrado\", \"data\": null}")
            ))
    })*/
    @GetMapping("/{id}")
    public ParabrisaDTO findById(@PathVariable UUID id) {
         return windshieldService.findById(id);
    }


    /*@Operation(summary = "crear un parabrisa",
            description = "no es necesario enviar el creadoen y actualizaden omitir esos valores ",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value =
                                    "{\"arriba\": 20, \"abajo\": 20, \"costado\": 20, \"medio\": 20, \"observacion\": \"string\", \"categoriaId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\", \"posicionId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"}"
                            )
                    )
            )
    )*/
    @PostMapping
    public ParabrisaDTO createParabrisa(@Valid @RequestBody ParabrisaDTO windshieldDTO) {
        System.out.println(windshieldDTO.toString() + "llega el dto");
        return windshieldService.create(windshieldDTO);

    }


    @PutMapping("/{id}")
    public ParabrisaDTO updateWindshield(@PathVariable UUID id, @Valid @RequestBody ParabrisaDTO windshieldDTO) {
        return windshieldService.update(id, windshieldDTO);
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWindshield(@PathVariable UUID id) {

        return windshieldService.delete(id);
    }



    /*@PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        return imagenesService.CargarImagen(file);
    }*/



    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String filename) {
        return imagenesService.DescargarImagen(filename);
    }



}