package com.ProyectoPerfulandia.Perfulandia.controller;

import com.ProyectoPerfulandia.Perfulandia.model.Stock;
import com.ProyectoPerfulandia.Perfulandia.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    // Obtener todos los stocks
    @GetMapping
    public List<Stock> obtenerTodos() {
        return stockService.getStocks();
    }

    // Obtener stock por ID
    @GetMapping("/{id}")
    public ResponseEntity<Stock> obtenerPorId(@PathVariable int id) {
        return stockService.getStock(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo stock
    @PostMapping
    public Stock crear(@RequestBody Stock stock) {
        return stockService.guardarStock(stock);
    }

    // Actualizar un stock existente
    @PutMapping("/{id}")
    public ResponseEntity<Stock> actualizar(@PathVariable int id, @RequestBody Stock stock) {
        return stockService.getStock(id).map(s -> {
            stock.setId(id);
            return ResponseEntity.ok(stockService.guardarStock(stock));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Eliminar stock por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        stockService.deleteStock(id);
        return ResponseEntity.ok("Stock eliminado con éxito");
    }
}
