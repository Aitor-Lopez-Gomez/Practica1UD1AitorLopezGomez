package com.aitorl.agenciamaterialaudiovisual.modelo;

import java.time.LocalDate;
/**
 * Representa un proyector de la agencia de alquiler.
 * Hereda de MaterialAudiovisual y añade la luminosidad
 * como característica específica.
 *
 */
public class Proyector extends  MaterialAudiovisual{
    private int luminosidad;

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
     * @param luminosidad Luminosidad del proyector en lúmenes.
     */
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
