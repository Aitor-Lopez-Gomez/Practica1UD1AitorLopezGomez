package com.aitorl.agenciamaterialaudiovisual;

import com.aitorl.agenciamaterialaudiovisual.controlador.ControladorMaterial;
import com.aitorl.agenciamaterialaudiovisual.modelo.Inventario;

import javax.swing.*;
/**
 * Clase principal de la aplicación de alquiler audiovisual.
 * Inicializa la ventana, la vista, el inventario
 * y el controlador para ejecutar el programa.
 *
 */
public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Agencia de Alquiler audiovisual");
        Vista vista = new Vista();
        Inventario inventario = new Inventario();


        ventana.setContentPane(vista.getPanelPrincipal());
        ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        ControladorMaterial controlador = new ControladorMaterial(vista, inventario);

        ventana.setSize(1125,775);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        ventana.setJMenuBar(vista.crearBarraMenu());
    }

}
