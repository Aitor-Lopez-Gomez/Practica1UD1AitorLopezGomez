package com.aitorl.agenciamaterialaudiovisual.controlador;

import com.aitorl.agenciamaterialaudiovisual.Vista;
import com.aitorl.agenciamaterialaudiovisual.modelo.*;
import com.aitorl.agenciamaterialaudiovisual.persistencia.GestorFicheros;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;


public class ControladorMaterial {
    private Vista vista;
    private Inventario inventario;
    private int valoracion = 0;
    private int siguienteId = 1;
    private String idMaterialSeleccionado = null;

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
                vista.resetearCampos();
                vista.limpiarSeleccionTabla();
            }
        });

        vista.alPulsarMateriales(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.mostrarFormulario(false);
                vista.limpiarSeleccionTabla();
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

                if(!validarCamposObligatorios(marca, modelo, precioTexto, fechaRegistro, campoEspecifico, tipoMaterial)){
                    return;
                };

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

        vista.alSeleccionarMaterial(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()){
                    return;
                }

                String id = vista.obtenerIdSeleccionado();

                if(id == null){
                    return;
                }

                MaterialAudiovisual material = inventario.buscarMaterial(id);

                if(material != null){
                    idMaterialSeleccionado = material.getId();

                    valoracion = material.getValoracion();
                    vista.mostrarFormulario(true);
                    vista.cargarMateriales(material);


                }
            }
        });

        vista.alPulsarGuardar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idMaterialSeleccionado == null){
                    vista.mostrarError("Primero debes seleccionar un material de la tabla.");
                    return;
                }


                String marca = vista.obtenerMarca();
                String modelo = vista.obtenerModelo();
                String precioTexto = vista.obtenerPrecio();
                LocalDate fechaRegistro = vista.obtenerFechaRegistro();
                String campoEspecifico = vista.obtenerCampoEspecifico();
                String tipoMaterial = vista.obtenerTipoMaterial();

                if(!validarCamposObligatorios(marca, modelo, precioTexto, fechaRegistro, campoEspecifico, tipoMaterial)){
                    return;
                }
                try{
                    double precio = Double.parseDouble(precioTexto);

                    if (precio <= 0) {
                        vista.mostrarError("El precio debe ser mayor que cero.");
                        return;
                    }

                    if (tipoMaterial.equals("Cámara")){
                        Camara camara = new Camara(idMaterialSeleccionado,marca,modelo,precio,fechaRegistro,valoracion,"camara.jpg",campoEspecifico);
                        boolean actualizando = inventario.actualizarMaterial(camara);

                        if (actualizando){
                            vista.actualizarTabla(inventario.obtenerMateriales());
                            vista.resetearCampos();

                        } else {
                            vista.mostrarError("No se ha encontrado el material");
                        }
                    }
                    if(tipoMaterial.equals("Micrófono")){
                        Microfono microfono = new Microfono(idMaterialSeleccionado,marca,modelo,precio,fechaRegistro,valoracion,"microfono.jpg",campoEspecifico);
                        boolean actualizando = inventario.actualizarMaterial(microfono);

                        if(actualizando){
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
                        Foco foco = new Foco(idMaterialSeleccionado,marca,modelo,precio,fechaRegistro,valoracion,"foco.jpg",potencia);

                        boolean actualizando = inventario.actualizarMaterial(foco);

                        if(actualizando){
                            vista.actualizarTabla(inventario.obtenerMateriales());

                            vista.resetearCampos();

                        }  else {
                            vista.mostrarError("Ya existe un material con ese ID.");
                        }
                    }
                    if(tipoMaterial.equals("Altavoz")){
                        int potencia = Integer.parseInt(campoEspecifico);
                        if (potencia <= 0) {
                            vista.mostrarError("La potencia debe ser mayor que cero.");
                            return;
                        }
                        Altavoz altavoz = new Altavoz(idMaterialSeleccionado,marca,modelo,precio,fechaRegistro,valoracion,"altavoz.jpg",potencia);
                        boolean actualizando = inventario.actualizarMaterial(altavoz);

                        if(actualizando){
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
                        Proyector proyector = new Proyector(idMaterialSeleccionado,marca,modelo,precio,fechaRegistro,valoracion,"proyector.jpg",luminosidad);
                        boolean actualizando = inventario.actualizarMaterial(proyector);

                        if(actualizando){
                            vista.actualizarTabla(inventario.obtenerMateriales());
                            vista.resetearCampos();

                        } else {
                            vista.mostrarError("Ya existe un material con ese ID.");
                        }
                    }

                } catch (NumberFormatException ex) {
                    vista.mostrarError("Introduce un precio numérico válido.");
                }



            }
        });

        vista.alPulsarEliminar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(idMaterialSeleccionado == null){
                    vista.mostrarError("Primero debes seleccionar un material.");
                    return;
                }

                if(!vista.confirmarEliminacion(idMaterialSeleccionado)){
                    return;
                }

                boolean eliminado = inventario.eliminarMaterial(idMaterialSeleccionado);

                if(eliminado){
                    idMaterialSeleccionado = null;
                    valoracion = 0;

                    vista.limpiarSeleccionTabla();
                    vista.resetearCampos();
                    vista.actualizarTabla(inventario.obtenerMateriales());
                    vista.mostrarFormulario(false);
                } else {
                    vista.mostrarError("No se ha encontrado el material.");
                }
            }
        });

        vista.alPulsarExportar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File fichero = vista.elegirArchvioExportacion();

                if(fichero == null){
                    return;
                }
                if(!fichero.getName().toLowerCase().endsWith(".xml")){
                    fichero = new File(fichero.getAbsolutePath() + ".xml");
                }

                if(fichero.exists()){
                    vista.mostrarError("El archivo ya esxiste. Elige otro nombre.");
                    return;
                }

                GestorFicheros gestor = new GestorFicheros();
                try {
                    gestor.exportarXML(inventario,fichero);
                } catch (ParserConfigurationException | TransformerException ex){
                    ex.printStackTrace();
                    vista.mostrarError("No se ha podido exportar el inventario");
                }

            }
        });

        vista.alPulsarImportar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                File fichero = vista.elegirArchivoImportacion();

                if (fichero == null){
                    return;
                }
                try{
                    GestorFicheros gestor = new GestorFicheros();
                    Inventario inventarioImportado = gestor.importarXML(fichero);
                    for (MaterialAudiovisual material: inventarioImportado.obtenerMateriales()){
                        inventario.agregarMaterial(material);
                    }

                    vista.actualizarTabla(inventario.obtenerMateriales());
                    System.out.println(inventarioImportado.obtenerMateriales().size());
                } catch (ParserConfigurationException  | SAXException | IOException ex){
                    vista.mostrarError("No se ha podido imporar el archvio XML.");
                    ex.printStackTrace();
                }


            }
        });
    }



    private boolean validarCamposObligatorios(String marca, String modelo, String precioTexto, LocalDate fechaRegistro, String campoEspecifico, String tipoMaterial) {

        if (marca.isEmpty() || modelo.isEmpty()) {
            vista.mostrarError("La marca y el modelo son obligatorios.");
            return false;
        }

        if (precioTexto.isEmpty()) {
            vista.mostrarError("El precio es obligatorio.");
            return false;
        }

        if (fechaRegistro == null) {
            vista.mostrarError("Debes seleccionar una fecha.");
            return false;
        }

        if (tipoMaterial == null) {
            vista.mostrarError("Debes seleccionar un tipo de material.");
            return false;
        }

        if (campoEspecifico.isEmpty()) {
            vista.mostrarError("El campo específico es obligatorio.");
            return false;
        }

        if (valoracion == 0) {
            vista.mostrarError("Debes seleccionar una valoración.");
            return false;
        }

        return true;
    }





}




