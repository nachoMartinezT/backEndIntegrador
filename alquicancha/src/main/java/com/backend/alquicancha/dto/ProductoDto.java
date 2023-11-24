package com.backend.alquicancha.dto;

import com.backend.alquicancha.entity.Categoria;
import com.backend.alquicancha.entity.Producto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductoDto {

    private Long id;
    private String title;
    private String description;
    private double price;

    private Set<Categoria> categorias;
    private Set<ImagenDto> imagenes = new HashSet<>();

    public ProductoDto(Producto producto) {
        this.id = producto.getId();
        this.title = producto.getTitle();
        this.description = producto.getDescription();
        this.price = producto.getPrice();
        this.categorias = producto.getCategorias();
        this.imagenes = producto.getImagenes().stream().map(ImagenDto::new).collect(Collectors.toSet());
    }

    public ProductoDto() {
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public Set<ImagenDto> getImagenes() {
        return imagenes;
    }

    public void setImagenes(Set<ImagenDto> imagenes) {
        this.imagenes = imagenes;
    }


}
