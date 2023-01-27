package org.iesch.Productos.controlador;

import org.iesch.Productos.modelo.Categoria;
import org.iesch.Productos.repositorio.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoriaControlador {
    @Autowired
    CategoriaRepositorio categoriaRepositorio;
    
    // SELECT
    @GetMapping("api/categoria/{id}")
    public ResponseEntity<?> buscaPorId(@PathVariable Long id) {
        Categoria result = categoriaRepositorio.findById(id).orElse(null);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("api/categoria")
    public ResponseEntity<?> obtenerTodos() {
        List<Categoria> result = categoriaRepositorio.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    // INSERT
    // Código 201 --> Insertado correctamente
    @PostMapping("api/categoria")
    public ResponseEntity<?> insertarProducto(@RequestBody Categoria producto) {
        Categoria salvado = categoriaRepositorio.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvado);

    }

    // UPDATE
    // Código (200 --> OK), (404 --> No se ha encontrado el producto)

    @PutMapping("api/categoria/{id}")
    public ResponseEntity<?> editarProducto(@RequestBody Categoria producto, @PathVariable Long id) {
        /*
        return productoRepositorio.findById(id).map(productoEditado -> {
            productoEditado.setNombre(producto.getNombre());
            productoEditado.setPrecio(producto.getPrecio());
            return ResponseEntity.ok(productoRepositorio.save(productoEditado));
        }).orElseGet(() -> {
            return ResponseEntity.notFound().build();
        });
         */

        if (categoriaRepositorio.existsById(id)) {
            producto.setId(id);
            Categoria actualizado = categoriaRepositorio.save(producto);
            return ResponseEntity.ok(categoriaRepositorio.save(actualizado));
        } else {
            return ResponseEntity.notFound().build();

        }
    }

    // DELETE
    @DeleteMapping("api/categoria/{id}")
    public ResponseEntity<?> borraProducto(@PathVariable Long id) {
        categoriaRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
