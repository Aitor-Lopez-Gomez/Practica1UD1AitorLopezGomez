package com.aitorl.agenciamaterialaudiovisual.modelo;

import java.time.LocalDate;

public class Proyector extends  MaterialAudiovisual{
    private int luminosidad;


    public Proyector(String id, String marca, String modelo, double precio, LocalDate fechaRegistro, int valoracion, String rutaImagen, int luminosidad) {
        super(id, marca, modelo, precio, fechaRegistro, valoracion, rutaImagen);
        this.luminosidad = luminosidad;
    }

    public int getLuminosidad() {
        return luminosidad;
    }

    public void setLuminosidad(int luminosidad) {
        this.luminosidad = luminosidad;
    }

    @Override
    public String toString() {
        return super.toString() + " | Luminosidad: " + luminosidad;
    }
}
