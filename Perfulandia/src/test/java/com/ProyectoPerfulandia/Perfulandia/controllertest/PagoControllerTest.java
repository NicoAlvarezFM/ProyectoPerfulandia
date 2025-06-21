package com.ProyectoPerfulandia.Perfulandia.controllertest;

import com.ProyectoPerfulandia.Perfulandia.controller.PagoController;
import com.ProyectoPerfulandia.Perfulandia.model.Pago;
import com.ProyectoPerfulandia.Perfulandia.service.PagoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PagoControllerTest {

    @InjectMocks
    private PagoController pagoController;

    @Mock
    private PagoService pagoService;

    private Pago pago;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pago = new Pago();
        pago.setId(1);
        pago.setMetodo("Tarjeta");
        pago.setMonto(150000);
    }

    @Test
    void testObtenerTodos() {
        List<Pago> lista = List.of(pago);
        when(pagoService.getPagos()).thenReturn(lista);

        List<Pago> resultado = pagoController.obtenerTodos();

        assertEquals(1, resultado.size());
        assertEquals("Tarjeta", resultado.get(0).getMetodo());
    }

    @Test
    void testObtenerPorId_Existente() {
        when(pagoService.getPago(1)).thenReturn(Optional.of(pago));

        ResponseEntity<Pago> response = pagoController.obtenerPorId(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Tarjeta", response.getBody().getMetodo());
    }

    @Test
    void testObtenerPorId_NoExistente() {
        when(pagoService.getPago(99)).thenReturn(Optional.empty());

        ResponseEntity<Pago> response = pagoController.obtenerPorId(99);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testCrear() {
        when(pagoService.guardarPago(pago)).thenReturn(pago);

        Pago creado = pagoController.crear(pago);

        assertEquals("Tarjeta", creado.getMetodo());
        verify(pagoService).guardarPago(pago);
    }

    @Test
    void testActualizar_Existente() {
        when(pagoService.getPago(1)).thenReturn(Optional.of(pago));
        when(pagoService.guardarPago(pago)).thenReturn(pago);

        ResponseEntity<Pago> response = pagoController.actualizar(1, pago);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Tarjeta", response.getBody().getMetodo());
        verify(pagoService).guardarPago(pago);
    }

    @Test
    void testActualizar_NoExistente() {
        when(pagoService.getPago(99)).thenReturn(Optional.empty());

        ResponseEntity<Pago> response = pagoController.actualizar(99, pago);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testEliminar() {
        doNothing().when(pagoService).deletePago(1);

        ResponseEntity<String> response = pagoController.eliminar(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Pago eliminado con éxito", response.getBody());
        verify(pagoService).deletePago(1);
    }
}
