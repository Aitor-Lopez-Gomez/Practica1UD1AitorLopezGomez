package com.aitorl.agenciamaterialaudiovisual.modelo;

import java.time.LocalDate;

public class Camara extends MaterialAudiovisual{
    private String resolucion;
    public Camara(String id, String marca, String modelo, double precio, LocalDate fechaRegistro, int valoracion, String rutaImagen, String resolucion) {
        super(id, marca, modelo, precio, fechaRegistro, valoracion, rutaImagen);
        this.resolucion = resolucion;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    @Override
    public String toString() {
        return super.toString() + " | Resolución: " + resolucion;
    }

}
