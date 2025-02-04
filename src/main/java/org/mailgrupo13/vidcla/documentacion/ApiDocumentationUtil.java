package org.mailgrupo13.vidcla.documentacion;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public class ApiDocumentationUtil {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parabrisa Encontrado"),
            @ApiResponse(responseCode = "404", description = "Parabrisa no Encontrado", content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"status\": 404, \"message\": \"Parabrisa con id 1 no encontrado\", \"data\": null}")
            ))
    })
    public static ApiResponses getParabrisaApiResponses() {
        // Este método no necesita lógica adicional, ya que las anotaciones se aplican directamente.
        return null; // O puedes devolver un objeto ApiResponses si es necesario.
    }




}
