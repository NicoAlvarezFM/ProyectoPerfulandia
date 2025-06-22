package com.ProyectoPerfulandia.Perfulandia.controller;

import com.ProyectoPerfulandia.Perfulandia.model.Perfume;
import com.ProyectoPerfulandia.Perfulandia.service.PerfumeService;
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
@RequestMapping("/api/v1/perfumes")
@Tag(name = "Perfumes", description = "Operaciones relacionadas con los perfumes")
public class PerfumeController {

    @Autowired
    private PerfumeService perfumeService;

    @Operation(summary = "Obtener todos los perfumes", description = "Devuelve una lista con todos los perfumes registrados")
    @GetMapping
    public CollectionModel<EntityModel<Perfume>> obtenerTodos() {
        List<EntityModel<Perfume>> perfumes = perfumeService.getPerfumes().stream()
                .map(perfume -> EntityModel.of(perfume,
                        linkTo(methodOn(PerfumeController.class).obtenerPorId(perfume.getId())).withSelfRel(),
                        linkTo(methodOn(PerfumeController.class).obtenerTodos()).withRel("perfumes")
                )).collect(Collectors.toList());

        return CollectionModel.of(perfumes,
                linkTo(methodOn(PerfumeController.class).obtenerTodos()).withSelfRel());
    }

    @Operation(summary = "Obtener un perfume por ID", description = "Devuelve los datos de un perfume específico según su ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Perfume>> obtenerPorId(@PathVariable int id) {
        return perfumeService.getPerfume(id)
                .map(perfume -> {
                    EntityModel<Perfume> recurso = EntityModel.of(perfume,
                            linkTo(methodOn(PerfumeController.class).obtenerPorId(id)).withSelfRel(),
                            linkTo(methodOn(PerfumeController.class).obtenerTodos()).withRel("perfumes")
                    );
                    return ResponseEntity.ok(recurso);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener un perfume por SKU", description = "Devuelve los datos de un perfume específico según su SKU")
    @GetMapping("/sku/{sku}")
    public ResponseEntity<EntityModel<Perfume>> obtenerPorSku(@PathVariable int sku) {
        Perfume p = perfumeService.getPerfumePorSku(sku);
        if (p != null) {
            EntityModel<Perfume> recurso = EntityModel.of(p,
                    linkTo(methodOn(PerfumeController.class).obtenerPorSku(sku)).withSelfRel(),
                    linkTo(methodOn(PerfumeController.class).obtenerTodos()).withRel("perfumes")
            );
            return ResponseEntity.ok(recurso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Crear un nuevo perfume", description = "Registra un nuevo perfume en la base de datos")
    @PostMapping
    public Perfume crear(@RequestBody Perfume perfume) {
        return perfumeService.guardarPerfume(perfume);
    }

    @Operation(summary = "Actualizar un perfume existente", description = "Actualiza los datos de un perfume específico según su ID")
    @PutMapping("/{id}")
    public ResponseEntity<Perfume> actualizar(@PathVariable int id, @RequestBody Perfume perfume) {
        return perfumeService.getPerfume(id).map(p -> {
            perfume.setId(id);
            return ResponseEntity.ok(perfumeService.guardarPerfume(perfume));
        }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un perfume", description = "Elimina un perfume específico de la base de datos según su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        perfumeService.deletePerfume(id);
        return ResponseEntity.ok("Perfume eliminado con éxito");
    }
}
