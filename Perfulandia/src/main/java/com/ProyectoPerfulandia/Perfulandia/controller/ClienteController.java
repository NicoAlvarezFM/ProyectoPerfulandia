package com.ProyectoPerfulandia.Perfulandia.controller;

import com.ProyectoPerfulandia.Perfulandia.model.Cliente;
import com.ProyectoPerfulandia.Perfulandia.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getClientes() {
        return clienteService.getClientes();
    }
    @PostMapping
    public Cliente guardarCliente(@RequestBody Cliente cliente) {
        return clienteService.guardarCliente(cliente);
    }
    @GetMapping("{id}")
    public Cliente getCliente(@PathVariable int id){
        return clienteService.getCliente(id);
    }
    @PutMapping("{id}")
    public Cliente actualizarCliente(@PathVariable int id, @RequestBody Cliente cliente) {
        return clienteService.editarCliente(cliente);
    }
    @DeleteMapping("{id}")
    public String eliminarCliente(@PathVariable int id){
        return clienteService.deleteCliente(id);
    }
}
