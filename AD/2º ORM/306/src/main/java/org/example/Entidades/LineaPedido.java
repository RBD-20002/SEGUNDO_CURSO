package org.example.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lineapedido")
@Getter
@Setter
@NoArgsConstructor
public class LineaPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLineaPedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPedido")
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProducto")
    private Producto producto;

    @Column(name = "cantidad")
    private int cantidad;



    public LineaPedido(Pedido pedido, Producto producto, int cantidad) {
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    @Override
    public String toString(){
        return "LINEA DE PEDIDO: "+
                "\nPEDIDO ID: "+pedido.getIdPedido()+
                "\nPRODUCTO ID: "+producto.getIdProducto()+
                "\nCANTIDAD: "+cantidad;
    }
}
