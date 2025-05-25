package com.ProyectoPerfulandia.Perfulandia.service;

import com.ProyectoPerfulandia.Perfulandia.model.Venta;
import com.ProyectoPerfulandia.Perfulandia.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> getVentas(){
        return ventaRepository.obtenerVentas();
    }
    public Venta buscarVentaPorId(int id){
        return ventaRepository.buscarVentaPorId(id);
    }
    public Venta buscarVentaPorFactura(String factura){
        return ventaRepository.buscarVentaPorFactura(factura);
    }
    public Venta guardarVenta(Venta venta){
        return ventaRepository.guardarVenta(venta);
    }
    public Venta editarVenta(Venta venta){
        return ventaRepository.editarVenta(venta);
    }
    public String eliminarVenta(int id){
        ventaRepository.borrarVentaPorId(id);
        return "Venta eliminada con exito";
    }
}
