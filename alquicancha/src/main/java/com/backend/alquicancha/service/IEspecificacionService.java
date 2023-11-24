package com.backend.alquicancha.service;

import com.backend.alquicancha.entity.Especificacion;

import java.util.List;

public interface IEspecificacionService {
    Especificacion buscarEspecificacionPorId(Long id);

    List<Especificacion> listarEspecificaciones();

    Especificacion guardarEspecificacion(String nombre);

    void eliminarEspecificacion(Long id);

    Especificacion actualizarEspecificacion(Long id, Especificacion especificacion);
}
