package com.ProyectoPerfulandia.Perfulandia.controller;

import com.ProyectoPerfulandia.Perfulandia.model.Perfume;
import com.ProyectoPerfulandia.Perfulandia.service.PerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/perfumes")
public class PerfumeController {
    @Autowired
    private PerfumeService perfumeService;

    @GetMapping
    public List<Perfume> getListPerfumes() {
        return perfumeService.getPerfumes();
    }
    @PostMapping
    public Perfume guardarPerfume(@RequestBody Perfume perfume) {
        return perfumeService.guardarPerfume(perfume);
    }
    @GetMapping("{id}")
    public Perfume getPerfumePorId(@PathVariable int id) {
        return perfumeService.getPerfume(id);
    }
    @PutMapping("{id}")
    public Perfume editarPerfume(@PathVariable int id, @RequestBody Perfume perfume) {
        return perfumeService.editarPerfume(perfume);
    }
    @DeleteMapping("{id}")
    public String eliminarPerfume(@PathVariable int id) {
        return perfumeService.deletePerfume(id);
    }
}
