package com.ProyectoPerfulandia.Perfulandia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Stock {
    private String nombre;
    private String descripcion;
    private int precio;
    private int cantidad;
    private int id;
}
