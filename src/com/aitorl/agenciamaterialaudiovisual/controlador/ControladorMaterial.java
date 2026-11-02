package com.aitorl.agenciamaterialaudiovisual.controlador;

import com.aitorl.agenciamaterialaudiovisual.Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControladorMaterial {
    private Vista vista;

    public ControladorMaterial(Vista vista){
        this.vista = vista;
        vista.cambiarImagenMaterial("camara.jpg");

        vista.mostrarFormulario(false);

        vista.alPulsarNuevo(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.mostrarFormulario(true);
            }
        });

        vista.alPulsarMateriales(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.mostrarFormulario(false);
            }
        });

        vista.alCambiarTipoMaterial(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = e.getActionCommand();

                if (tipo.equals("Cámara")){
                    vista.cambiarTextoCampo("Resolución: ");
                    vista.cambiarImagenMaterial("camara.jpg");
                } else if (tipo.equals("Micrófono")){
                    vista.cambiarTextoCampo("Tipo de conexión: ");
                    vista.cambiarImagenMaterial("microfono.jpg");
                } else if (tipo.equals("Foco")){
                    vista.cambiarTextoCampo("Potencia (W): ");
                    vista.cambiarImagenMaterial("foco.jpg");
                } else if (tipo.equals("Altavoz")){
                    vista.cambiarTextoCampo("Potencia (W): ");
                    vista.cambiarImagenMaterial("altavoz.jpg");
                } else if (tipo.equals("Proyector")){
                    vista.cambiarTextoCampo("Luminosidad (lm): ");
                    vista.cambiarImagenMaterial("proyector.jpg");
                }
            }
        });


    }



}
