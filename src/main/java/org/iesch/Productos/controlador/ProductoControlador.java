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

    @GetMapping("api/producto")
    public List<Producto> obtenerTodos() {
        return productoServicio.buscaTodosLosProductos();
    }

    @PostMapping("api/producto")
    public Producto insertarProducto(@RequestBody Producto producto) {
        Producto actorSalvado = productoServicio.guardaProducto(producto);
        return actorSalvado;
    }

    @GetMapping("api/producto/{id}")
    public Producto buscaPorId(@PathVariable int id) {
        return productoServicio.buscaPorId(String.valueOf(id));
    }

    @PutMapping("api/producto/{id}")
    public Producto editarProducto(@RequestBody Producto producto, @PathVariable int id) {
        Producto productoEditar = productoServicio.updateProducto(producto);
        return productoEditar;
    }


}
