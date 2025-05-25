package com.ProyectoPerfulandia.Perfulandia.controller;

import com.ProyectoPerfulandia.Perfulandia.model.Pago;
import com.ProyectoPerfulandia.Perfulandia.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {
    @Autowired
    private PagoService pagoService;

    @GetMapping
    public List<Pago> listar(){
        return pagoService.getPagos();
    }
    @PostMapping
    public Pago guardarPago(@RequestBody Pago pago) {
        return pagoService.guardarPago(pago);
    }
    @GetMapping("{id}")
    public Pago obtenerPagoPorId(@PathVariable int id){
        return pagoService.getPago(id);
    }
    @PutMapping("{id}")
    public Pago editarPagoPorId(@PathVariable int id, @RequestBody Pago pago){
        return pagoService.editarPago(pago);
    }
    @DeleteMapping("{id}")
    public String eliminarPagoPorId(@PathVariable int id){
        return pagoService.eliminarPago(id);
    }
}
