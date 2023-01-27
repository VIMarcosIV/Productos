package org.iesch.Productos.controlador;

import org.iesch.Productos.modelo.Producto;
import org.iesch.Productos.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoControlador {

    @Autowired
    ProductoServicio productoServicio;

    // SELECT
    @GetMapping("api/producto")
    public List<Producto> obtenerTodos() {
        return productoServicio.buscaTodosLosProductos();
    }

    @GetMapping("api/producto/{id}")
    public Producto buscaPorId(@PathVariable Long id) {
        return productoServicio.buscaPorId(id);
    }

    // INSERT
    @PostMapping("api/producto")
    public Producto insertarProducto(@RequestBody Producto producto) {
        return productoServicio.guardaProducto(producto);
    }

    // UPDATE
    @PutMapping("api/producto/{id}")
    public Producto editarProducto(@RequestBody Producto producto, @PathVariable Long id) {
        return productoServicio.updateProducto(producto);
    }

    // DELETE
    @DeleteMapping("api/producto/{id}")
    public Producto borraProducto(@PathVariable Long id){
        return productoServicio.deleteProducto(id);
    }

}
