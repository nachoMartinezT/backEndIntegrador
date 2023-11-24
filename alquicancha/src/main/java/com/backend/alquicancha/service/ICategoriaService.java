package com.backend.alquicancha.service;

import com.backend.alquicancha.entity.Categoria;

import java.util.List;

public interface ICategoriaService {
    Categoria buscarCategoriaPorId(Long id);

    List<Categoria> listarCategorias();

    Categoria guardarCategoria(String nombre);

    void eliminarCategoria(Long id);

    Categoria actualizarCategoria(Long id, Categoria categoria);


}
