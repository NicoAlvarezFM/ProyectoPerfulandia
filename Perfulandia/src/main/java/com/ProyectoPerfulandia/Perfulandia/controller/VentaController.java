package com.ProyectoPerfulandia.Perfulandia.controller;

import com.ProyectoPerfulandia.Perfulandia.model.Venta;
import com.ProyectoPerfulandia.Perfulandia.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ventas")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @GetMapping
    public List<Venta> obtenerVentas(){
        return ventaService.getVentas();
    }
    @PostMapping
    public Venta crearVenta(@RequestBody Venta venta){
        return ventaService.guardarVenta(venta);
    }
    @GetMapping("{id}")
    public Venta obtenerVentaPorId(@PathVariable int id){
        return ventaService.buscarVentaPorId(id);
    }
    @PutMapping("{id}")
    public Venta editarVenta(@PathVariable int id, @RequestBody Venta venta){
        return ventaService.editarVenta(venta);
    }
    @DeleteMapping("{id}")
    public String eliminarVentaPorId(@PathVariable int id){
        return ventaService.eliminarVenta(id);
    }
}
