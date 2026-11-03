package com.aitorl.agenciamaterialaudiovisual.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.*;
/**
 * Contiene métodos auxiliares utilizados en la aplicación.
 * Permite mostrar cuadros de diálogo de confirmación
 * para determinadas acciones del usuario.
 *
 */
public class Util {
    public static int mensajeConfirmacion(String mensaje, String titulo){
        return JOptionPane.showConfirmDialog(null,mensaje,titulo,JOptionPane.YES_NO_OPTION);
    }

    public static File leerUltimaRuta() {
        Properties propiedades = new Properties();
        File archivo = new File("config.conf");

        if (!archivo.exists()) {
            return null;
        }
        try (FileInputStream entrada = new FileInputStream(archivo)) {
            propiedades.load(entrada);
            String ruta = propiedades.getProperty("ultimaRutaExportacion", "");
            if (ruta.isEmpty()) {
                return null;
            }
            File carpeta = new File(ruta);

            if (carpeta.isDirectory()) {
                return carpeta;
            }

        } catch (IOException e) {
            return null;
        }

        return null;
    }

    public static boolean guardarUltimaRuta(File carpeta) {
        Properties propiedades = new Properties();

        propiedades.setProperty("ultimaRutaExportacion", carpeta.getAbsolutePath());

        try (FileOutputStream salida = new FileOutputStream("config.conf")) {
            propiedades.store(salida, "Configuracion de exportacion");
            return true;

        } catch (IOException e) {
            return false;
        }
    }
}
