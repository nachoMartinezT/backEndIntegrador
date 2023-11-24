package com.backend.alquicancha.service.impl;

import com.backend.alquicancha.entity.Categoria;
import com.backend.alquicancha.repository.ICategoriaRepository;
import com.backend.alquicancha.service.ICategoriaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService implements ICategoriaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductoService.class);

    @Autowired
    private ICategoriaRepository categoriaRepository;


    @Override
    public Categoria buscarCategoriaPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        return categoria;
    }

    @Override
    public List<Categoria> listarCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias;
    }

    @Override
    public Categoria guardarCategoria(String nombre) {
        Categoria categoria = new Categoria(nombre);
        categoriaRepository.save(categoria);
        LOGGER.info("Categoria guardada con exito");
        return categoria;
    }

    @Override
    public void eliminarCategoria(Long id) {
        if (categoriaRepository.findById(id) != null) {
            categoriaRepository.deleteById(id);
            LOGGER.info("Categoria eliminada con exito");
        } else {
            LOGGER.error("Error al eliminar la categoria");
        }
    }

    @Override
    public Categoria actualizarCategoria(Long id, Categoria categoria) {
        Categoria categoriaBuscada = categoriaRepository.findById(id).orElse(null);
        categoriaBuscada.setNombre(categoria.getNombre());
        categoriaBuscada.setProductos(categoria.getProductos());
        categoriaRepository.save(categoriaBuscada);
        LOGGER.info("Categoria actualizada con exito");
        return categoriaBuscada;
    }
}
