package com.ProyectoPerfulandia.Perfulandia.repository;

import com.ProyectoPerfulandia.Perfulandia.model.Stock;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StockRepository {
    //GUARDA TODO EL STOCK
    private List<Stock> ListStock = new ArrayList<>();
    //MUESTRA TODO EL STOCK
    public List<Stock> obtenerStock(){
        return ListStock;
    }
    //BUSCAR STOCK POR ID
    public Stock buscarStockPorId(int id){
        for (Stock stock : ListStock){
            if (stock.getId() == id){
                return stock;
            }
        }
        return null;
    }
    //GUARDAR STOCK
    public Stock guardarStock(Stock stock){
        ListStock.add(stock);
        return stock;
    }
    //EDITAR EL STOCK
    public Stock editarStock(Stock stock){
        int id = 0;
        int idPosicion = 0;

        for (int i = 0; i < ListStock.size(); i++){
            if (ListStock.get(i).getId() == stock.getId()){
                id = stock.getId();
                idPosicion = i;

            }
        }
        Stock stock1 = new Stock();
        stock1.setId(id);
        stock1.setCantidad(stock.getCantidad());

        ListStock.set(idPosicion, stock1);
        return stock1;
    }
    //ELIMINAR EL STOCK
    public void borrarStock(int id){
        Stock stock = buscarStockPorId(id);
        if (stock != null){
            ListStock.remove(stock);
        }
    }
}
