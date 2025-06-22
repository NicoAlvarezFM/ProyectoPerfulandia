package com.ProyectoPerfulandia.Perfulandia.controller;

import com.ProyectoPerfulandia.Perfulandia.model.Stock;
import com.ProyectoPerfulandia.Perfulandia.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1/stock")
@Tag(name = "Stock", description = "Operaciones relacionadas con el stock de productos")
public class StockController {

    @Autowired
    private StockService stockService;

    @Operation(summary = "Obtener todo el stock", description = "Devuelve una lista con todos los registros de stock")
    @GetMapping
    public CollectionModel<EntityModel<Stock>> obtenerTodos() {
        List<EntityModel<Stock>> stocks = stockService.getStocks().stream()
                .map(stock -> EntityModel.of(stock,
                        linkTo(methodOn(StockController.class).obtenerPorId(stock.getId())).withSelfRel(),
                        linkTo(methodOn(StockController.class).obtenerTodos()).withRel("stock")
                )).collect(Collectors.toList());

        return CollectionModel.of(stocks,
                linkTo(methodOn(StockController.class).obtenerTodos()).withSelfRel());
    }

    @Operation(summary = "Obtener un registro de stock por ID", description = "Devuelve los datos de un stock específico según su ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Stock>> obtenerPorId(@PathVariable int id) {
        return stockService.getStock(id)
                .map(stock -> {
                    EntityModel<Stock> recurso = EntityModel.of(stock,
                            linkTo(methodOn(StockController.class).obtenerPorId(id)).withSelfRel(),
                            linkTo(methodOn(StockController.class).obtenerTodos()).withRel("stock")
                    );
                    return ResponseEntity.ok(recurso);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo stock", description = "Registra un nuevo stock en la base de datos")
    @PostMapping
    public Stock crear(@RequestBody Stock stock) {
        return stockService.guardarStock(stock);
    }

    @Operation(summary = "Actualizar un stock existente", description = "Actualiza los datos de un stock específico según su ID")
    @PutMapping("/{id}")
    public ResponseEntity<Stock> actualizar(@PathVariable int id, @RequestBody Stock stock) {
        return stockService.getStock(id).map(s -> {
            stock.setId(id);
            return ResponseEntity.ok(stockService.guardarStock(stock));
        }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un stock", description = "Elimina un registro de stock específico según su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        stockService.deleteStock(id);
        return ResponseEntity.ok("Stock eliminado con éxito");
    }
}
