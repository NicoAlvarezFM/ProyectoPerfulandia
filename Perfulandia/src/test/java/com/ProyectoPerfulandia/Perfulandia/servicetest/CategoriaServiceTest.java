package com.ProyectoPerfulandia.Perfulandia.servicetest;

import com.ProyectoPerfulandia.Perfulandia.model.Categoria;
import com.ProyectoPerfulandia.Perfulandia.repository.CategoriaRepository;
import com.ProyectoPerfulandia.Perfulandia.service.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoriaServiceTest {

    @InjectMocks
    private CategoriaService categoriaService;

    @Mock
    private CategoriaRepository categoriaRepository;

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoria = new Categoria();
        categoria.setId(1);
        categoria.setNombre("Fragancias Florales");
    }

    @Test
    void testGetCategorias() {
        List<Categoria> lista = List.of(categoria);
        when(categoriaRepository.findAll()).thenReturn(lista);

        List<Categoria> resultado = categoriaService.getCategorias();

        assertEquals(1, resultado.size());
        assertEquals("Fragancias Florales", resultado.get(0).getNombre());
    }

    @Test
    void testGetCategoria_Existente() {
        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));

        Optional<Categoria> resultado = categoriaService.getCategoria(1);

        assertTrue(resultado.isPresent());
        assertEquals("Fragancias Florales", resultado.get().getNombre());
    }

    @Test
    void testGetCategoria_NoExistente() {
        when(categoriaRepository.findById(99)).thenReturn(Optional.empty());

        Optional<Categoria> resultado = categoriaService.getCategoria(99);

        assertFalse(resultado.isPresent());
    }

    @Test
    void testGuardarCategoria() {
        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        Categoria guardada = categoriaService.guardarCategoria(categoria);

        assertEquals("Fragancias Florales", guardada.getNombre());
        verify(categoriaRepository).save(categoria);
    }

    @Test
    void testDeleteCategoria() {
        doNothing().when(categoriaRepository).deleteById(1);

        categoriaService.deleteCategoria(1);

        verify(categoriaRepository).deleteById(1);
    }
}
