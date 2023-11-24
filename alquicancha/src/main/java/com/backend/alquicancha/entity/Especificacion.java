package com.backend.alquicancha.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Especificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToMany
    private Set<Producto> productos = new HashSet<>();

    public Especificacion() {
    }
    public Especificacion(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "Especificacion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +

                '}';
    }
}
