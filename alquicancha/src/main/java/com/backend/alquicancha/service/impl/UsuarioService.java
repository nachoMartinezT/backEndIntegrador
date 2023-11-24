package com.backend.alquicancha.service.impl;

import com.backend.alquicancha.dto.UsuarioDto;
import com.backend.alquicancha.entity.Usuario;
import com.backend.alquicancha.exceptions.BadRequestException;
import com.backend.alquicancha.exceptions.ResourceNotFoundException;
import com.backend.alquicancha.repository.IUsuarioRepository;
import com.backend.alquicancha.service.IUsuarioService;
import com.backend.alquicancha.utils.JsonPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);
    private final IUsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public UsuarioService(IUsuarioRepository usuarioRepository, ObjectMapper objectMapper) {
        this.usuarioRepository = usuarioRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public UsuarioDto guardarUsuario(Usuario usuario) throws BadRequestException {
        Usuario usuarioNuevo = usuarioRepository.save(usuario);
        UsuarioDto usuarioDtoNuevo = objectMapper.convertValue(usuarioNuevo, UsuarioDto.class);

        usuarioDtoNuevo.setAdmin(false);

        if (usuarioNuevo == null) {
            LOGGER.error("Error al registrar el usuario");
            throw new BadRequestException("Error al registrar el usuario");
        }
        LOGGER.info("Nuevo usuario registrado con exito: {}", JsonPrinter.toString(usuarioDtoNuevo));

        return usuarioDtoNuevo;
    }

    @Override
    public UsuarioDto buscarUsuarioPorId(Long id)  {
        Usuario usuarioBuscado = usuarioRepository.findById(id).orElse(null);
        UsuarioDto usuarioDto = null;
        if (usuarioBuscado != null) {
            usuarioDto = objectMapper.convertValue(usuarioBuscado, UsuarioDto.class);
            LOGGER.info("Usuario encontrado: {}", JsonPrinter.toString(usuarioDto));
        } else {
            LOGGER.info("El id no se encuentra registrado en la base de datos");

        }

        return usuarioDto;
    }


    @Override
    public UsuarioDto actualizarUsuario(Usuario usuario, long id) throws ResourceNotFoundException {
        Usuario usuarioAActualizar = usuarioRepository.findById(id).orElse(null);
        UsuarioDto usuarioActualizadoDto = null;

        if (usuarioAActualizar != null) {
            usuarioAActualizar.setNombre(usuario.getNombre());
            usuarioAActualizar.setApellido(usuario.getApellido());
            usuarioAActualizar.setEmail(usuario.getEmail());
            usuarioAActualizar.setPassword(usuario.getPassword());
            usuarioAActualizar.setTelefono(usuario.getTelefono());
            usuarioAActualizar.setDni(usuario.getDni());
            usuarioAActualizar.setAdmin(usuario.isAdmin());
            usuarioAActualizar.setFechaIngreso(usuario.getFechaIngreso());
            usuarioAActualizar.setCalle(usuario.getCalle());
            usuarioAActualizar.setNumero(usuario.getNumero());
            usuarioAActualizar.setLocalidad(usuario.getLocalidad());

            usuarioRepository.save(usuarioAActualizar);
            usuarioActualizadoDto = objectMapper.convertValue(usuarioAActualizar, UsuarioDto.class);
            LOGGER.info("Usuario actualizado con exito: {}", JsonPrinter.toString(usuarioActualizadoDto));
        } else {
            LOGGER.error("No fue posible actualizar los datos ya que el usuario no se encuentra registrado");
            throw new ResourceNotFoundException("No fue posible actualizar los datos ya que el usuario no se encuentra registrado");
        }

        return usuarioActualizadoDto;
    }

    @Override
    public UsuarioDto loginUsuario(String mail, String pass) {
        List<Usuario> usuarios = usuarioRepository.findAll();

        Optional<Usuario> usuarioEncontrado = usuarios.stream()
                .filter(usuario -> usuario.getEmail().equalsIgnoreCase(mail) && usuario.getPassword().equals(pass))
                .findFirst();

        if (usuarioEncontrado.isPresent()) {
            Usuario usuario = usuarioEncontrado.get();
            UsuarioDto usuarioDto = objectMapper.convertValue(usuario, UsuarioDto.class);
            LOGGER.info("Usuario encontrado: {}", JsonPrinter.toString(usuarioDto));
            return usuarioDto;
        } else {
            LOGGER.info("Login erroneo, no coinciden los datos");
            return null;
        }
    }

    @Override
    public boolean buscarUsuarioPorMail(String email) {
        List<Usuario> usuarios = usuarioRepository.findAll();

        // Utilizando el método equalsIgnoreCase para comparar correos electrónicos sin importar mayúsculas o minúsculas
        boolean existe = usuarios.stream().anyMatch(usuario -> usuario.getEmail().equalsIgnoreCase(email));

        if (existe) {
            System.out.println("Existe");
        } else {
            System.out.println("No existe");
        }

        return existe;
    }

    @Override
    public UsuarioDto actualizarUsuario(Usuario usuario) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public List<UsuarioDto> listarUsuarios() {

        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDto> usuarioDtos = usuarios.stream()
                .map(usuario -> {
                    return new UsuarioDto(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getPassword(), usuario.getTelefono(), usuario.getDni(), usuario.isAdmin(), usuario.getFechaIngreso(), usuario.getCalle(), usuario.getNumero(), usuario.getLocalidad());
                })
                .toList();

        return usuarioDtos;
    }

    @Override
    public void eliminarUsuario(Long id) throws ResourceNotFoundException {
        if (buscarUsuarioPorId(id) != null) {
            usuarioRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el usuario con id {}", id);
        } else {
            LOGGER.error("No se ha encontrado el usuario con id {} " + id);
            throw new ResourceNotFoundException("No se ha encontrado el usuario con id " + id);
        }
    }
}
