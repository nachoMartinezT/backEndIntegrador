package com.backend.alquicancha.repository;

import com.backend.alquicancha.entity.Categoria;
import com.backend.alquicancha.entity.Especificacion;
import com.backend.alquicancha.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
    @Query("SELECT p FROM Producto p WHERE p.especificaciones IN (:especificaciones)")
    public List<Producto> findByEspecificaciones(Especificacion especificacion) throws Exception;

    @Query("SELECT p FROM Producto p WHERE p.categorias IN (:categorias)")
    List<Producto> findByCategorias(Categoria categoria) throws Exception;
}
