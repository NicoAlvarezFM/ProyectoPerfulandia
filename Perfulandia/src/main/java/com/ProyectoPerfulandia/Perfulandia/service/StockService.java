package com.ProyectoPerfulandia.Perfulandia.service;

import com.ProyectoPerfulandia.Perfulandia.model.Stock;
import com.ProyectoPerfulandia.Perfulandia.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public List<Stock> getStock(){
        return stockRepository.obtenerStock();
    }
    public Stock obtenerStockPorId(int id){
        return stockRepository.buscarStockPorId(id);
    }
    public Stock guardarStock(Stock stock){
        return stockRepository.guardarStock(stock);
    }
    public Stock editarStock(Stock stock){
        return stockRepository.editarStock(stock);
    }
    public String eliminarStockPorId(int id){
        stockRepository.borrarStock(id);
        return "Stock eliminado con exito";
    }
}
