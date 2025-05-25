package com.ProyectoPerfulandia.Perfulandia.controller;

import com.ProyectoPerfulandia.Perfulandia.model.Categoria;
import com.ProyectoPerfulandia.Perfulandia.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> obtenerCategorias(){
        return categoriaService.getCategorias();
    }
    @PostMapping
    public Categoria guardarCategoria(@RequestBody Categoria categoria){
        return categoriaService.guardarCategoria(categoria);
    }
    @GetMapping("{id}")
    public Categoria obtenerCategoria(@PathVariable int id){
        return categoriaService.getCategoria(id);
    }
    @PutMapping("{id}")
    public Categoria editarrCategoria(@PathVariable int id, @RequestBody Categoria categoria){
        return categoriaService.editarCategoria(categoria);
    }
    @DeleteMapping("{id}")
    public String eliminarCategoria(@PathVariable int id){
        return categoriaService.eliminarCategoria(id);
    }
}
