package org.iesch.Productos.servicio;

import org.iesch.Productos.modelo.Producto;
import org.iesch.Productos.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicio {
    @Autowired
    ProductoRepositorio productoRepositorio;

    public List<Producto> buscaTodosLosProductos() {
        return productoRepositorio.findAll();
    }

    public Producto guardaProducto(Producto producto) {
        return productoRepositorio.save(producto);
    }

    public Producto buscaPorId(Long id) {
        Optional<Producto> productoOptional = productoRepositorio.findById(id);
        if (productoOptional.isPresent()) {
            return productoOptional.get();
        } else {
            return null;
        }
    }

    public Producto updateProducto(Producto producto) {
        if (productoRepositorio.existsById(producto.getId())) {
            producto.setId(producto.getId());
            return productoRepositorio.save(producto);
        } else {
            return null;
        }
    }

    public Producto deleteProducto(Long id) {
        if(productoRepositorio.existsById(id)){
            Producto result = productoRepositorio.findById(id).get();
            productoRepositorio.deleteById(id);
            return result;
        }else{
        return null;
        }
    }
}
