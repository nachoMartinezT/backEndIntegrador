package com.backend.alquicancha.controller;

import com.backend.alquicancha.entity.Categoria;
import com.backend.alquicancha.service.ICategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/categories")
public class CategoriaController {

    @Autowired
    private ICategoriaService categoriaService;

    @Operation(summary = "Buscar categoria por id")
    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarCategoriaPorId(@RequestParam Long id){
        return ResponseEntity.ok(categoriaService.buscarCategoriaPorId(id));
    }

    @Operation(summary = "Listar categorias")
    @GetMapping
    public List<Categoria> listarCategorias(){
        return categoriaService.listarCategorias();
    }

    @Operation(summary = "Guardar categoria")
    @PostMapping("/save")
    public ResponseEntity<Object> guardarCategoria(@RequestParam String nombre){
        categoriaService.guardarCategoria(nombre);
        return ResponseEntity.ok("Categoria guardada con exito");
    }

    @Operation(summary = "Eliminar categoria")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> eliminarCategoria(@RequestParam Long id){
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.ok("Categoria eliminada con exito");
    }

    @Operation(summary = "Actualizar categoria")
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> actualizarCategoria(@RequestParam Long id,@RequestBody Categoria categoria){
        categoriaService.actualizarCategoria(id, categoria);
        return ResponseEntity.ok("Categoria actualizada con exito");
    }



}
