package com.ssdd.Inmobiliaria_CIP.entities;

import java.time.LocalDate;

public class Casa {
    private int id;
    private String nombrePropiedad;
    private double precio;
    private String tipo;
    private int numHabitaciones;
    private int numBannos;
    private double superficie;
    private String direccion;
    private String descripcion;
    private LocalDate fecha;

    public Casa() {
    }

    public Casa(String nombrePropiedad, double precio, String tipo, int numHabitaciones, int numBannos, double superficie, String direccion, String descripcion, LocalDate fecha) {
        this.nombrePropiedad = nombrePropiedad;
        this.precio = precio;
        this.tipo = tipo;
        this.numHabitaciones = numHabitaciones;
        this.numBannos = numBannos;
        this.superficie = superficie;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrePropiedad() {
        return nombrePropiedad;
    }

    public void setNombrePropiedad(String nombrePropiedad) {
        this.nombrePropiedad = nombrePropiedad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(int numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public int getNumBannos() {
        return numBannos;
    }

    public void setNumBannos(int numBannos) {
        this.numBannos = numBannos;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
