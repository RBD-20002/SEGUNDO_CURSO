package org.example.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "telefono")
@Getter
@Setter
@NoArgsConstructor
public class Telefono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTelefono")
    private int idTelefono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @Column(name = "descripcion", length = 100)
    private String descripcion;

    @Column(name = "numero", length = 20)
    private String numero;


    public Telefono(Cliente cliente, String descripcion, String numero) {
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.numero = numero;
    }

    @Override
    public String toString(){
        return "TELEFONO: "+
                "\nID: "+idTelefono+
                "\nID CLIENTE: "+cliente.getIdCliente()+
                "\nDESCRIPCION: "+descripcion+
                "\nNUMERO: "+numero;
    }
}
