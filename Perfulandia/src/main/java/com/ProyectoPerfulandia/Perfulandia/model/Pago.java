package com.ProyectoPerfulandia.Perfulandia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Pago {
    private int id;
    private String metodo;
    private int monto;
    private int fecha;
    private String estado;
}
