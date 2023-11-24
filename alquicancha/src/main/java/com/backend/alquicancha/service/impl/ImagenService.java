package com.backend.alquicancha.service.impl;

import com.backend.alquicancha.dto.ImagenDto;
import com.backend.alquicancha.entity.Imagen;
import com.backend.alquicancha.repository.IImagenRepository;
import com.backend.alquicancha.service.IImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagenService implements IImagenService {
    @Autowired
    private IImagenRepository imagenRepository;

    @Override
    public ImagenDto buscarImagenPorId(Long id){
        Imagen findPhoto = imagenRepository.findById(id).orElse(null);
        ImagenDto photoDTO = new ImagenDto(findPhoto);
        return photoDTO;
    }

    @Override
    public ImagenDto registrarImagen(String name, String url, String description){
        Imagen imagen = new Imagen(name, url, description);
        imagenRepository.save(imagen);
        ImagenDto imagenDto = new ImagenDto(imagen);
        return imagenDto;
    }

    @Override
    public void eliminar(Long id){
        imagenRepository.deleteById(id);
    }

    @Override
    public ImagenDto editarImagen(Long productId,Long photoId, Imagen imagen){
        Imagen imagenBuscada = imagenRepository.findById(photoId).orElse(null);
        imagenBuscada.setName(imagen.getName());
        imagenBuscada.setUrl(imagen.getUrl());
        imagenBuscada.setDescription(imagen.getDescription());
        imagenRepository.save(imagenBuscada);
        ImagenDto imagenDto = new ImagenDto(imagenBuscada);
        return imagenDto;
    }
}