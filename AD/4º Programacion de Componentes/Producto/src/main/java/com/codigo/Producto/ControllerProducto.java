package com.codigo.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ControllerProducto {

    private final ServiceProducto serviceProducto;

    @Autowired
    public ControllerProducto(ServiceProducto serviceProducto){
        this.serviceProducto = serviceProducto;
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> obtenerProductos(){
        List<ProductoDTO> productos = serviceProducto.obtenerProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerProductoId(@PathVariable Long id){
        ProductoDTO producto = serviceProducto.obtenerProductoId(id);
        if(producto != null){
            return ResponseEntity.ok(producto);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> agregarProducto(@RequestBody ProductoDTO producto){
        serviceProducto.agregarProducto(producto);
        return ResponseEntity.ok().body("Producto agregado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        ProductoDTO producto = serviceProducto.obtenerProductoId(id);
        if(producto != null){
            serviceProducto.eliminarProducto(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
