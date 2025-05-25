package com.ProyectoPerfulandia.Perfulandia.repository;

import com.ProyectoPerfulandia.Perfulandia.model.Pago;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PagoRepository {
    //GUARDAR TODOS LOS PAGOS
    private List<Pago> ListPago = new ArrayList<>();

    //MUESTRA TODOS PAGOS
    public List<Pago> obtenerPagos(){
        return ListPago;
    }
    //BUSCAR PAGOS POR ID
    public Pago buscarPagoPorId(int id){
        for (Pago pago : ListPago){
            if (pago.getId() == id){
                return pago;
            }
        }
        return null;
    }
    //BUSCAR PAGO POR SU METODO
    public Pago buscarPagoPorMetodo(String metodo){
        for (Pago pago : ListPago){
            if (pago.getMetodo().equals(metodo)){
                return pago;
            }
        }
        return null;
    }
    //GUARDAR PAGOS
    public Pago guardarPago(Pago pago){
        ListPago.add(pago);
        return pago;
    }
    //EDITAR PAGO
    public Pago editarPago(Pago pago){
        int id = 0;
        int idPosicion = 0;

        for (int i = 0; i < ListPago.size(); i++){
            if (ListPago.get(i).getId() == pago.getId()){
                id = pago.getId();
                idPosicion = i;
            }
        }
        Pago pago1 = new Pago();
        pago1.setId(id);
        pago1.setMetodo(pago.getMetodo());
        pago1.setMonto(pago.getMonto());
        pago1.setFecha(pago.getFecha());
        pago1.setEstado(pago.getEstado());

        ListPago.set(idPosicion, pago1);
        return pago1;
    }
    //ELIMINAR PAGO
    public void borrarPago(int id){
        Pago pago = buscarPagoPorId(id);
        if (pago != null){
            ListPago.remove(pago);
        }
    }
}
