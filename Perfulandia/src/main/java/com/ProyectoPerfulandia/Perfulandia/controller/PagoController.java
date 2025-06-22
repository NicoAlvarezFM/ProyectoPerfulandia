package com.ProyectoPerfulandia.Perfulandia.controller;

import com.ProyectoPerfulandia.Perfulandia.model.Pago;
import com.ProyectoPerfulandia.Perfulandia.service.PagoService;
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
@RequestMapping("/api/v1/pagos")
@Tag(name = "Pagos", description = "Operaciones relacionadas con los pagos realizados")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Operation(summary = "Obtener todos los pagos", description = "Devuelve una lista con todos los pagos registrados")
    @GetMapping
    public CollectionModel<EntityModel<Pago>> obtenerTodos() {
        List<EntityModel<Pago>> pagos = pagoService.getPagos().stream()
                .map(pago -> EntityModel.of(pago,
                        linkTo(methodOn(PagoController.class).obtenerPorId(pago.getId())).withSelfRel(),
                        linkTo(methodOn(PagoController.class).obtenerTodos()).withRel("pagos")
                )).collect(Collectors.toList());

        return CollectionModel.of(pagos,
                linkTo(methodOn(PagoController.class).obtenerTodos()).withSelfRel());
    }

    @Operation(summary = "Obtener un pago por ID", description = "Devuelve los datos de un pago específico según su ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Pago>> obtenerPorId(@PathVariable int id) {
        return pagoService.getPago(id)
                .map(pago -> {
                    EntityModel<Pago> recurso = EntityModel.of(pago,
                            linkTo(methodOn(PagoController.class).obtenerPorId(id)).withSelfRel(),
                            linkTo(methodOn(PagoController.class).obtenerTodos()).withRel("pagos")
                    );
                    return ResponseEntity.ok(recurso);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo pago", description = "Registra un nuevo pago en la base de datos")
    @PostMapping
    public Pago crear(@RequestBody Pago pago) {
        return pagoService.guardarPago(pago);
    }

    @Operation(summary = "Actualizar un pago existente", description = "Actualiza los datos de un pago específico según su ID")
    @PutMapping("/{id}")
    public ResponseEntity<Pago> actualizar(@PathVariable int id, @RequestBody Pago pago) {
        return pagoService.getPago(id).map(p -> {
            pago.setId(id);
            return ResponseEntity.ok(pagoService.guardarPago(pago));
        }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un pago", description = "Elimina un pago específico de la base de datos según su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        pagoService.deletePago(id);
        return ResponseEntity.ok("Pago eliminado con éxito");
    }
}
