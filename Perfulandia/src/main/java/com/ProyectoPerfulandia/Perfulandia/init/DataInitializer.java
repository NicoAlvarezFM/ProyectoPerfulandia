package com.ProyectoPerfulandia.Perfulandia.init;

import com.ProyectoPerfulandia.Perfulandia.model.*;
import com.ProyectoPerfulandia.Perfulandia.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PerfumeRepository perfumeRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public void run(String... args) {
        //CREACION DE PERFUME
        Perfume perfume = new Perfume(0, 1001, "Invictus", "EDT", "Fragancia fresca", 85000, 20);
        perfumeRepository.save(perfume);
        System.out.println("Perfume creado y tabla creada si no existía.");

        //CREACION DE CATEGORIA
        Categoria categoria = new Categoria(0, "Aromáticos", "Perfumes frescos y naturales");
        categoriaRepository.save(categoria);

        //CREACION DE CLIENTE
        Cliente cliente = new Cliente(0,"203885318","Nicolás","Álvarez","nicolas@gmail.com" ,958693265,"Av. Las Torres");
        clienteRepository.save(cliente);

        //CREACION DE PAGO
        Pago pago = new Pago(0, "Tarjeta", 85000,"12/05/2012", "Completado");
        pagoRepository.save(pago);

        //CREAR VENTA
        Venta venta = new Venta(0,"FAC-2025-0001",11,2,100,"12/09/2024", 100000);
        ventaRepository.save(venta);

        System.out.println("✔Creado");
    }
}
