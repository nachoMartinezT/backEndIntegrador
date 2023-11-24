package com.backend.alquicancha.dto;

import com.backend.alquicancha.entity.Imagen;

public class ImagenDto {
    private Long id;
    private String name;
    private String url;
    private String description;

    public ImagenDto() {
    }
    public ImagenDto(Imagen imagen) {
        this.id = imagen.getId();
        this.name = imagen.getName();
        this.url = imagen.getUrl();
        this.description = imagen.getDescription();
    }
}
