package com.ProyectoPerfulandia.Perfulandia.controllertest;

import com.ProyectoPerfulandia.Perfulandia.controller.ClienteController;
import com.ProyectoPerfulandia.Perfulandia.model.Cliente;
import com.ProyectoPerfulandia.Perfulandia.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente(0, "203885318", "Nicolás", "Álvarez","nicolasalvarez@gmail.com" ,958693265, "Av. Las Torres");
        cliente.setId(1);
        cliente.setNombre("Juan Pérez");
        cliente.setEmail("nicolasalvarez@gmail.com");
    }

    @Test
    void testObtenerTodos() {
        List<Cliente> lista = List.of(cliente);
        when(clienteService.getClientes()).thenReturn(lista);

        List<Cliente> resultado = clienteController.obtenerTodos();

        assertEquals(1, resultado.size());
        assertEquals("Juan Pérez", resultado.get(0).getNombre());
    }

    @Test
    void testObtenerPorId_Existente() {
        when(clienteService.getCliente(1)).thenReturn(Optional.of(cliente));

        ResponseEntity<Cliente> response = clienteController.obtenerPorId(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Juan Pérez", response.getBody().getNombre());
    }

    @Test
    void testObtenerPorId_NoExistente() {
        when(clienteService.getCliente(99)).thenReturn(Optional.empty());

        ResponseEntity<Cliente> response = clienteController.obtenerPorId(99);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testCrear() {
        when(clienteService.guardarCliente(cliente)).thenReturn(cliente);

        Cliente creado = clienteController.crear(cliente);

        assertEquals("Juan Pérez", creado.getNombre());
        verify(clienteService, times(1)).guardarCliente(cliente);
    }

    @Test
    void testActualizar_Existente() {
        when(clienteService.getCliente(1)).thenReturn(Optional.of(cliente));
        when(clienteService.guardarCliente(cliente)).thenReturn(cliente);

        ResponseEntity<Cliente> response = clienteController.actualizar(1, cliente);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Juan Pérez", response.getBody().getNombre());
        verify(clienteService).guardarCliente(cliente);
    }

    @Test
    void testActualizar_NoExistente() {
        when(clienteService.getCliente(99)).thenReturn(Optional.empty());

        ResponseEntity<Cliente> response = clienteController.actualizar(99, cliente);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testEliminar() {
        doNothing().when(clienteService).deleteCliente(1);

        ResponseEntity<String> response = clienteController.eliminar(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Cliente eliminado con éxito", response.getBody());
        verify(clienteService).deleteCliente(1);
    }
}
