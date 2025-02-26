package org.mailgrupo13.vidcla.Inventario.parabrisa.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaP {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private  String nombre;
    private String codigo;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;
    @OneToMany(mappedBy = "categoria")
    private List<Parabrisa> parabrisas;



    @PrePersist
    protected void onCreate() {
        this.creadoEn = java.time.LocalDateTime.now();
        this.actualizadoEn = java.time.LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.actualizadoEn = java.time.LocalDateTime.now();
    }


}
