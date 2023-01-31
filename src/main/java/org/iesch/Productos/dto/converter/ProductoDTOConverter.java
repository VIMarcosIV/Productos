package org.iesch.Productos.dto.converter;

import lombok.RequiredArgsConstructor;
import org.iesch.Productos.dto.CreateProductoDTO;
import org.iesch.Productos.dto.ProductoDTO;
import org.iesch.Productos.modelo.Producto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
// Para que esta etiqueta funcione tiene que ser final las variables que querais que se autoinstancien como con @Autowired
@RequiredArgsConstructor
public class ProductoDTOConverter {

    private final ModelMapper modelMapper;

    public ProductoDTO convertToDTO(Producto producto) {
        return modelMapper.map(producto, ProductoDTO.class);
    }

    public Producto convertDesdeDTO (CreateProductoDTO CreateProductoDTO){
        return  modelMapper.map(CreateProductoDTO, Producto.class);
    }

}
