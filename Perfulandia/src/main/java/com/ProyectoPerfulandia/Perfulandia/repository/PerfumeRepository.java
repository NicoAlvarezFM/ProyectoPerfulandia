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
    //ACTUALIZAR PERFUME

}

