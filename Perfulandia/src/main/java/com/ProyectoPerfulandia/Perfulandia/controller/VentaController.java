package com.ProyectoPerfulandia.Perfulandia.controller;

import com.ProyectoPerfulandia.Perfulandia.model.Venta;
import com.ProyectoPerfulandia.Perfulandia.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    // Obtener todas las ventas
    @GetMapping
    public List<Venta> obtenerTodas() {
        return ventaService.getVentas();
    }

    // Obtener venta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerPorId(@PathVariable int id) {
        return ventaService.getVenta(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva venta
    @PostMapping
    public Venta crear(@RequestBody Venta venta) {
        return ventaService.guardarVenta(venta);
    }

    // Actualizar una venta existente
    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizar(@PathVariable int id, @RequestBody Venta venta) {
        return ventaService.getVenta(id).map(v -> {
            venta.setId(id);
            return ResponseEntity.ok(ventaService.guardarVenta(venta));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Eliminar venta por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        ventaService.deleteVenta(id);
        return ResponseEntity.ok("Venta eliminada con éxito");
    }
}
