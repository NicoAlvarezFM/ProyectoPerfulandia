package com.ProyectoPerfulandia.Perfulandia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data

public class Venta {
    private int id;
    private String factura;
    private int clienteId;
    private int pagoId;
    private int perfumeId;
    private String fecha;
    private int total;
}
