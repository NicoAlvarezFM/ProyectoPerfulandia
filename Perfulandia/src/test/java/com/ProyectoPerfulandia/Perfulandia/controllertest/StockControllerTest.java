package com.ProyectoPerfulandia.Perfulandia.controllertest;

import com.ProyectoPerfulandia.Perfulandia.controller.StockController;
import com.ProyectoPerfulandia.Perfulandia.model.Stock;
import com.ProyectoPerfulandia.Perfulandia.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockControllerTest {

    @InjectMocks
    private StockController stockController;

    @Mock
    private StockService stockService;

    private Stock stock;

    @BeforeEach
    void setUp() {
        stock = new Stock();
        stock.setId(1);
        stock.setCantidad(50);
    }

    @Test
    void testObtenerTodos() {
        List<Stock> lista = List.of(stock);
        when(stockService.getStocks()).thenReturn(lista);

        CollectionModel<EntityModel<Stock>> resultado = stockController.obtenerTodos();

        List<Stock> extraidos = resultado.getContent().stream()
                .map(EntityModel::getContent)
                .collect(Collectors.toList());

        assertEquals(1, extraidos.size());
        assertEquals(50, extraidos.get(0).getCantidad());
    }

    @Test
    void testObtenerPorId_Existente() {
        when(stockService.getStock(1)).thenReturn(Optional.of(stock));

        ResponseEntity<EntityModel<Stock>> response = stockController.obtenerPorId(1);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(50, response.getBody().getContent().getCantidad());
    }

    @Test
    void testObtenerPorId_NoExistente() {
        when(stockService.getStock(99)).thenReturn(Optional.empty());

        ResponseEntity<EntityModel<Stock>> response = stockController.obtenerPorId(99);

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
