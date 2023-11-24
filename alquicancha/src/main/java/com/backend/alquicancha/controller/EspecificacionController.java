package com.backend.alquicancha.controller;

import com.backend.alquicancha.entity.Especificacion;
import com.backend.alquicancha.service.IEspecificacionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/especificaciones")
public class EspecificacionController {

    @Autowired
    private IEspecificacionService especificacionService;

    @Operation(summary = "Buscar especificación por id")
    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarEspecificacionPorId(@RequestParam Long id){
        return ResponseEntity.ok(especificacionService.buscarEspecificacionPorId(id));
    }

    @Operation(summary = "Listar especificaciones")
    @GetMapping
    public ResponseEntity<Object> listarEspecificaciones(){
        return ResponseEntity.ok(especificacionService.listarEspecificaciones());
    }

    @Operation(summary = "Guardar especificación")
    @PostMapping("/guardar")
    public ResponseEntity<Object> guardarEspecificacion(@RequestParam String nombre){
        especificacionService.guardarEspecificacion(nombre);
        return ResponseEntity.ok("Especificacion guardada con exito");
    }

    @Operation(summary = "Eliminar especificación")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Object> eliminarEspecificacion(@RequestParam Long id){
        especificacionService.eliminarEspecificacion(id);
        return ResponseEntity.ok("Especificacion eliminada con exito");
    }

    @Operation(summary = "Actualizar especificación")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Object> actualizarEspecificacion(@RequestParam Long id,@RequestBody Especificacion especificacion){
        especificacionService.actualizarEspecificacion(id, especificacion);
        return ResponseEntity.ok("Especificacion actualizada con exito");
    }

}
