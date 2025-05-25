package com.ProyectoPerfulandia.Perfulandia.service;

import com.ProyectoPerfulandia.Perfulandia.model.Cliente;
import com.ProyectoPerfulandia.Perfulandia.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getClientes() {
        return clienteRepository.getListCliente();
    }
    public Cliente getCliente(int id) {
        return clienteRepository.buscarPorId(id);
    }
    public Cliente getClientePorEmail(String email) {
        return clienteRepository.buscarPorEmail(email);
    }
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.guardarCliente(cliente);
    }
    public Cliente editarCliente(Cliente cliente) {
        return clienteRepository.editarCliente(cliente);
    }
    public String deleteCliente(int id) {
        clienteRepository.borrarCliente(id);
        return "Cliente eliminado correctamente";
    }

}
