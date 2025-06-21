package com.ProyectoPerfulandia.Perfulandia.servicetest;

import com.ProyectoPerfulandia.Perfulandia.model.Venta;
import com.ProyectoPerfulandia.Perfulandia.repository.VentaRepository;
import com.ProyectoPerfulandia.Perfulandia.service.VentaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VentaServiceTest {

    @InjectMocks
    private VentaService ventaService;

    @Mock
    private VentaRepository ventaRepository;

    private Venta venta;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        venta = new Venta();
        venta.setId(1);
        venta.setFecha("2025-06-20");
        venta.setTotal(1200000);
    }

    @Test
    void testGetVentas() {
        List<Venta> lista = List.of(venta);
        when(ventaRepository.findAll()).thenReturn(lista);

        List<Venta> resultado = ventaService.getVentas();

        assertEquals(1, resultado.size());
        assertEquals(1200000, resultado.get(0).getTotal());
    }

    @Test
    void testGetVenta_Existente() {
        when(ventaRepository.findById(1)).thenReturn(Optional.of(venta));

        Optional<Venta> resultado = ventaService.getVenta(1);

        assertTrue(resultado.isPresent());
        assertEquals("2025-06-20", resultado.get().getFecha());
    }

    @Test
    void testGetVenta_NoExistente() {
        when(ventaRepository.findById(99)).thenReturn(Optional.empty());

        Optional<Venta> resultado = ventaService.getVenta(99);

        assertFalse(resultado.isPresent());
    }

    @Test
    void testGuardarVenta() {
        when(ventaRepository.save(venta)).thenReturn(venta);

        Venta guardada = ventaService.guardarVenta(venta);

        assertEquals(1200000, guardada.getTotal());
        verify(ventaRepository).save(venta);
    }

    @Test
    void testDeleteVenta() {
        doNothing().when(ventaRepository).deleteById(1);

        ventaService.deleteVenta(1);

        verify(ventaRepository).deleteById(1);
    }
}
