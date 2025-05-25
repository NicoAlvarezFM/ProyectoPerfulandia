package com.ProyectoPerfulandia.Perfulandia.service;

import com.ProyectoPerfulandia.Perfulandia.model.Stock;
import com.ProyectoPerfulandia.Perfulandia.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    // Listar todos los stocks
    public List<Stock> getStocks() {
        return stockRepository.findAll();
    }

    // Buscar stock por ID
    public Optional<Stock> getStock(int id) {
        return stockRepository.findById((long) id);
    }

    // Guardar o actualizar stock
    public Stock guardarStock(Stock stock) {
        return stockRepository.save(stock);
    }

    // Eliminar stock por ID
    public String deleteStock(int id) {
        stockRepository.deleteById((long) id);
        return "Stock eliminado con éxito";
    }
}
