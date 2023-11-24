package com.backend.alquicancha.service.impl;

import com.backend.alquicancha.entity.Categoria;
import com.backend.alquicancha.entity.Especificacion;
import com.backend.alquicancha.repository.IEspecificacionRepository;
import com.backend.alquicancha.service.IEspecificacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecificacionService implements IEspecificacionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductoService.class);

    @Autowired
    private IEspecificacionRepository especificacionRepository;

    @Override
    public Especificacion buscarEspecificacionPorId(Long id) {
        Especificacion especificacion = especificacionRepository.findById(id).orElse(null);
        return especificacion;
    }

    @Override
    public List<Especificacion> listarEspecificaciones() {
        List<Especificacion> especificaciones = especificacionRepository.findAll();
        return especificaciones;
    }

    @Override
    public Especificacion guardarEspecificacion(String nombre) {
        Especificacion especificacion = new Especificacion(nombre);
        especificacionRepository.save(especificacion);
        LOGGER.info("Especificación guardada con exito");
        return especificacion;
    }

    @Override
    public void eliminarEspecificacion(Long id) {
        if (especificacionRepository.findById(id) != null) {
            especificacionRepository.deleteById(id);
            LOGGER.info("Especificación eliminada con exito");
        } else {
            LOGGER.error("Error al eliminar la especificación");
        }
    }

    @Override
    public Especificacion actualizarEspecificacion(Long id, Especificacion especificacion) {
        Especificacion especificacionBuscada = especificacionRepository.findById(id).orElse(null);
        especificacionBuscada.setNombre(especificacion.getNombre());
        especificacionBuscada.setProductos(especificacion.getProductos());
        especificacionRepository.save(especificacion);
        LOGGER.info("Especificación actualizada con exito");
        return especificacionBuscada;
    }



}
