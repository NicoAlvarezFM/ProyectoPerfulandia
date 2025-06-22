package com.ProyectoPerfulandia.Perfulandia.controller;

import com.ProyectoPerfulandia.Perfulandia.model.Categoria;
import com.ProyectoPerfulandia.Perfulandia.service.CategoriaService;
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
@RequestMapping("/api/v1/categorias")
@Tag(name = "Categorías", description = "Operaciones relacionadas con las categorías de productos")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Operation(summary = "Obtener todas las categorías", description = "Devuelve una lista con todas las categorías registradas")
    @GetMapping
    public CollectionModel<EntityModel<Categoria>> obtenerTodas() {
        List<EntityModel<Categoria>> categorias = categoriaService.getCategorias().stream()
                .map(categoria -> EntityModel.of(categoria,
                                linkTo(methodOn(CategoriaController.class).obtenerPorId(categoria.getId())).withSelfRel(),
                                linkTo(methodOn(CategoriaController.class).obtenerTodas()).withRel("categorias")
                        )
                )
                .collect(Collectors.toList());

        return CollectionModel.of(categorias,
                linkTo(methodOn(CategoriaController.class).obtenerTodas()).withSelfRel());
    }

    @Operation(summary = "Obtener una categoría por ID", description = "Devuelve los datos de una categoría específica según su ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Categoria>> obtenerPorId(@PathVariable int id) {
        return categoriaService.getCategoria(id)
                .map(categoria -> {
                    EntityModel<Categoria> recurso = EntityModel.of(categoria,
                            linkTo(methodOn(CategoriaController.class).obtenerPorId(id)).withSelfRel(),
                            linkTo(methodOn(CategoriaController.class).obtenerTodas()).withRel("categorias")
                    );
                    return ResponseEntity.ok(recurso);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva categoría", description = "Crea y guarda una nueva categoría en la base de datos")
    @PostMapping
    public Categoria crear(@RequestBody Categoria categoria) {
        return categoriaService.guardarCategoria(categoria);
    }

    @Operation(summary = "Actualizar una categoría existente", description = "Actualiza los datos de una categoría existente según su ID")
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(@PathVariable int id, @RequestBody Categoria categoria) {
        return categoriaService.getCategoria(id).map(c -> {
            categoria.setId(id);
            return ResponseEntity.ok(categoriaService.guardarCategoria(categoria));
        }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar una categoría", description = "Elimina una categoría específica de la base de datos según su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        categoriaService.deleteCategoria(id);
        return ResponseEntity.ok("Categoría eliminada con éxito");
    }
}
