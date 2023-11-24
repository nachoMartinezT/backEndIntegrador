package com.backend.alquicancha.controller;

import com.backend.alquicancha.dto.ProductoDto;
import com.backend.alquicancha.dto.UsuarioDto;
import com.backend.alquicancha.exceptions.BadRequestException;
import com.backend.alquicancha.exceptions.ResourceNotFoundException;
import com.backend.alquicancha.service.IUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")

public class UsuarioController {

    private IUsuarioService usuarioService;

    @Autowired
    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // LIST
    @Operation(summary = "Listado de todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado correcto",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductoDto.class))}),
            @ApiResponse(responseCode = "404", description = "Usuarios no encontrados",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error",
                    content = @Content)
    })
    @GetMapping
    public List<UsuarioDto> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    // READ
    @Operation(summary = "Busqueda de un usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductoDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id invalido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> buscarusuarioPorId(@PathVariable Long id) {
        return new ResponseEntity<>(usuarioService.buscarUsuarioPorId(id), null, HttpStatus.OK);
    }

    @Operation(summary = "Login de usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario logueado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductoDto.class))}),
            @ApiResponse(responseCode = "400", description = "Datos invalidos",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Loggin no correcto",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error",
                    content = @Content)
    })
    @GetMapping("/login/{mail}:{pass}")
    public ResponseEntity<UsuarioDto> loginUsuario(@PathVariable String mail, @PathVariable String pass) {
        return new ResponseEntity<>(usuarioService.loginUsuario(mail, pass), null, HttpStatus.OK);
    }

    @Operation(summary = "Busqueda de un mail si es existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "mail encontrado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Boolean.class))}),
            @ApiResponse(responseCode = "400", description = "Mail invalido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Mail no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error",
                    content = @Content)
    })
    @GetMapping("/email/{mail}")
    public ResponseEntity<Boolean> buscarUsuarioPorMail(@PathVariable String mail) {
        return new ResponseEntity<>(usuarioService.buscarUsuarioPorMail(mail), null, HttpStatus.OK);
    }

    // CREATE
    @Operation(summary = "Creación de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id invalido",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error",
                    content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<UsuarioDto> registrarUsuario(@Valid @RequestBody com.backend.alquicancha.entity.Usuario usuario) throws BadRequestException {
        return new ResponseEntity<>(usuarioService.guardarUsuario(usuario), null, HttpStatus.OK);
    }

    // UPDATE
    @Operation(summary = "Modificación de un usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario modificado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id invalido",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error",
                    content = @Content)
    })
    @CrossOrigin
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<UsuarioDto> actualizarUsuario(@Valid @RequestBody com.backend.alquicancha.entity.Usuario usuario, @PathVariable long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(usuarioService.actualizarUsuario(usuario, id), null, HttpStatus.OK);
    }

    // DELETE
    @Operation(summary = "Eliminación de un usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductoDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id invalido",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error",
                    content = @Content)
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) throws ResourceNotFoundException {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok("Usuario eliminado");

    }
}
