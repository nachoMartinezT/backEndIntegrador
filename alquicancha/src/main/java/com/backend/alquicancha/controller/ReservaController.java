package com.backend.alquicancha.controller;

import com.backend.alquicancha.dto.ProductoDto;
import com.backend.alquicancha.dto.ReservaDto;
import com.backend.alquicancha.exceptions.BadRequestException;
import com.backend.alquicancha.exceptions.ResourceNotFoundException;
import com.backend.alquicancha.service.IReservaService;
import com.backend.alquicancha.service.impl.ProductoService;
import com.backend.alquicancha.service.impl.UsuarioService;
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


@RestController
@RequestMapping("/reservas")
@CrossOrigin
public class ReservaController {
    private IReservaService reservaService;
    private UsuarioService usuarioService;
    private ProductoService productoService;

    @Autowired
    public ReservaController(IReservaService turnoService) {
        this.reservaService = turnoService;
    }

    @Operation(summary = "Listado de todos los turnos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado correcto",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductoDto.class))}),
            @ApiResponse(responseCode = "404", description = "Turnos no encontrados",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error",
                    content = @Content)
    })
    @GetMapping()
    public List<ReservaDto> listarTurnos() {
        return reservaService.listarTodos();
    }

    // CREATE
    @Operation(summary = "Creación de un turno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Turno creado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductoDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id invalido",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error",
                    content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<ReservaDto> guardarTurno(@Valid @RequestBody com.backend.alquicancha.entity.Reserva reserva) throws BadRequestException {

        return new ResponseEntity<>(reservaService.guardarTurno(reserva), null, HttpStatus.CREATED);
    }

    // READ
    @Operation(summary = "Busqueda de un turno por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turno encontrado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductoDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id invalido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ReservaDto> buscarTurnoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(reservaService.buscarTurnoPorId(id), null, HttpStatus.OK);
    }

    // DELETE
    @Operation(summary = "Eliminación de un turno por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Turno eliminado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductoDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id invalido",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error",
                    content = @Content)
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        reservaService.eliminarTurno(id);
        return ResponseEntity.ok("Turno eliminado");
    }

    // UPDATE
    @Operation(summary = "Modificación de un turno por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turno modificado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductoDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id invalido",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error",
                    content = @Content)
    })
    @PutMapping("/actualizar")
    public ResponseEntity<ReservaDto> actualizarTurno(@Valid @RequestBody com.backend.alquicancha.entity.Reserva reserva) throws ResourceNotFoundException, BadRequestException {
        return new ResponseEntity<>(reservaService.actualizarTurno(reserva), null, HttpStatus.OK);
    }
}
