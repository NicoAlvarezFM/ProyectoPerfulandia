package com.ProyectoPerfulandia.Perfulandia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Cliente {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String celular;
    private String direccion;
}
