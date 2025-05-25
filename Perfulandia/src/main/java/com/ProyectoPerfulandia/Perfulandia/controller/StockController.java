package com.ProyectoPerfulandia.Perfulandia.controller;

import com.ProyectoPerfulandia.Perfulandia.model.Stock;
import com.ProyectoPerfulandia.Perfulandia.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping
    public List<Stock> getStock(){
        return stockService.getStock();
    }
    @PostMapping
    public Stock addStock(@RequestBody Stock stock){
        return  stockService.guardarStock(stock);
    }
    @GetMapping("{id}")
    public Stock getStockById(@PathVariable int id){
        return stockService.obtenerStockPorId(id);
    }
    @PutMapping("{id}")
    public Stock editarStock(@PathVariable int id, @RequestBody Stock stock){
        return stockService.editarStock(stock);
    }
    @DeleteMapping("{id}")
    public String eliminarStock(@PathVariable int id){
        return stockService.eliminarStockPorId(id);
    }
}
