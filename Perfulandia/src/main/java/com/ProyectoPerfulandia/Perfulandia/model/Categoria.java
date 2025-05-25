package com.ProyectoPerfulandia.Perfulandia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Categoria {
    private int id;
    private String nombre;
    private String descripcion;
}
