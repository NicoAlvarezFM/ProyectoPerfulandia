package com.ProyectoPerfulandia.Perfulandia.servicetest;

import com.ProyectoPerfulandia.Perfulandia.model.Cliente;
import com.ProyectoPerfulandia.Perfulandia.repository.ClienteRepository;
import com.ProyectoPerfulandia.Perfulandia.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente();
        cliente.setId(1);
        cliente.setNombre("Laura Sánchez");
        cliente.setEmail("laura@example.com");
    }

    @Test
    void testGetClientes() {
        List<Cliente> lista = List.of(cliente);
        when(clienteRepository.findAll()).thenReturn(lista);

        List<Cliente> resultado = clienteService.getClientes();

        assertEquals(1, resultado.size());
        assertEquals("Laura Sánchez", resultado.get(0).getNombre());
    }

    @Test
    void testGetCliente_Existente() {
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        Optional<Cliente> resultado = clienteService.getCliente(1);

        assertTrue(resultado.isPresent());
        assertEquals("laura@example.com", resultado.get().getEmail());
    }

    @Test
    void testGetCliente_NoExistente() {
        when(clienteRepository.findById(99)).thenReturn(Optional.empty());

        Optional<Cliente> resultado = clienteService.getCliente(99);

        assertFalse(resultado.isPresent());
    }

    @Test
    void testGuardarCliente() {
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente guardado = clienteService.guardarCliente(cliente);

        assertEquals("Laura Sánchez", guardado.getNombre());
        verify(clienteRepository).save(cliente);
    }

    @Test
    void testDeleteCliente() {
        doNothing().when(clienteRepository).deleteById(1);

        clienteService.deleteCliente(1);

        verify(clienteRepository).deleteById(1);
    }
}
