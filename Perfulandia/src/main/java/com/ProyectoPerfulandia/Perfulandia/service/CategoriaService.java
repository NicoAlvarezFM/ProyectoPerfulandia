package com.ProyectoPerfulandia.Perfulandia.service;

import com.ProyectoPerfulandia.Perfulandia.model.Categoria;
import com.ProyectoPerfulandia.Perfulandia.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Listar todas las categorías
    public List<Categoria> getCategorias() {
        return categoriaRepository.findAll();
    }

    // Buscar categoría por ID
    public Optional<Categoria> getCategoria(int id) {
        return categoriaRepository.findById(id);
    }

    // Guardar o actualizar categoría
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // Eliminar categoría por ID
    public void deleteCategoria(int id) {
        categoriaRepository.deleteById(id);
    }
}
