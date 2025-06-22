package com.ProyectoPerfulandia.Perfulandia.controllertest;

import com.ProyectoPerfulandia.Perfulandia.controller.ClienteController;
import com.ProyectoPerfulandia.Perfulandia.model.Cliente;
import com.ProyectoPerfulandia.Perfulandia.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente(0, "203885318", "Nicolás", "Álvarez", "nicolasalvarez@gmail.com", 958693265, "Av. Las Torres");
        cliente.setId(1);
        cliente.setNombre("Juan Pérez");
        cliente.setEmail("nicolasalvarez@gmail.com");
    }

    @Test
    void testObtenerTodos() {
        List<Cliente> lista = List.of(cliente);
        when(clienteService.getClientes()).thenReturn(lista);

        CollectionModel<EntityModel<Cliente>> resultado = clienteController.obtenerTodos();

        List<Cliente> clientesExtraidos = resultado.getContent().stream()
                .map(EntityModel::getContent)
                .collect(Collectors.toList());

        assertEquals(1, clientesExtraidos.size());
        assertEquals("Juan Pérez", clientesExtraidos.get(0).getNombre());
    }

    @Test
    void testObtenerPorId_Existente() {
        when(clienteService.getCliente(1)).thenReturn(Optional.of(cliente));

        ResponseEntity<EntityModel<Cliente>> response = clienteController.obtenerPorId(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Juan Pérez", response.getBody().getContent().getNombre());
    }

    @Test
    void testObtenerPorId_NoExistente() {
        when(clienteService.getCliente(99)).thenReturn(Optional.empty());

        ResponseEntity<EntityModel<Cliente>> response = clienteController.obtenerPorId(99);

        assertEquals(404, response.getStatusCodeValue());
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
