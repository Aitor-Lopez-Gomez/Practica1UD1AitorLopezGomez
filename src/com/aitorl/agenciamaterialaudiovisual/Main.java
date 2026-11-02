package com.aitorl.agenciamaterialaudiovisual;

import com.aitorl.agenciamaterialaudiovisual.controlador.ControladorMaterial;
import com.aitorl.agenciamaterialaudiovisual.modelo.Inventario;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Agencia de Alquiler audiovisual");
        Vista vista = new Vista();
        Inventario inventario = new Inventario();
        ControladorMaterial controlador = new ControladorMaterial(vista, inventario);

        ventana.setContentPane(vista.getPanelPrincipal());
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ventana.setSize(1125,750);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

}
