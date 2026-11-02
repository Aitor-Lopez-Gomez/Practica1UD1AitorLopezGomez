package com.aitorl.agenciamaterialaudiovisual;

import com.aitorl.agenciamaterialaudiovisual.controlador.ControladorMaterial;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Agencia de Alquiler audiovisual");
        Vista vista = new Vista();
        ControladorMaterial controlador = new ControladorMaterial(vista);

        ventana.setContentPane(vista.getPanelPrincipal());
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ventana.setSize(1125,750);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

}
