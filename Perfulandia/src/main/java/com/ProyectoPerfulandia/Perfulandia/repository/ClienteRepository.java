package com.ProyectoPerfulandia.Perfulandia.repository;

import com.ProyectoPerfulandia.Perfulandia.model.Cliente;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;

@Repository
public class ClienteRepository {
    //GUARDARÁ LOS CLIENTES
    private List<Cliente> ListCliente = new ArrayList<>();

    //RETORNA TODOS LOS CLIENTE
    public List<Cliente> getListCliente() {
        return ListCliente;
    }

    //BUSCAR CLIENTE POR SU ID
    public Cliente buscarPorId(int id) {
        for (Cliente cliente : ListCliente) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    //BUSCAR CLIENTE POR EMAIL
    public Cliente buscarPorEmail(String email) {
        for (Cliente cliente : ListCliente) {
            if (cliente.getEmail().equals(email)) {
                return cliente;
            }
        }
        return null;
    }

    //GUARDAR CLIENTE
    public Cliente guardarCliente(Cliente cliente) {
        ListCliente.add(cliente);
        return cliente;
    }

    //EDITAR CLIENTE
    public Cliente editarCliente(Cliente cliente) {
        int id = 0;
        int idPosicion = 0;

        for (int i = 0; i < ListCliente.size(); i++) {
            if (ListCliente.get(i).getId() == cliente.getId()) {
                id = cliente.getId();
                idPosicion = i;
            }
        }
        Cliente cliente1 = new Cliente();
        cliente1.setId(id);
        cliente1.setRun(cliente.getRun());
        cliente1.setNombre(cliente.getNombre());
        cliente1.setApellido(cliente.getApellido());
        cliente1.setEmail(cliente.getEmail());
        cliente1.setCelular(cliente.getCelular());
        cliente1.setDireccion(cliente.getDireccion());

        ListCliente.set(idPosicion, cliente1);
        return cliente1;
    }
    //ELIMINAR CLIENTE
    public void borrarCliente(int id) {
        Cliente cliente = buscarPorId(id);
        if(cliente != null){
            ListCliente.remove(cliente);
        }
    }
}
