package com.ProyectoPerfulandia.Perfulandia.controllertest;

import com.ProyectoPerfulandia.Perfulandia.controller.VentaController;
import com.ProyectoPerfulandia.Perfulandia.model.Venta;
import com.ProyectoPerfulandia.Perfulandia.service.VentaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VentaControllerTest {

    @InjectMocks
    private VentaController ventaController;

    @Mock
    private VentaService ventaService;

    private Venta venta;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        venta = new Venta();
        venta.setId(1);
        venta.setFecha("2024-12-10");
        venta.setTotal((int) 350000);
    }

    @Test
    void testObtenerTodas() {
        List<Venta> lista = List.of(venta);
        when(ventaService.getVentas()).thenReturn(lista);

        List<Venta> resultado = ventaController.obtenerTodas();

        assertEquals(1, resultado.size());
        assertEquals(350000, resultado.get(0).getTotal());
    }

    @Test
    void testObtenerPorId_Existente() {
        when(ventaService.getVenta(1)).thenReturn(Optional.of(venta));

        ResponseEntity<Venta> response = ventaController.obtenerPorId(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("2024-12-10", response.getBody().getFecha());
    }

    @Test
    void testObtenerPorId_NoExistente() {
        when(ventaService.getVenta(99)).thenReturn(Optional.empty());

        ResponseEntity<Venta> response = ventaController.obtenerPorId(99);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testCrear() {
        when(ventaService.guardarVenta(venta)).thenReturn(venta);

        Venta creada = ventaController.crear(venta);

        assertEquals(350000, creada.getTotal());
        verify(ventaService).guardarVenta(venta);
    }

    @Test
    void testActualizar_Existente() {
        when(ventaService.getVenta(1)).thenReturn(Optional.of(venta));
        when(ventaService.guardarVenta(venta)).thenReturn(venta);

        ResponseEntity<Venta> response = ventaController.actualizar(1, venta);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(350000, response.getBody().getTotal());
    }

    @Test
    void testActualizar_NoExistente() {
        when(ventaService.getVenta(99)).thenReturn(Optional.empty());

        ResponseEntity<Venta> response = ventaController.actualizar(99, venta);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testEliminar() {
        doNothing().when(ventaService).deleteVenta(1);

        ResponseEntity<String> response = ventaController.eliminar(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Venta eliminada con éxito", response.getBody());
        verify(ventaService).deleteVenta(1);
    }
}
