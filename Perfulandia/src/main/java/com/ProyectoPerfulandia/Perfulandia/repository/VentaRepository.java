package com.ProyectoPerfulandia.Perfulandia.repository;

import com.ProyectoPerfulandia.Perfulandia.model.Venta;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VentaRepository {
    //GUARDAR TODAS LAS VENTAS
    private List<Venta> ListVentas = new ArrayList<>();

    //MUESTRA TODAS LAS VENTAS
    public List<Venta> obtenerVentas(){
        return ListVentas;
    }
    //BUSCAR VENTA POR SU ID
    public Venta buscarVentaPorId (int id){
        for  (Venta venta : ListVentas){
            if(venta.getId() == id){
                return venta;
            }
        }
        return null;
    }
    //BUSCAR VENTA POR SU FACTURA
    public Venta buscarVentaPorFactura (String factura){
        for  (Venta venta : ListVentas){
            if(venta.getFactura().equals(factura)){
                return venta;
            }
        }
        return null;
    }
    //GUARDAR VENTA
    public Venta guardarVenta (Venta venta){
        ListVentas.add(venta);
        return venta;
    }
    //EDITAR VENTAS
    public Venta editarVenta(Venta venta){
        int id = 0;
        int idPosicion =0;

        for (int i = 0; i < ListVentas.size(); i++) {
            if(ListVentas.get(i).getId() == venta.getId()){
                id = venta.getId();
                idPosicion = i;
            }
        }
        Venta venta1 = new Venta();
        venta1.setId(id);
        venta1.setFactura(venta.getFactura());
        venta1.setClienteId(venta.getClienteId());
        venta1.setPagoId(venta.getPagoId());
        venta1.setPerfumeId(venta.getPerfumeId());
        venta1.setFecha(venta.getFecha());
        venta1.setTotal(venta.getTotal());

        ListVentas.set(idPosicion, venta1);
        return venta1;
    }
    //ELIMINAR VENTA
    public void borrarVentaPorId(int id){
        Venta venta = buscarVentaPorId(id);
        if(venta != null){
            ListVentas.remove(venta);
        }
    }
}
