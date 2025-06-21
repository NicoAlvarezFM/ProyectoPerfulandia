package com.ProyectoPerfulandia.Perfulandia.controllertest;

import com.ProyectoPerfulandia.Perfulandia.controller.PerfumeController;
import com.ProyectoPerfulandia.Perfulandia.model.Perfume;
import com.ProyectoPerfulandia.Perfulandia.service.PerfumeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PerfumeControllerTest {

    @InjectMocks
    private PerfumeController perfumeController;

    @Mock
    private PerfumeService perfumeService;

    private Perfume perfume;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        perfume = new Perfume();
        perfume.setId(1);
        perfume.setNombre("Aqua Di Gio");
        perfume.setSku(12345);
    }

    @Test
    void testObtenerTodos() {
        List<Perfume> lista = List.of(perfume);
        when(perfumeService.getPerfumes()).thenReturn(lista);

        List<Perfume> resultado = perfumeController.obtenerTodos();

        assertEquals(1, resultado.size());
        assertEquals("Aqua Di Gio", resultado.get(0).getNombre());
    }

    @Test
    void testObtenerPorId_Existente() {
        when(perfumeService.getPerfume(1)).thenReturn(Optional.of(perfume));

        ResponseEntity<Perfume> response = perfumeController.obtenerPorId(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Aqua Di Gio", response.getBody().getNombre());
    }

    @Test
    void testObtenerPorId_NoExistente() {
        when(perfumeService.getPerfume(99)).thenReturn(Optional.empty());

        ResponseEntity<Perfume> response = perfumeController.obtenerPorId(99);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testObtenerPorSku_Existente() {
        when(perfumeService.getPerfumePorSku(12345)).thenReturn(perfume);

        ResponseEntity<Perfume> response = perfumeController.obtenerPorSku(12345);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(12345, response.getBody().getSku());
    }

    @Test
    void testObtenerPorSku_NoExistente() {
        when(perfumeService.getPerfumePorSku(99999)).thenReturn(null);

        ResponseEntity<Perfume> response = perfumeController.obtenerPorSku(99999);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testCrear() {
        when(perfumeService.guardarPerfume(perfume)).thenReturn(perfume);

        Perfume creado = perfumeController.crear(perfume);

        assertEquals("Aqua Di Gio", creado.getNombre());
        verify(perfumeService).guardarPerfume(perfume);
    }

    @Test
    void testActualizar_Existente() {
        when(perfumeService.getPerfume(1)).thenReturn(Optional.of(perfume));
        when(perfumeService.guardarPerfume(perfume)).thenReturn(perfume);

        ResponseEntity<Perfume> response = perfumeController.actualizar(1, perfume);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Aqua Di Gio", response.getBody().getNombre());
    }

    @Test
    void testActualizar_NoExistente() {
        when(perfumeService.getPerfume(99)).thenReturn(Optional.empty());

        ResponseEntity<Perfume> response = perfumeController.actualizar(99, perfume);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testEliminar() {
        doNothing().when(perfumeService).deletePerfume(1);

        ResponseEntity<String> response = perfumeController.eliminar(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Perfume eliminado con éxito", response.getBody());
        verify(perfumeService).deletePerfume(1);
    }
}
