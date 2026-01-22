package org.example.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "factura")
@Setter
@Getter
@NoArgsConstructor
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFactura")
    private int idFactura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "precio")
    private double precio;

    @Column(name = "fecha", columnDefinition = "DATE")
    private Date fecha;



    public Factura(Cliente cliente, String descripcion, double precio, Date fecha) {
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fecha = new Date();
    }

    @Override
    public String toString(){
        return "FACTURA: "+
                "\nID FACTURA: "+idFactura+
                "\nID CLIENTE: "+ cliente.getIdCliente()+
                "\nDESCRIPCION: "+descripcion+
                "\nPRECIO: "+precio+
                "\nFECHA: "+fecha;
    }
}
