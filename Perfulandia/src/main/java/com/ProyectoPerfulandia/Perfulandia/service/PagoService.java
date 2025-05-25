package com.ProyectoPerfulandia.Perfulandia.service;

import com.ProyectoPerfulandia.Perfulandia.model.Pago;
import com.ProyectoPerfulandia.Perfulandia.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoService {
    @Autowired
    private PagoRepository pagoRepository;

    public List<Pago> getPagos(){
        return pagoRepository.obtenerPagos();
    }
    public Pago getPago(int id){
        return pagoRepository.buscarPagoPorId(id);
    }
    public Pago getPagoPorMetodo(String metodo){
        return pagoRepository.buscarPagoPorMetodo(metodo);
    }
    public Pago guardarPago(Pago pago){
        return pagoRepository.guardarPago(pago);
    }
    public Pago editarPago(Pago pago){
        return pagoRepository.editarPago(pago);
    }
    public String eliminarPago(int id){
        pagoRepository.borrarPago(id);
        return "Pago borrado";
    }
}
