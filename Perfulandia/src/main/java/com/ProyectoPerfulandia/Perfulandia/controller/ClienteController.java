package com.ProyectoPerfulandia.Perfulandia.controller;

import com.ProyectoPerfulandia.Perfulandia.model.Cliente;
import com.ProyectoPerfulandia.Perfulandia.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1/clientes")
@Tag(name = "Clientes", description = "Operaciones relacionadas con los clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Obtener todos los clientes", description = "Devuelve una lista con todos los clientes registrados")
    @GetMapping
    public CollectionModel<EntityModel<Cliente>> obtenerTodos() {
        List<EntityModel<Cliente>> clientes = clienteService.getClientes().stream()
                .map(cliente -> EntityModel.of(cliente,
                        linkTo(methodOn(ClienteController.class).obtenerPorId(cliente.getId())).withSelfRel(),
                        linkTo(methodOn(ClienteController.class).obtenerTodos()).withRel("clientes")
                )).collect(Collectors.toList());

        return CollectionModel.of(clientes,
                linkTo(methodOn(ClienteController.class).obtenerTodos()).withSelfRel());
    }

    @Operation(summary = "Obtener un cliente por ID", description = "Devuelve los datos de un cliente específico según su ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Cliente>> obtenerPorId(@PathVariable int id) {
        return clienteService.getCliente(id)
                .map(cliente -> {
                    EntityModel<Cliente> recurso = EntityModel.of(cliente,
                            linkTo(methodOn(ClienteController.class).obtenerPorId(id)).withSelfRel(),
                            linkTo(methodOn(ClienteController.class).obtenerTodos()).withRel("clientes")
                    );
                    return ResponseEntity.ok(recurso);
                }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo cliente", description = "Crea y guarda un nuevo cliente en la base de datos")
    @PostMapping
    public Cliente crear(@RequestBody Cliente cliente) {
        return clienteService.guardarCliente(cliente);
    }

    @Operation(summary = "Actualizar un cliente existente", description = "Actualiza los datos de un cliente existente según su ID")
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable int id, @RequestBody Cliente cliente) {
        return clienteService.getCliente(id).map(c -> {
            cliente.setId(id);
            return ResponseEntity.ok(clienteService.guardarCliente(cliente));
        }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente específico de la base de datos según su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.ok("Cliente eliminado con éxito");
    }
}
