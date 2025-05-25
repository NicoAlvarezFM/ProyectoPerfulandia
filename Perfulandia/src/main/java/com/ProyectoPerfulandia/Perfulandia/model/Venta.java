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
    private int clienteId;
    private List<Integer> perfumeIds;
    private int pagoId;
    private String fecha;
    private int total;
}
