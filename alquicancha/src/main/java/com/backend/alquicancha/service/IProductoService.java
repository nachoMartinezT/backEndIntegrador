package com.backend.alquicancha.service;

import com.backend.alquicancha.dto.ProductoDto;
import com.backend.alquicancha.entity.Categoria;
import com.backend.alquicancha.entity.Especificacion;
import com.backend.alquicancha.entity.Imagen;
import com.backend.alquicancha.entity.Producto;
import com.backend.alquicancha.exceptions.BadRequestException;
import com.backend.alquicancha.exceptions.ResourceNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface IProductoService {

    ProductoDto guardarProducto(@Valid ProductoDto productoDto) throws BadRequestException;

    List<ProductoDto> listarProductos();

    void eliminarProducto(Long id) throws ResourceNotFoundException;

    ProductoDto buscarProducto(Long id) ;

    ProductoDto actualizarProducto(Producto producto, Long id) throws ResourceNotFoundException;

    void agregarCategoria(Long id, Categoria categoria) throws ResourceNotFoundException;

    void eliminarCategoria(Long id, Categoria categoria) throws ResourceNotFoundException;

    List<ProductoDto> filtrarPorCategoria(Long id) throws Exception;

    void uploadProductImage(Long productId, MultipartFile photo, String description) throws IOException;

    Set<Imagen> getPhotos(Long productoId);

    int getPhotosCount(Long productoId);

    void deletePhoto(Long productoId, Long imagenId);


    void agregarEspecificacion(Long id, Especificacion especificacion) throws ResourceNotFoundException;

    void eliminarEspecificacion(Long id, Especificacion especificacion) throws ResourceNotFoundException;

    List<ProductoDto> filtrarPorEspecificacion(Long id) throws Exception;
}
