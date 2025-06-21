package com.ProyectoPerfulandia.Perfulandia.service;

import com.ProyectoPerfulandia.Perfulandia.model.Perfume;
import com.ProyectoPerfulandia.Perfulandia.repository.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfumeService {

    @Autowired
    private PerfumeRepository perfumeRepository;

    // Listar todos los perfumes
    public List<Perfume> getPerfumes() {
        return perfumeRepository.findAll();
    }

    // Buscar perfume por ID
    public Optional<Perfume> getPerfume(int id) {
        return perfumeRepository.findById(id);
    }

    // Buscar perfume por SKU
    public Perfume getPerfumePorSku(int sku) {
        return perfumeRepository.findBySku(sku);
    }

    // Guardar perfume
    public Perfume guardarPerfume(Perfume perfume) {
        return perfumeRepository.save(perfume);
    }

    // Eliminar perfume por ID
    public void deletePerfume(int id) {
        perfumeRepository.deleteById(id);
    }
}
