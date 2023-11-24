package com.backend.alquicancha.service;

import com.backend.alquicancha.dto.UsuarioDto;
import com.backend.alquicancha.entity.Usuario;
import com.backend.alquicancha.exceptions.BadRequestException;
import com.backend.alquicancha.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IUsuarioService {

    UsuarioDto guardarUsuario(Usuario usuario) throws BadRequestException;

    List<UsuarioDto> listarUsuarios();

    void eliminarUsuario(Long id) throws ResourceNotFoundException;

    UsuarioDto buscarUsuarioPorId(Long id);

    UsuarioDto actualizarUsuario(Usuario usuario, long id) throws ResourceNotFoundException;

    UsuarioDto loginUsuario(String mail, String pass);

    boolean buscarUsuarioPorMail(String email);

    UsuarioDto actualizarUsuario(Usuario usuario) throws ResourceNotFoundException;
}
