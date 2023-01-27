package org.iesch.Productos.controlador;

import org.iesch.Productos.modelo.Producto;
import org.iesch.Productos.repositorio.ProductoRepositorio;
import org.iesch.Productos.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    // Código 201 --> Insertado correctamente
    @PostMapping("api/producto")
    public ResponseEntity<?> insertarProducto(@RequestBody Producto producto) {
        Producto salvado = productoRepositorio.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvado);

    }

    // UPDATE
    // Código (200 --> OK), (404 --> No se ha encontrado el producto)

    @PutMapping("api/producto/{id}")
    public ResponseEntity<?> editarProducto(@RequestBody Producto producto, @PathVariable Long id) {
        /*
        return productoRepositorio.findById(id).map(productoEditado -> {
            productoEditado.setNombre(producto.getNombre());
            productoEditado.setPrecio(producto.getPrecio());
            return ResponseEntity.ok(productoRepositorio.save(productoEditado));
        }).orElseGet(() -> {
            return ResponseEntity.notFound().build();
        });
         */

        if (productoRepositorio.existsById(id)) {
            producto.setId(id);
            Producto actualizado = productoRepositorio.save(producto);
            return ResponseEntity.ok(productoRepositorio.save(actualizado));
        } else {
            return ResponseEntity.notFound().build();

        }
    }

    // DELETE
    @DeleteMapping("api/producto/{id}")
    public ResponseEntity<?> borraProducto(@PathVariable Long id) {
        productoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
