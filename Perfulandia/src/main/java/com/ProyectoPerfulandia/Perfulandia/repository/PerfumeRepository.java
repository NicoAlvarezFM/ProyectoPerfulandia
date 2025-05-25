package com.ProyectoPerfulandia.Perfulandia.repository;


import com.ProyectoPerfulandia.Perfulandia.model.Perfume;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PerfumeRepository {
    //GUARDARÁ LOS PERFUMES
    private List<Perfume> ListPerfumes = new ArrayList<>();

    //RETORNA TODOS LOS PERFUMES
    public List<Perfume> getListPerfumes() {
        return ListPerfumes;
    }
    //BUSCAR PERFUME POR SU ID
    public Perfume buscarPorId(int id) {
        for (Perfume perfume : ListPerfumes) {
            if (perfume.getId() == id) {
                return perfume;
            }
        }
        return null;
    }
    //BUSCAR PERFUME POR SU SKU
    public Perfume buscarPorSku(int sku) {
        for (Perfume perfume : ListPerfumes) {
            if (perfume.getSku() == sku) {
                return perfume;
            }
        }
        return null;
    }
    //GUARDAR PERFUME
    public Perfume guardarPerfume(Perfume perfume) {
        ListPerfumes.add(perfume);
        return perfume;
    }
    //EDITAR PERFUME
    public Perfume editarPerfume(Perfume perfume) {
        int id = 0;
        int idPosicion = 0;

        for (int i = 0; i<ListPerfumes.size(); i++) {
            if (ListPerfumes.get(i).getId() == perfume.getId()) {
                id = perfume.getId();
                idPosicion = i;
            }
        }
        Perfume perfume1 = new Perfume();
        perfume1.setId(id);
        perfume1.setSku(perfume.getSku());
        perfume1.setNombre(perfume.getNombre());
        perfume1.setTipo(perfume.getTipo());
        perfume1.setDescripcion(perfume.getDescripcion());
        perfume1.setPrecio(perfume.getPrecio());
        perfume1.setCantidad(perfume.getCantidad());

        ListPerfumes.set(idPosicion, perfume1);
        return perfume1;
    }
    //ELIMINAR PERFUME
    public void borrarPerfume(int id) {
        Perfume perfume = buscarPorId(id);
        if  (perfume != null) {
            ListPerfumes.remove(perfume);
        }
    }
}

