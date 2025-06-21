package com.ProyectoPerfulandia.Perfulandia.servicetest;

import com.ProyectoPerfulandia.Perfulandia.model.Perfume;
import com.ProyectoPerfulandia.Perfulandia.repository.PerfumeRepository;
import com.ProyectoPerfulandia.Perfulandia.service.PerfumeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PerfumeServiceTest {

    @InjectMocks
    private PerfumeService perfumeService;

    @Mock
    private PerfumeRepository perfumeRepository;

    private Perfume perfume;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        perfume = new Perfume();
        perfume.setId(1);
        perfume.setNombre("Bleu de Chanel");
        perfume.setSku(123456);
    }

    @Test
    void testGetPerfumes() {
        List<Perfume> lista = List.of(perfume);
        when(perfumeRepository.findAll()).thenReturn(lista);

        List<Perfume> resultado = perfumeService.getPerfumes();

        assertEquals(1, resultado.size());
        assertEquals("Bleu de Chanel", resultado.get(0).getNombre());
    }

    @Test
    void testGetPerfume_Existente() {
        when(perfumeRepository.findById(1)).thenReturn(Optional.of(perfume));

        Optional<Perfume> resultado = perfumeService.getPerfume(1);

        assertTrue(resultado.isPresent());
        assertEquals(123456, resultado.get().getSku());
    }

    @Test
    void testGetPerfume_NoExistente() {
        when(perfumeRepository.findById(99)).thenReturn(Optional.empty());

        Optional<Perfume> resultado = perfumeService.getPerfume(99);

        assertFalse(resultado.isPresent());
    }

    @Test
    void testGetPerfumePorSku() {
        when(perfumeRepository.findBySku(123456)).thenReturn(perfume);

        Perfume resultado = perfumeService.getPerfumePorSku(123456);

        assertNotNull(resultado);
        assertEquals("Bleu de Chanel", resultado.getNombre());
    }

    @Test
    void testGuardarPerfume() {
        when(perfumeRepository.save(perfume)).thenReturn(perfume);

        Perfume guardado = perfumeService.guardarPerfume(perfume);

        assertEquals("Bleu de Chanel", guardado.getNombre());
        verify(perfumeRepository).save(perfume);
    }

    @Test
    void testDeletePerfume() {
        doNothing().when(perfumeRepository).deleteById(1);

        perfumeService.deletePerfume(1);

        verify(perfumeRepository).deleteById(1);
    }
}
