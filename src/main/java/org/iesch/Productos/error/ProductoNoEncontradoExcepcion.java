package org.iesch.Productos.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductoNoEncontradoExcepcion extends RuntimeException {

    private static final long serialVersionUID = 16555545L;
    public ProductoNoEncontradoExcepcion(Long id) {
        super("No se puede encontrar el producto con la ID: " + id);
    }

}
