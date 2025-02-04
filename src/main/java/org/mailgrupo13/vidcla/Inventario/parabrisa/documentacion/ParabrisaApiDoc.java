package org.mailgrupo13.vidcla.Inventario.parabrisa.documentacion;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.mailgrupo13.vidcla.Inventario.parabrisa.dto.ParabrisaDTO;

import java.util.UUID;

public interface ParabrisaApiDoc {
    @Operation(summary = "Encontrar un parabrisas por ID", description = "Retorna un parabrisas si lo encuentra, de lo contrario devuelve un codigo htto 404")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parabrisas encontrado"),
            @ApiResponse(responseCode = "404", description = "Parabrisas no encontrado", content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"status\": 404, \"message\": \"Parabrisas con ID 1 no encontrado\", \"data\": null}")
            ))
    })
    ParabrisaDTO findById(UUID id);
}
