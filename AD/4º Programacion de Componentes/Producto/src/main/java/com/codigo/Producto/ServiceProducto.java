package com.codigo.Producto;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceProducto {

    public final List<ProductoDTO> productos = new ArrayList<>();
    private Long idCounter = 1L;

    public List<ProductoDTO> obtenerProductos(){
        return productos;
    }

    public ProductoDTO obtenerProductoId(Long id){
        return productos.stream().filter(productoDTO -> productoDTO.getId().equals(id)).findFirst().orElse(null);
    }

    public void agregarProducto(ProductoDTO producto){
        producto.setId(idCounter++);
        productos.add(producto);
    }

    public void eliminarProducto(Long id){
        for(int i=0; i< productos.size(); i++){
            if(productos.get(i).getId().equals(id)){
                productos.remove(i);
                break;
            }
        }
    }
}
