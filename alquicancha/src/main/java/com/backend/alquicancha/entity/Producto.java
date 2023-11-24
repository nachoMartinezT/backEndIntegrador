package com.backend.alquicancha.entity;

import com.backend.alquicancha.exceptions.ValidarNumero;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50, message = "El titulo debe tener hasta 50 caracteres")
    @NotNull(message = "El titulo no puede ser nulo")
    @NotBlank(message = "Debe especificarse un titulo")
    private String title;

    @Size(max = 350, message = "La descripción debe tener hasta 350 caracteres")
    @NotNull(message = "La descripción no puede ser nulo")
    @NotBlank(message = "Debe especificarse una descripción")
    private String description;

    @ValidarNumero(message = "El precio debe ser solo numeros")
    @Min(1)
    @NotNull(message = "El precio no puede ser nula")
    private double price;

    @ManyToMany(mappedBy = "productos", fetch = FetchType.EAGER)
    private Set<Categoria> categorias;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Imagen> imagenes = new HashSet<>();

    @ManyToMany(mappedBy = "productos", fetch = FetchType.EAGER)
    private Set<Especificacion> especificaciones;

    public Producto() {
    }

    public Producto(String title, String description, double price,Set<Categoria> categorias ,Set<Imagen> imagenes, Set<Especificacion> especificaciones) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.categorias = categorias;
        this.imagenes = imagenes;
        this.especificaciones = especificaciones;
    }

    public Producto(String title, String description, double price, Set<Imagen> imagenes) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.imagenes = imagenes;
    }

    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(Set<Imagen> imagens) {
        this.imagenes = imagens;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }

    public void agregarCategoria(Categoria categoria){
        if (categoria != null && !this.categorias.contains(categoria)) {
            this.categorias.add(categoria);
        }
    }

    public void removerCategoria(Categoria categoria){
        if (categoria != null) {
            this.categorias.remove(categoria);
        }
    }

    public void agregarImagen(Imagen imagen){
        if (imagen != null && !imagenes.contains(imagen)) {
            imagenes.add(imagen);
            imagen.setProduct(this);
        }
    }

    public void removerImagen(Imagen imagen){
        if (imagen != null) {
            imagenes.remove(imagen);
        }
    }

    public void agregarEspecificacion(Especificacion especificacion){
        if (especificacion != null && !this.especificaciones.contains(especificacion)) {
            this.especificaciones.add(especificacion);
        }
    }

    public void removerEspecificacion(Especificacion especificacion){
        if (especificacion != null) {
            this.especificaciones.remove(especificacion);
        }
    }
    @Override
    public String toString() {
        return "\n ID: " + id + " - Producto: " + title + " " + description + " Precio: " + price + ".";
    }
}
