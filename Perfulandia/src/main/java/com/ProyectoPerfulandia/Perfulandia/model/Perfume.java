package com.ProyectoPerfulandia.Perfulandia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import jakarta.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity

public class Perfume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int sku;
    private String nombre;
    private String tipo;
    private String descripcion;
    private int precio;
    private int cantidad;

}
