package com.ProyectoPerfulandia.Perfulandia.servicetest;

import com.ProyectoPerfulandia.Perfulandia.model.Pago;
import com.ProyectoPerfulandia.Perfulandia.repository.PagoRepository;
import com.ProyectoPerfulandia.Perfulandia.service.PagoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PagoServiceTest {

    @InjectMocks
    private PagoService pagoService;

    @Mock
    private PagoRepository pagoRepository;

    private Pago pago;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pago = new Pago();
        pago.setId(1);
        pago.setMetodo("Transferencia");
        pago.setMonto(280000);
    }

    @Test
    void testGetPagos() {
        List<Pago> lista = List.of(pago);
        when(pagoRepository.findAll()).thenReturn(lista);

        List<Pago> resultado = pagoService.getPagos();

        assertEquals(1, resultado.size());
        assertEquals("Transferencia", resultado.get(0).getMetodo());
    }

    @Test
    void testGetPago_Existente() {
        when(pagoRepository.findById(1)).thenReturn(Optional.of(pago));

        Optional<Pago> resultado = pagoService.getPago(1);

        assertTrue(resultado.isPresent());
        assertEquals(280000, resultado.get().getMonto());
    }

    @Test
    void testGetPago_NoExistente() {
        when(pagoRepository.findById(99)).thenReturn(Optional.empty());

        Optional<Pago> resultado = pagoService.getPago(99);

        assertFalse(resultado.isPresent());
    }

    @Test
    void testGuardarPago() {
        when(pagoRepository.save(pago)).thenReturn(pago);

        Pago guardado = pagoService.guardarPago(pago);

        assertEquals("Transferencia", guardado.getMetodo());
        verify(pagoRepository).save(pago);
    }

    @Test
    void testDeletePago() {
        doNothing().when(pagoRepository).deleteById(1);

        pagoService.deletePago(1);

        verify(pagoRepository).deleteById(1);
    }
}
