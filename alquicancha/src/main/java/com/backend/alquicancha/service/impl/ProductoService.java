package com.backend.alquicancha.service.impl;


import com.backend.alquicancha.dto.ProductoDto;
import com.backend.alquicancha.entity.Categoria;
import com.backend.alquicancha.entity.Especificacion;
import com.backend.alquicancha.entity.Imagen;
import com.backend.alquicancha.entity.Producto;
import com.backend.alquicancha.exceptions.BadRequestException;
import com.backend.alquicancha.exceptions.ResourceNotFoundException;
import com.backend.alquicancha.repository.ICategoriaRepository;
import com.backend.alquicancha.repository.IEspecificacionRepository;
import com.backend.alquicancha.repository.IImagenRepository;
import com.backend.alquicancha.repository.IProductoRepository;
import com.backend.alquicancha.service.IProductoService;
import com.backend.alquicancha.utils.JsonPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
public class ProductoService implements IProductoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductoService.class);
    private final IProductoRepository productRepository;
    private final ObjectMapper mapper;
    private final IImagenRepository imagenRepository;
    private final IEspecificacionRepository especificacionRepository;
    private final ICategoriaRepository categoriaRepository;

    @Autowired
    public ProductoService(IProductoRepository productRepository, ObjectMapper mapper, IImagenRepository imagenRepository, IEspecificacionRepository especificacionRepository, ICategoriaRepository categoriaRepository) {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.imagenRepository = imagenRepository;
        this.especificacionRepository = especificacionRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public ProductoDto guardarProducto(@Valid ProductoDto productoDto) throws BadRequestException {
        Producto producto = mapper.convertValue(productoDto, Producto.class);
        ProductoDto savedProductoDto = mapper.convertValue(productRepository.save(producto), ProductoDto.class);

        if (savedProductoDto == null) {
            LOGGER.error("Error al registrar el product");
            throw new BadRequestException("Error al registrar el product");
        }

        LOGGER.info("Nuevo productoregistrado con exito: {}", JsonPrinter.toString(savedProductoDto));
        return savedProductoDto;
    }

    @Override
    public List<ProductoDto> listarProductos() {
        List<ProductoDto> productoDtos = productRepository.findAll().stream().map(producto-> mapper.convertValue(producto, ProductoDto.class)).toList();
        LOGGER.info("Lista de todos los productos: {}", JsonPrinter.toString(productoDtos));

        return productoDtos;
    }

    @Override
    public void eliminarProducto(Long id) throws ResourceNotFoundException {
        if (buscarProducto(id) != null) {
            productRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el pruducto con id {}", id);
        } else {
            LOGGER.error("No se ha encontrado el pruducto con id " + id);
            throw new ResourceNotFoundException("No se ha encontrado el pruducto con id " + id);
        }
    }

    @Override
    public ProductoDto buscarProducto(Long id)   {
        Producto productoBuscado = productRepository.findById(id).orElse(null);
        ProductoDto productoDto = null;
        if (productoBuscado != null) {
            productoDto = mapper.convertValue(productoBuscado, ProductoDto.class);
            LOGGER.info("Producto encontrado: {}", JsonPrinter.toString(productoDto));
        } else {
            LOGGER.info("El producto con id: " + id + " no existe en la base de datos");

        }
        return productoDto;
    }

    @Override
    public ProductoDto actualizarProducto(Producto producto, Long id) throws ResourceNotFoundException {
        Producto productoAActualizar = productRepository.findById(id).orElse(null);
        ProductoDto productoActualizadoDto = null;

        if(productoAActualizar != null){
            productoAActualizar.setTitle(producto.getTitle());
            productoAActualizar.setDescription(producto.getDescription());
            productoAActualizar.setPrice(producto.getPrice());
            productoAActualizar.setImagenes(producto.getImagenes());

            productRepository.save(productoAActualizar);
            productoActualizadoDto = mapper.convertValue(productoAActualizar, ProductoDto.class);
            LOGGER.info("Producto actualizado con exito: {}", JsonPrinter.toString(productoActualizadoDto));

        } else {
            LOGGER.info("No se pudo actualizar, el producto no se encuentra registrado.");
            throw new ResourceNotFoundException("No fue posible encontrar el producto");
        }

        return productoActualizadoDto;
    }

    @Override
    public void agregarCategoria(Long id, Categoria categoria) throws ResourceNotFoundException {
        Producto producto = productRepository.findById(id).orElse(null);
        if (producto != null) {
            producto.agregarCategoria(categoria);
            productRepository.save(producto);
            LOGGER.info("Se ha agregado la categoria {} al producto con id {}", categoria, id);
        } else {
            LOGGER.error("No se ha encontrado el producto con id " + id);
            throw new ResourceNotFoundException("No se ha encontrado el producto con id " + id);
        }
    }

    @Override
    public void eliminarCategoria(Long id, Categoria categoria) throws ResourceNotFoundException {
        Producto producto = productRepository.findById(id).orElse(null);
        if (producto != null) {
            producto.removerCategoria(categoria);
            productRepository.save(producto);
            LOGGER.info("Se ha eliminado la categoria {} al producto con id {}", categoria, id);
        } else {
            LOGGER.error("No se ha encontrado el producto con id " + id);
            throw new ResourceNotFoundException("No se ha encontrado el producto con id " + id);
        }
    }

    @Override
    public List<ProductoDto> filtrarPorCategoria(Long id) throws Exception {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        if (categoria != null) {
            List<ProductoDto> productos = productRepository.findByCategorias(categoria).stream().map(producto-> mapper.convertValue(producto, ProductoDto.class)).toList();
            LOGGER.info("Se ha encontrado la categoria "+categoria + " en los productos " + productos);
            return productos;
        } else {
            LOGGER.error("No se ha encontrado el producto con id " + id);
            throw new ResourceNotFoundException("No se ha encontrado el producto con id " + id);
        }
    }

  /*  @Override
    public ProductDto actualizarProducto(@Valid ProductDto product, long id) throws ResourceNotFoundException {
        productoproductoActualizar = productRepository.findById(id).orElse(null);
        ProductDto productoDtoActualizado = null;

        if (productoActualizar != null) {
            //productoActualizar = product;
            //productRepository.save(productoActualizar);
            //productoDtoActualizado = mapper.convertValue(productoActualizar, ProductDto.class);
            LOGGER.info("Producto actualizado con exito: {}", JsonPrinter.toString(productoDtoActualizado));
        } else {
            LOGGER.info("No se pudo actualizar, el producto no se encuentra registrado.");
            throw new ResourceNotFoundException("No fue posible encontrar el producto");
        }
        return productoDtoActualizado;
    }

   */

    public void uploadProductImage(Long productId, MultipartFile photo, String description) throws IOException {
        // Create a folder to store the photos
        String photosDir = "./src/main/resources/static/photos";
        File photosDirFile = new File(photosDir);
        if (!photosDirFile.exists()) {
            photosDirFile.mkdirs();
        }

        // Get the file from the request
        String fileName = photo.getOriginalFilename();
        byte[] bytes = photo.getBytes();

        // Write the file to the directory
        FileOutputStream fileOutputStream = new FileOutputStream(new File(photosDir, fileName));
        fileOutputStream.write(bytes);
        fileOutputStream.close();

        // Associate the photo with the product

        Producto producto = productRepository.findById(productId).orElse(null);

        Imagen photoo = new Imagen(fileName, "/photos/" + fileName, "Photo of " + producto.getTitle());
        Imagen photoObj = imagenRepository.save(photoo);

        producto.agregarImagen(photoObj);

        productRepository.save(producto);
        System.out.println("Product photos: " + producto.getImagenes());

    }

    @Override
    public Set<Imagen> getPhotos(Long productId){
        Producto producto = productRepository.findById(productId).orElse(null);
        return producto.getImagenes();
    }

    @Override
    public int getPhotosCount(Long productId){
        Producto producto = productRepository.findById(productId).orElse(null);
        return producto.getImagenes().size();
    }

    @Override
    public void deletePhoto(Long productId, Long photoId){
        Producto producto = productRepository.findById(productId).orElse(null);
        Imagen imagen = imagenRepository.findById(photoId).orElse(null);
        producto.removerImagen(imagen);
        productRepository.save(producto);
    }

    @Override
    public void agregarEspecificacion(Long id, Especificacion especificacion) throws ResourceNotFoundException {
        Producto producto = productRepository.findById(id).orElse(null);
        if (producto != null) {
            producto.agregarEspecificacion(especificacion);
            productRepository.save(producto);
            LOGGER.info("Se ha agregado la especificacion {} al producto con id {}", especificacion, id);
        } else {
            LOGGER.error("No se ha encontrado el producto con id " + id);
            throw new ResourceNotFoundException("No se ha encontrado el producto con id " + id);
        }
    }

    @Override
    public void eliminarEspecificacion(Long id, Especificacion especificacion) throws ResourceNotFoundException {
        Producto producto = productRepository.findById(id).orElse(null);
        if (producto != null) {
            producto.removerEspecificacion(especificacion);
            productRepository.save(producto);
            LOGGER.info("Se ha eliminado la especificacion {} al producto con id {}", especificacion, id);
        } else {
            LOGGER.error("No se ha encontrado el producto con id " + id);
            throw new ResourceNotFoundException("No se ha encontrado el producto con id " + id);
        }
    }

    @Override
    public List<ProductoDto> filtrarPorEspecificacion(Long id) throws Exception {
        Especificacion especificacion= especificacionRepository.findById(id).orElse(null);
        if (especificacion != null) {
            List<ProductoDto> productos = productRepository.findByEspecificaciones(especificacion).stream().map(productoo-> mapper.convertValue(productoo, ProductoDto.class)).toList();
            LOGGER.info("Se ha encontrado la especificacion "+especificacion + " en los productos " + productos);
            return productos;
        } else {
            LOGGER.error("No se ha encontrado el producto con id " + id);
            throw new ResourceNotFoundException("No se ha encontrado el producto con id " + id);
        }
    }
}
