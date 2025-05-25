package com.ProyectoPerfulandia.Perfulandia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Perfume {
    private int id;
    private int sku;
    private String nombre;
    private String tipo;
    private String descripcion;
    private int precio;
    private int cantidad;

}
