package com.ProyectoPerfulandia.Perfulandia.repository;

import com.ProyectoPerfulandia.Perfulandia.model.Categoria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoriaRepository {
    //GUARDARÁ LAS CATEGORIAS
    private List<Categoria> ListCategorias = new ArrayList<>();

    //RETORNA TODAS LAS CATEGORIAS
    public List<Categoria> getListCategorias() {
        return ListCategorias;
    }

    //BUSCAR CATEGORIA POR SU ID
    public Categoria buscarPorId(int id) {
        for (Categoria categoria : ListCategorias) {
            if (categoria.getId() == id) {
                return categoria;
            }
        }
        return null;
    }

    //BUSCAR CATEGORIA POR SU NOMBRE
    public Categoria buscarPorNombre(String nombre) {
        for (Categoria categoria : ListCategorias) {
            if (categoria.getNombre().equals(nombre)) {
                return categoria;
            }
        }
        return null;
    }

    //GUARDAR CATEGORIA
    public Categoria guardarCategoria(Categoria categoria) {
        ListCategorias.add(categoria);
        return categoria;
    }
    //EDITAR CATEGORIA
    public Categoria editarCategoria(Categoria categoria) {
        int id = 0;
        int idPosicion = 0;

        for (int i = 0; i < ListCategorias.size(); i++) {
            if (ListCategorias.get(i).getId() == categoria.getId()) {
                id = categoria.getId();
                idPosicion = i;
            }
        }
        Categoria categoria1 = new Categoria();
        categoria1.setId(id);
        categoria1.setNombre(categoria.getNombre());
        categoria1.setDescripcion(categoria.getDescripcion());

        ListCategorias.set(idPosicion, categoria1);
        return categoria1;
    }
    //ELIMINAR CATEGORIA
    public void borrarCategoria(int id) {
        Categoria categoria = buscarPorId(id);
        if (categoria != null) {
            ListCategorias.remove(categoria);
        }
    }
}
