package org.example.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "producto")
@Getter
@Setter
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "idProducto")
    private int idProducto;

    @Column(name = "nombre", length = 250)
    private String nombre;

    @Column(name = "descripcion", length = 250)
    private String descripcion;

    @Column(name = "precio")
    private double precio;

    @Column(name = "imagen", length = 250)
    private String imagen;

    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    private List<LineaPedido> lineaPedido;



    public Producto(String nombre, String descripcion, double precio, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
    }

    @Override
    public String toString(){
        return "PRODUCTO: "+
                "\nNOMBRE: "+nombre+
                "\nDESCRIPCION: "+descripcion+
                "\nPRECIO: "+precio+
                "\nIMAGEN: "+imagen;
    }
}
