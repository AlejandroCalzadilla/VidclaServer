package org.mailgrupo13.vidcla.ventas.cliente.dtos;
import java.util.UUID;
import lombok.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private UUID   id;
    private String nombre;
    private String apellido;
    private String ci;
    private String telefono;
    private String direccion;
    private String email;
    private String nit;
}
