package com.backend.alquicancha.service;

import com.backend.alquicancha.dto.ImagenDto;
import com.backend.alquicancha.entity.Imagen;

public interface IImagenService {
    ImagenDto buscarImagenPorId(Long id);
    ImagenDto registrarImagen(String name, String url, String description);
    void eliminar(Long id);
    ImagenDto editarImagen(Long productoId,Long imagenId, Imagen imagen);
}
