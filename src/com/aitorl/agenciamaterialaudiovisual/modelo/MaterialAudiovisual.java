package com.aitorl.agenciamaterialaudiovisual.modelo;

import java.time.LocalDate;

public class MaterialAudiovisual {
    private String id;
    private String marca;
    private String modelo;
    private double precioPorDia;
    private LocalDate fechaRegistro;
    private int valoracion;
    private String rutaImagen;

    public MaterialAudiovisual(String id, String marca, String modelo, double precio, LocalDate fechaRegistro, int valoracion, String rutaImagen) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.precioPorDia = precio;
        this.fechaRegistro = fechaRegistro;
        this.valoracion = valoracion;
        this.rutaImagen = rutaImagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecioPorDia() {
        return precioPorDia;
    }

    public void setPrecioPorDia(double precioPorDia) {
        this.precioPorDia = precioPorDia;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    @Override
    public String toString() {
        return "MaterialAudiovisual{" +
                "id='" + id + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", precioPorDia=" + precioPorDia +
                ", fechaRegistro=" + fechaRegistro +
                ", valoracion=" + valoracion +
                ", rutaImagen='" + rutaImagen + '\'' +
                '}';
    }
}
