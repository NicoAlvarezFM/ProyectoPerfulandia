package com.ProyectoPerfulandia.Perfulandia.service;

import com.ProyectoPerfulandia.Perfulandia.model.Venta;
import com.ProyectoPerfulandia.Perfulandia.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    // Listar todas las ventas
    public List<Venta> getVentas() {
        return ventaRepository.findAll();
    }

    // Buscar venta por ID
    public Optional<Venta> getVenta(int id) {
        return ventaRepository.findById(id);
    }

    // Guardar o actualizar venta
    public Venta guardarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    // Eliminar venta por ID
    public void deleteVenta(int id) {
        ventaRepository.deleteById(id);
    }
}
