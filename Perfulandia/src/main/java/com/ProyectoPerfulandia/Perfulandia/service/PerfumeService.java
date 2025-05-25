package com.ProyectoPerfulandia.Perfulandia.service;

import com.ProyectoPerfulandia.Perfulandia.model.Perfume;
import com.ProyectoPerfulandia.Perfulandia.repository.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfumeService {
    @Autowired
    private PerfumeRepository perfumeRepository;

    public List<Perfume> getPerfumes() {
        return perfumeRepository.getListPerfumes();
    }
    public Perfume getPerfume(int id) {
        return perfumeRepository.buscarPorId(id);
    }
    public Perfume getPerfumePorSku(int sku) {
        return perfumeRepository.buscarPorSku(sku);
    }
    public Perfume guardarPerfume(Perfume perfume) {
        return perfumeRepository.guardarPerfume(perfume);
    }
    public Perfume editarPerfume(Perfume perfume) {
        return perfumeRepository.editarPerfume(perfume);
    }
    public String deletePerfume(int id) {
        perfumeRepository.borrarPerfume(id);
        return "Perfume eliminado con exito";
    }
}
