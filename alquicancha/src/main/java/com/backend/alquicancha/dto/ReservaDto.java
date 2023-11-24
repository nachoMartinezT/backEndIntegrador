package com.backend.alquicancha.dto;

import com.backend.alquicancha.entity.Reserva;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservaDto {

    private Long id;
    private Long idUsuario;
    private Long idProducto;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate fecha;

    public ReservaDto() {
    }

    public ReservaDto(Long id, Long idUsuario,Long idProducto,LocalDate fecha) {

        this.id = id;
        this.idUsuario = idUsuario;
        this.idProducto = idProducto;
        this.fecha = fecha;

    }

    public ReservaDto(UsuarioDto usuarioTurno, ProductoDto productoTurno, LocalDate fecha) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuario() {
        return idUsuario;
    }

    public void setUsuario(Long usuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long producto) {
        this.idProducto = idProducto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public static ReservaDto fromTurno(Reserva reserva) {
        Long usuario = reserva.getUsuario().getId();
        Long producto = reserva.getProducto().getId();
        return new ReservaDto(reserva.getId(), usuario, producto, reserva.getFecha());
    }
}
