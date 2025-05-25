package com.ProyectoPerfulandia.Perfulandia.service;

import com.ProyectoPerfulandia.Perfulandia.model.Pago;
import com.ProyectoPerfulandia.Perfulandia.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    // Listar todos los pagos
    public List<Pago> getPagos() {
        return pagoRepository.findAll();
    }

    // Buscar pago por ID
    public Optional<Pago> getPago(int id) {
        return pagoRepository.findById(id);
    }

    // Guardar o actualizar pago
    public Pago guardarPago(Pago pago) {
        return pagoRepository.save(pago);
    }

    // Eliminar pago por ID
    public String deletePago(int id) {
        pagoRepository.deleteById(id);
        return "Pago eliminado con éxito";
    }
}
