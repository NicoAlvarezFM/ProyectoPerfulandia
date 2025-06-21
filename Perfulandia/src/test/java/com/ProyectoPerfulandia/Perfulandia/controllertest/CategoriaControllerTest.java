package com.ProyectoPerfulandia.Perfulandia.controllertest;

import com.ProyectoPerfulandia.Perfulandia.controller.CategoriaController;
import com.ProyectoPerfulandia.Perfulandia.model.Categoria;
import com.ProyectoPerfulandia.Perfulandia.service.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaControllerTest {

    @Mock
    private CategoriaService categoriaService;

    @InjectMocks
    private CategoriaController categoriaController;

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setId(1);
        categoria.setNombre("Fragancias");
        categoria.setDescripcion("Perfumes y colonias");
    }

    @Test
    void testObtenerTodas() {
        List<Categoria> categorias = Arrays.asList(categoria);
        when(categoriaService.getCategorias()).thenReturn(categorias);

        List<Categoria> resultado = categoriaController.obtenerTodas();

        assertEquals(1, resultado.size());
        assertEquals("Fragancias", resultado.get(0).getNombre());
    }

    @Test
    void testObtenerPorId_Existe() {
        when(categoriaService.getCategoria(1)).thenReturn(Optional.of(categoria));

        ResponseEntity<Categoria> respuesta = categoriaController.obtenerPorId(1);

        assertTrue(respuesta.getStatusCode().is2xxSuccessful());
        assertEquals("Fragancias", respuesta.getBody().getNombre());
    }

    @Test
    void testObtenerPorId_NoExiste() {
        when(categoriaService.getCategoria(2)).thenReturn(Optional.empty());

        ResponseEntity<Categoria> respuesta = categoriaController.obtenerPorId(2);

        assertTrue(respuesta.getStatusCode().is4xxClientError());
        assertEquals(404, respuesta.getStatusCode().value());
    }

    @Test
    void testCrear() {
        when(categoriaService.guardarCategoria(categoria)).thenReturn(categoria);

        Categoria creada = categoriaController.crear(categoria);

        assertNotNull(creada);
        assertEquals("Fragancias", creada.getNombre());
    }

    @Test
    void testActualizar_Existe() {
        Categoria actualizada = new Categoria(1, "Nueva", "Descripción nueva");

        when(categoriaService.getCategoria(1)).thenReturn(Optional.of(categoria));
        when(categoriaService.guardarCategoria(any(Categoria.class))).thenReturn(actualizada);

        ResponseEntity<Categoria> respuesta = categoriaController.actualizar(1, actualizada);

        assertEquals(200, respuesta.getStatusCode().value());
        assertEquals("Nueva", respuesta.getBody().getNombre());
    }

    @Test
    void testActualizar_NoExiste() {
        when(categoriaService.getCategoria(5)).thenReturn(Optional.empty());

        ResponseEntity<Categoria> respuesta = categoriaController.actualizar(5, categoria);

        assertEquals(404, respuesta.getStatusCode().value());
    }

    @Test
    void testEliminar() {
        doNothing().when(categoriaService).deleteCategoria(1);

        ResponseEntity<String> respuesta = categoriaController.eliminar(1);

        assertEquals(200, respuesta.getStatusCode().value());
        assertEquals("Categoría eliminada con éxito", respuesta.getBody());
    }
}