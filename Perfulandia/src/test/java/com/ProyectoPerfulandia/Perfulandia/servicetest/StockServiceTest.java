package com.ProyectoPerfulandia.Perfulandia.servicetest;

import com.ProyectoPerfulandia.Perfulandia.model.Stock;
import com.ProyectoPerfulandia.Perfulandia.repository.StockRepository;
import com.ProyectoPerfulandia.Perfulandia.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StockServiceTest {

    @InjectMocks
    private StockService stockService;

    @Mock
    private StockRepository stockRepository;

    private Stock stock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        stock = new Stock();
        stock.setId(1);
        stock.setCantidad(100);
    }

    @Test
    void testGetStocks() {
        List<Stock> lista = List.of(stock);
        when(stockRepository.findAll()).thenReturn(lista);

        List<Stock> resultado = stockService.getStocks();

        assertEquals(1, resultado.size());
    }

    @Test
    void testGetStock_Existente() {
        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));

        Optional<Stock> resultado = stockService.getStock(1);

        assertTrue(resultado.isPresent());
        assertEquals(100, resultado.get().getCantidad());
    }

    @Test
    void testGetStock_NoExistente() {
        when(stockRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Stock> resultado = stockService.getStock(99);

        assertFalse(resultado.isPresent());
    }

    @Test
    void testGuardarStock() {
        when(stockRepository.save(stock)).thenReturn(stock);

        Stock guardado = stockService.guardarStock(stock);

        verify(stockRepository).save(stock);
    }

    @Test
    void testDeleteStock() {
        doNothing().when(stockRepository).deleteById(1L);

        stockService.deleteStock(1);

        verify(stockRepository).deleteById(1L);
    }
}
