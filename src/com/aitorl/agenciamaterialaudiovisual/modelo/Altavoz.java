package com.aitorl.agenciamaterialaudiovisual.modelo;

import java.time.LocalDate;
/**
 * Representa un altavoz de la agencia de alquiler.
 * Hereda de MaterialAudiovisual y añade la potencia
 * en vatios como característica específica.
 *
 */
public class Altavoz extends  MaterialAudiovisual{
    private int potenciaVatios;

    /**
     * Crea una cámara con sus datos y resolución.
     *
     * @param id Identificador único del material.
     * @param marca Marca de la cámara.
     * @param modelo Modelo de la cámara.
     * @param precio Precio de alquiler por día.
     * @param fechaRegistro Fecha de registro.
     * @param valoracion Valoración de 1 a 5 estrellas.
     * @param rutaImagen Ruta de la imagen asociada.
     * @param potenciaVatios Potencia del altavoz en vatios.
     */
    public Altavoz(String id, String marca, String modelo, double precio, LocalDate fechaRegistro, int valoracion, String rutaImagen, int potenciaVatios) {
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
