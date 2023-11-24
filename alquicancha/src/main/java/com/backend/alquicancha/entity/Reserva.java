package com.backend.alquicancha.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "RESERVAS")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    @NotNull(message = "El usuario no puede ser nulo")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @NotNull(message = "El producto no puede ser nulo")
    private Producto producto;
    @DateTimeFormat(pattern = "yyyy-MM-dd")//Pasar "2023-08-25"
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha y hora del turno")
    private LocalDate fechaDesde;

    @DateTimeFormat(pattern = "yyyy-MM-dd")//Pasar "2023-08-25"
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha y hora del turno")
    private LocalDate fechaHasta;




    public Reserva(Usuario usuario, Producto producto, LocalDate fecha, LocalDate fechaHasta) {
        this.usuario = usuario;
        this.producto = producto;
        this.fechaDesde = fecha;
        this.fechaHasta = fechaHasta;
    }

    public Reserva() {
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public LocalDate getFecha() {
        return fechaDesde;
    }

    public void setFecha(LocalDate fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public LocalDate getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(LocalDate fechaHasta) {
        this.fechaHasta = fechaHasta;
    }



    @Override
    public String toString() {
        return "Turno id: " + id +
                ", usuario: " + usuario +
                ", producto: " + producto +
                ", fecha desde: " + fechaDesde +
                ", fecha hasta: " + fechaHasta;
    }


}
