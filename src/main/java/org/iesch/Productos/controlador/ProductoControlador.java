package org.iesch.Productos.controlador;

import org.iesch.Productos.modelo.Producto;
import org.iesch.Productos.repositorio.ProductoRepositorio;
import org.iesch.Productos.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoControlador {

    @Autowired
    ProductoServicio productoServicio;
    @Autowired
    ProductoRepositorio productoRepositorio;

    // SELECT
    @GetMapping("api/producto/{id}")
    public ResponseEntity<?> buscaPorId(@PathVariable Long id) {
        Producto result = productoRepositorio.findById(id).orElse(null);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("api/producto")
    public ResponseEntity<?> obtenerTodos() {
        List<Producto> result = productoRepositorio.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
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
    public Producto borraProducto(@PathVariable Long id) {
        return productoServicio.deleteProducto(id);
    }

}
