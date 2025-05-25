package com.ProyectoPerfulandia.Perfulandia.service;

import com.ProyectoPerfulandia.Perfulandia.model.Categoria;
import com.ProyectoPerfulandia.Perfulandia.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getCategorias() {
        return categoriaRepository.getListCategorias();
    }
    public Categoria getCategoria(int id) {
        return categoriaRepository.buscarPorId(id);
    }
    public Categoria getCategoriaPorNombre(String nombre) {
        return categoriaRepository.buscarPorNombre(nombre);
    }
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.guardarCategoria(categoria);
    }
    public Categoria editarCategoria(Categoria categoria) {
        return categoriaRepository.editarCategoria(categoria);
    }
    public String eliminarCategoria(int id) {
        categoriaRepository.borrarCategoria(id);
        return "Categoria eliminada";
    }
}
