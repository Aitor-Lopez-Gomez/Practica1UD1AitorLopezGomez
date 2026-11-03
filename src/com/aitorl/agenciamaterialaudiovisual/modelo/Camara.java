package com.aitorl.agenciamaterialaudiovisual.modelo;

import java.time.LocalDate;
/**
 * Representa una cámara de la agencia de alquiler.
 * Hereda los atributos comunes de MaterialAudiovisual
 * y añade la resolución como característica específica.
 *
 */
public class Camara extends MaterialAudiovisual{
    private String resolucion;
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
     * @param resolucion Resolución de la cámara.
     */
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
