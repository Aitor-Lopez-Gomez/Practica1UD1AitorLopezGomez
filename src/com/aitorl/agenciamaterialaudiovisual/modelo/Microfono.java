package com.aitorl.agenciamaterialaudiovisual.modelo;

import java.time.LocalDate;

public class Microfono extends MaterialAudiovisual{
    private String tipoConexion;

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
