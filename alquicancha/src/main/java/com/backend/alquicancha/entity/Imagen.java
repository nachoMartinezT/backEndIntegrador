package com.backend.alquicancha.entity;

import javax.persistence.*;

@Entity
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    public Imagen() {
    }
    public Imagen(String name, String url, String description) {
        this.name = name;
        this.url = url;
        this.description = description;
    }

    public Imagen(Imagen photo) {
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Producto getProduct() {
        return producto;
    }

    public void setProduct(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", product_id=" + (producto != null ? producto.getId() : null) +
                '}';
    }
}
