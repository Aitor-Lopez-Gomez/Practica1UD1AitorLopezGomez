package com.aitorl.agenciamaterialaudiovisual.controlador;

import com.aitorl.agenciamaterialaudiovisual.Vista;
import com.aitorl.agenciamaterialaudiovisual.modelo.Camara;
import com.aitorl.agenciamaterialaudiovisual.modelo.Inventario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;


public class ControladorMaterial {
    private Vista vista;
    private Inventario inventario;
    private int valoracion = 4;
    private int siguienteId = 1;

    public ControladorMaterial(Vista vista, Inventario inventario){
        this.vista = vista;
        this.inventario = inventario;
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

        vista.alPulsarAñadir(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = "MAT-" + siguienteId;
                String marca = vista.obtenerMarca();
                String modelo = vista.obtenerModelo();
                String precioTexto = vista.obtenerPrecio();
                LocalDate fechaRegistro = vista.obtenerFechaRegistro();
                String campoEspecifico = vista.obtenerCampoEspecifico();
                String tipoMaterial = vista.obtenerTipoMaterial();



                if(marca.isEmpty() || modelo.isEmpty()){
                    vista.mostrarError("La marca y el modelo son obligatorios.");
                    return;
                }
                if (precioTexto.isEmpty()) {
                    vista.mostrarError("El precio es obligatorio.");
                    return;
                }
                if(fechaRegistro == null){
                    vista.mostrarError("Debes seleccionar una fecha de registro.");
                    return;
                }
                if(campoEspecifico.isEmpty()){
                    vista.mostrarError("El campo especifico es obligatorio.");
                    return;
                }
                if(tipoMaterial.isEmpty()){
                    vista.mostrarError("Debes seleccionar un tipo de material.");
                    return;
                }



                try{
                    double precio = Double.parseDouble((precioTexto));
                    if(precio <= 0){
                        vista.mostrarError("El precio tiene que ser mayor que cero.");
                        return;
                    }

                    System.out.println("ID preparado: " + id);
                    System.out.println("Marca introducida: " + marca);
                    System.out.println("Modelo introducido: " + modelo);
                    System.out.println("Precio introducido: " + precio);
                    System.out.println("Fecha de registro:" + fechaRegistro);
                    System.out.println("Valoracion: " + valoracion);
                    System.out.println("Campo espicifico: " + campoEspecifico);
                    System.out.println("Tipo de material: " + tipoMaterial);
                } catch (NumberFormatException ex){
                    vista.mostrarError("Introduce un precio numérico válido.");
                }


            }
        });

        vista.alPulsarEstrellas(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                valoracion = Integer.parseInt(e.getActionCommand());
                vista.pintarEstrellas(valoracion);

            }
        });
    }



}
