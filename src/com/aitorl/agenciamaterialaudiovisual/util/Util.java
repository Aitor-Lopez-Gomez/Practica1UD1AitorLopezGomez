package com.aitorl.agenciamaterialaudiovisual.util;

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
}
