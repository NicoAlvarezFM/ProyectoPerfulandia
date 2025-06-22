package com.ProyectoPerfulandia.Perfulandia.controller;

import com.ProyectoPerfulandia.Perfulandia.model.Venta;
import com.ProyectoPerfulandia.Perfulandia.service.VentaService;
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
@RequestMapping("/api/v1/ventas")
@Tag(name = "Ventas", description = "Operaciones relacionadas con las ventas realizadas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Operation(summary = "Obtener todas las ventas", description = "Devuelve una lista con todas las ventas registradas")
    @GetMapping
    public CollectionModel<EntityModel<Venta>> obtenerTodas() {
        List<EntityModel<Venta>> ventas = ventaService.getVentas().stream()
                .map(venta -> EntityModel.of(venta,
                        linkTo(methodOn(VentaController.class).obtenerPorId(venta.getId())).withSelfRel(),
                        linkTo(methodOn(VentaController.class).obtenerTodas()).withRel("ventas")
                ))
                .collect(Collectors.toList());

        return CollectionModel.of(ventas,
                linkTo(methodOn(VentaController.class).obtenerTodas()).withSelfRel());
    }

    @Operation(summary = "Obtener una venta por ID", description = "Devuelve los datos de una venta específica según su ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Venta>> obtenerPorId(@PathVariable int id) {
        return ventaService.getVenta(id)
                .map(venta -> {
                    EntityModel<Venta> recurso = EntityModel.of(venta,
                            linkTo(methodOn(VentaController.class).obtenerPorId(id)).withSelfRel(),
                            linkTo(methodOn(VentaController.class).obtenerTodas()).withRel("ventas")
                    );
                    return ResponseEntity.ok(recurso);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva venta", description = "Registra una nueva venta en la base de datos")
    @PostMapping
    public Venta crear(@RequestBody Venta venta) {
        return ventaService.guardarVenta(venta);
    }

    @Operation(summary = "Actualizar una venta existente", description = "Actualiza los datos de una venta específica según su ID")
    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizar(@PathVariable int id, @RequestBody Venta venta) {
        return ventaService.getVenta(id).map(v -> {
            venta.setId(id);
            return ResponseEntity.ok(ventaService.guardarVenta(venta));
        }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar una venta", description = "Elimina una venta específica de la base de datos según su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        ventaService.deleteVenta(id);
        return ResponseEntity.ok("Venta eliminada con éxito");
    }
}
