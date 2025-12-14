package org.example.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedido")
@Setter
@Getter
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;

    @Column(name = "fecha", columnDefinition = "TIMESTAMP")
    private Date fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LineaPedido> lineaPedidos;

    public Pedido(Date fecha, Cliente cliente) {
        this.fecha = fecha;
        this.cliente = cliente;
    }


    @Override
    public String toString(){
        return "PEDIDO: "+
                "\nID: "+idPedido+
                "\nFECHA: "+fecha+
                "\nCLIENTE ID: "+cliente.getIdCliente();
    }
}
