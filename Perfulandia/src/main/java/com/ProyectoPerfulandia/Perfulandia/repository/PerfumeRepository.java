package com.ProyectoPerfulandia.Perfulandia.repository;


import com.ProyectoPerfulandia.Perfulandia.model.Perfume;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, Integer> {
    Perfume findBySku(int sku);
}

