package com.backend.alquicancha.service;

import com.backend.alquicancha.dto.ReservaDto;
import com.backend.alquicancha.entity.Reserva;
import com.backend.alquicancha.exceptions.BadRequestException;
import com.backend.alquicancha.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IReservaService {
    ReservaDto guardarTurno(Reserva reserva) throws BadRequestException;

    List<ReservaDto> listarTodos();

    ReservaDto buscarTurnoPorId(Long id) throws ResourceNotFoundException;

    ReservaDto actualizarTurno(Reserva reserva) throws ResourceNotFoundException, BadRequestException;

    void eliminarTurno(Long id) throws ResourceNotFoundException;
}