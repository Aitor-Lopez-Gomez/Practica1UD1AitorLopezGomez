package com.aitorl.agenciamaterialaudiovisual.util;

import javax.swing.*;

public class Util {
    public static int mensajeConfirmacion(String mensaje, String titulo){
        return JOptionPane.showConfirmDialog(null,mensaje,titulo,JOptionPane.YES_NO_OPTION);
    }
}
