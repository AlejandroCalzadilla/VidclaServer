package org.mailgrupo13.vidcla.ventas.cliente.entitites;
import jakarta.persistence.*;
import  lombok.*;
import org.mailgrupo13.vidcla.compras.notacompra.entities.NotaCompra;
import org.mailgrupo13.vidcla.ventas.notaventa.entities.NotaVenta;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cliente",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nombre","apellido"}),
})
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="nombre", nullable = false)
    private String nombre;

    @Column(name="apellido", nullable = false)
    private String apellido;

    @Column(name="ci", nullable = false,unique = true)
    private String ci;

    @Column(name="telefono", nullable = false,unique = true)
    private String telefono;

    @Column(name="direccion", nullable = false)
    private String direccion;

    @Column(name="email", nullable = false,unique = true)
    private String email;

    @Column(name="nit", nullable = true,unique = true)
    private String nit;

    @OneToMany(mappedBy = "cliente")
    private List<NotaVenta> notasVenta;





}
