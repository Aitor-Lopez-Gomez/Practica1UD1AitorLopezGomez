package com.aitorl.agenciamaterialaudiovisual.modelo;

import java.time.LocalDate;

public class Foco extends MaterialAudiovisual{
    private int potenciaVatios;


    public Foco(String id, String marca, String modelo, double precio, LocalDate fechaRegistro, int valoracion, String rutaImagen, int potenciaVatios) {
        super(id, marca, modelo, precio, fechaRegistro, valoracion, rutaImagen);
        this.potenciaVatios = potenciaVatios;

    }

    public int getPotenciaVatios() {
        return potenciaVatios;
    }

    public void setPotenciaVatios(int potenciaVatios) {
        this.potenciaVatios = potenciaVatios;
    }

    @Override
    public String toString() {
        return super.toString() + " | Potencia: " + potenciaVatios + " W";
    }
}