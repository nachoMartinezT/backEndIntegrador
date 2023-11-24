package com.backend.alquicancha.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 15, message = "El nombre debe tener hasta 15 caracteres")
    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "Debe especificarse un nombre")
    private String nombre;


    private String imagen;

    @ManyToMany
    private Set<Producto> productos = new HashSet<>();


    public Categoria() {
    }

    public Categoria(String nombre, String imagen, Set<Producto> productos) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.productos = productos;
    }
    public Categoria(String nombre, Set<Producto> productos) {
        this.nombre = nombre;
        this.productos = productos;
    }

    public Categoria(String nombre, String imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }


    public Long getId() {
        return id;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

    public void addProducto(Producto producto) {
        this.productos.add(producto);
    }

    public void removeProducto(Producto producto) {
        this.productos.remove(producto);
    }


    @Override
    public String toString() {
        return "\n ID: " + id + " - Categoria: " + nombre + " " + " Imagen: " + imagen + ".";
    }
}
