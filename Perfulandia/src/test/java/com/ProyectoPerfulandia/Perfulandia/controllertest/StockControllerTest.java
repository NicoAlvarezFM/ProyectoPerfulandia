package com.ProyectoPerfulandia.Perfulandia.controllertest;


import com.ProyectoPerfulandia.Perfulandia.controller.StockController;
import com.ProyectoPerfulandia.Perfulandia.model.Stock;
import com.ProyectoPerfulandia.Perfulandia.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StockControllerTest {

    @InjectMocks
    private StockController stockController;

    @Mock
    private StockService stockService;

    private Stock stock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        stock = new Stock();
        stock.setId(1);
        stock.setCantidad(50);
    }

    @Test
    void testObtenerTodos() {
        List<Stock> lista = List.of(stock);
        when(stockService.getStocks()).thenReturn(lista);

        List<Stock> resultado = stockController.obtenerTodos();

        assertEquals(1, resultado.size());
    }

    @Test
    void testObtenerPorId_Existente() {
        when(stockService.getStock(1)).thenReturn(Optional.of(stock));

        ResponseEntity<Stock> response = stockController.obtenerPorId(1);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testObtenerPorId_NoExistente() {
        when(stockService.getStock(99)).thenReturn(Optional.empty());

        ResponseEntity<Stock> response = stockController.obtenerPorId(99);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testCrear() {
        when(stockService.guardarStock(stock)).thenReturn(stock);

        Stock creado = stockController.crear(stock);

        assertEquals(50, creado.getCantidad());
        verify(stockService).guardarStock(stock);
    }

    @Test
    void testActualizar_Existente() {
        when(stockService.getStock(1)).thenReturn(Optional.of(stock));
        when(stockService.guardarStock(stock)).thenReturn(stock);

        ResponseEntity<Stock> response = stockController.actualizar(1, stock);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(50, response.getBody().getCantidad());
    }

    @Test
    void testActualizar_NoExistente() {
        when(stockService.getStock(99)).thenReturn(Optional.empty());

        ResponseEntity<Stock> response = stockController.actualizar(99, stock);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testEliminar() {
        doNothing().when(stockService).deleteStock(1);

        ResponseEntity<String> response = stockController.eliminar(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Stock eliminado con éxito", response.getBody());
        verify(stockService).deleteStock(1);
    }
}
