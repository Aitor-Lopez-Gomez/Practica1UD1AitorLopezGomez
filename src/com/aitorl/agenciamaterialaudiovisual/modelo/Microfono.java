package com.aitorl.agenciamaterialaudiovisual.modelo;

import java.time.LocalDate;
/**
 * Representa un micrófono de la agencia de alquiler.
 * Hereda de MaterialAudiovisual y añade el tipo
 * de conexión como característica específica.
 *
 */
public class Microfono extends MaterialAudiovisual{
    private String tipoConexion;
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
     * @param tipoConexion Tipo de conexión del micrófono.
     */
    public Microfono(String id, String marca, String modelo, double precio, LocalDate fechaRegistro, int valoracion, String rutaImagen, String tipoConexion) {
        super(id, marca, modelo, precio, fechaRegistro, valoracion, rutaImagen);
        this.tipoConexion = tipoConexion;
    }

    public String getTipoConexion() {
        return tipoConexion;
    }

    public void setTipoConexion(String tipoConexion) {
        this.tipoConexion = tipoConexion;
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo de conexión: " + tipoConexion;
    }
}
