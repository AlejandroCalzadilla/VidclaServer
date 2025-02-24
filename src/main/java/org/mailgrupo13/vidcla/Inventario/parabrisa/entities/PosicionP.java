package org.mailgrupo13.vidcla.Inventario.parabrisa.entities;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import  lombok.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PosicionP {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nombre;
    private String codigo;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;
    @OneToMany(mappedBy = "posicion")
    private List<Parabrisa> parabrisas;


}
