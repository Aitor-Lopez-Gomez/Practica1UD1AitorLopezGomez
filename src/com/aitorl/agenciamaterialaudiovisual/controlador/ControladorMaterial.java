package com.aitorl.agenciamaterialaudiovisual.controlador;

import com.aitorl.agenciamaterialaudiovisual.Vista;
import com.aitorl.agenciamaterialaudiovisual.modelo.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;


public class ControladorMaterial {
    private Vista vista;
    private Inventario inventario;
    private int valoracion = 0;
    private int siguienteId = 1;

    public ControladorMaterial(Vista vista, Inventario inventario){
        this.vista = vista;
        this.inventario = inventario;
        vista.configurarTabla();
        vista.resetearCampos();
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
                if (tipoMaterial == null) {
                    vista.mostrarError("Debes seleccionar un tipo de material.");
                    return;
                }



                try{
                    double precio = Double.parseDouble((precioTexto));

                    if(precio <= 0){
                        vista.mostrarError("El precio tiene que ser mayor que cero.");
                        return;
                    }


                    if(tipoMaterial.equals("Cámara")){
                        Camara camara = new Camara(id,marca,modelo,precio,fechaRegistro,valoracion,"camara.jpg",campoEspecifico);
                        boolean agregado = inventario.agregarMaterial(camara);

                        if(agregado){
                            siguienteId++;
                            vista.actualizarTabla(inventario.obtenerMateriales());
                            vista.resetearCampos();

                        } else {
                            vista.mostrarError("Ya existe un material con ese ID.");
                        }
                    }
                    if(tipoMaterial.equals("Micrófono")){
                        Microfono microfono = new Microfono(id,marca,modelo,precio,fechaRegistro,valoracion,"microfono.jpg",campoEspecifico);
                        boolean agregado = inventario.agregarMaterial(microfono);

                        if(agregado){
                            siguienteId++;
                            vista.actualizarTabla(inventario.obtenerMateriales());
                            vista.resetearCampos();

                        } else {
                            vista.mostrarError("Ya existe un material con ese ID.");
                        }
                    }
                    if(tipoMaterial.equals("Foco")){
                        int potencia = Integer.parseInt(campoEspecifico);
                        if (potencia <= 0) {
                            vista.mostrarError("La potencia debe ser mayor que cero.");
                            return;
                        }
                        Foco foco = new Foco(id,marca,modelo,precio,fechaRegistro,valoracion,"foco.jpg",potencia);

                        boolean agregado = inventario.agregarMaterial(foco);

                        if(agregado){
                            siguienteId++;
                            vista.actualizarTabla(inventario.obtenerMateriales());
                            vista.resetearCampos();

                        } else {
                            vista.mostrarError("Ya existe un material con ese ID.");
                        }
                    }
                    if(tipoMaterial.equals("Altavoz")){
                        int potencia = Integer.parseInt(campoEspecifico);
                        if (potencia <= 0) {
                            vista.mostrarError("La potencia debe ser mayor que cero.");
                            return;
                        }
                        Altavoz altavoz = new Altavoz(id,marca,modelo,precio,fechaRegistro,valoracion,"altavoz.jpg",potencia);
                        boolean agregado = inventario.agregarMaterial(altavoz);

                        if(agregado){
                            siguienteId++;
                            vista.actualizarTabla(inventario.obtenerMateriales());
                            vista.resetearCampos();

                        } else {
                            vista.mostrarError("Ya existe un material con ese ID.");
                        }
                    }
                    if(tipoMaterial.equals("Proyector")){
                        int luminosidad = Integer.parseInt(campoEspecifico);
                        if (luminosidad <= 0) {
                            vista.mostrarError("La luminosidad debe ser mayor que cero.");
                            return;
                        }
                        Proyector proyector = new Proyector(id,marca,modelo,precio,fechaRegistro,valoracion,"proyector.jpg",luminosidad);
                        boolean agregado = inventario.agregarMaterial(proyector);

                        if(agregado){
                            siguienteId++;
                            vista.actualizarTabla(inventario.obtenerMateriales());
                            vista.resetearCampos();

                        } else {
                            vista.mostrarError("Ya existe un material con ese ID.");
                        }
                    }



                } catch (NumberFormatException ex){
                    vista.mostrarError("Introduce valores numéricos válidos en los campos correspondientes.");
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
