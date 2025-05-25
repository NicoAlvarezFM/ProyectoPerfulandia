package com.ProyectoPerfulandia.Perfulandia.controller;

import com.ProyectoPerfulandia.Perfulandia.model.Perfume;
import com.ProyectoPerfulandia.Perfulandia.service.PerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/perfumes")
public class PerfumeController {

    @Autowired
    private PerfumeService perfumeService;


    @GetMapping
    public List<Perfume> obtenerTodos() {
        return perfumeService.getPerfumes();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Perfume> obtenerPorId(@PathVariable int id) {
        return perfumeService.getPerfume(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/sku/{sku}")
    public ResponseEntity<Perfume> obtenerPorSku(@PathVariable int sku) {
        Perfume p = perfumeService.getPerfumePorSku(sku);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }


    @PostMapping
    public Perfume crear(@RequestBody Perfume perfume) {
        return perfumeService.guardarPerfume(perfume);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Perfume> actualizar(@PathVariable int id, @RequestBody Perfume perfume) {
        return perfumeService.getPerfume(id).map(p -> {
            perfume.setId(id);
            return ResponseEntity.ok(perfumeService.guardarPerfume(perfume));
        }).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        perfumeService.deletePerfume(id);
        return ResponseEntity.ok("Perfume eliminado con éxito");
    }
}