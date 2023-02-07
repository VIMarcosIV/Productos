package org.iesch.Productos.controlador;

import org.iesch.Productos.dto.CreateProductoDTO;
import org.iesch.Productos.dto.ProductoDTO;
import org.iesch.Productos.dto.converter.ProductoDTOConverter;
import org.iesch.Productos.error.ProductoNoEncontradoExcepcion;
import org.iesch.Productos.modelo.Producto;
import org.iesch.Productos.repositorio.CategoriaRepositorio;
import org.iesch.Productos.repositorio.ProductoRepositorio;
import org.iesch.Productos.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductoControlador {

    @Autowired
    ProductoServicio productoServicio;
    @Autowired
    ProductoRepositorio productoRepositorio;

    @Autowired
    ProductoDTOConverter productoDTOConverter;

    @Autowired
    CategoriaRepositorio categoriaRepositorio;

    // SELECT
    @GetMapping("api/producto/{id}")
    public Producto buscaPorId(@PathVariable Long id) {
        return productoRepositorio.findById(id).orElseThrow(() -> new ProductoNoEncontradoExcepcion(id));
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
    // Código (204 --> Delete correctamente)
    @DeleteMapping("api/producto/{id}")
    public ResponseEntity<?> borraProducto(@PathVariable Long id) {
        productoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Acciones con los DTO

    @CrossOrigin(origins = "http://localhost:9999")
    @GetMapping("api/productoDTO")
    public ResponseEntity<?> obtenerTodosATravesDelDTO() {
        List<Producto> result = productoRepositorio.findAll();

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<ProductoDTO> listaDTO = result.stream().map(
                    productoDTOConverter::convertToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(listaDTO);
        }
    }

    @PostMapping("api/productoDTO")
    public ResponseEntity<?> insertarProductoDTO(@RequestBody CreateProductoDTO nuevo) {
//        Producto productoNuevo = new Producto();
//        productoNuevo.setNombre(nuevo.getNombre());
//        productoNuevo.setPrecio(nuevo.getPrecio());
//        Categoria categoria = categoriaRepositorio.findById(nuevo.getCategoriaId()).orElse(null);
//        productoNuevo.setCategoria(categoria);

        Producto productoNuevo = productoDTOConverter.convertDesdeDTO(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoRepositorio.save(productoNuevo));
    }
}
