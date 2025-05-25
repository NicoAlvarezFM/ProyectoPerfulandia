package com.ProyectoPerfulandia.Perfulandia.controller;

import com.ProyectoPerfulandia.Perfulandia.model.Pago;
import com.ProyectoPerfulandia.Perfulandia.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    // Obtener todos los pagos
    @GetMapping
    public List<Pago> obtenerTodos() {
        return pagoService.getPagos();
    }

    // Obtener pago por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtenerPorId(@PathVariable int id) {
        return pagoService.getPago(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo pago
    @PostMapping
    public Pago crear(@RequestBody Pago pago) {
        return pagoService.guardarPago(pago);
    }

    // Actualizar un pago existente
    @PutMapping("/{id}")
    public ResponseEntity<Pago> actualizar(@PathVariable int id, @RequestBody Pago pago) {
        return pagoService.getPago(id).map(p -> {
            pago.setId(id);
            return ResponseEntity.ok(pagoService.guardarPago(pago));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Eliminar pago por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        pagoService.deletePago(id);
        return ResponseEntity.ok("Pago eliminado con éxito");
    }
}
