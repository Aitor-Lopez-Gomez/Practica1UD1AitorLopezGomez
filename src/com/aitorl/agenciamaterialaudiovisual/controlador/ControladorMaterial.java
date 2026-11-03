package com.aitorl.agenciamaterialaudiovisual.controlador;

import com.aitorl.agenciamaterialaudiovisual.Vista;
import com.aitorl.agenciamaterialaudiovisual.modelo.*;
import com.aitorl.agenciamaterialaudiovisual.persistencia.GestorFicheros;
import com.aitorl.agenciamaterialaudiovisual.util.Util;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
/**
 * Controla la comunicación entre la vista y el inventario.
 * Gestiona los eventos de los botones, la búsqueda,
 * la selección de materiales y las operaciones
 * de importación y exportación.
 *
 */

public class ControladorMaterial {
    private Vista vista;
    private Inventario inventario;
    private int valoracion = 0;
    private int siguienteId = 1;
    private String idMaterialSeleccionado = null;
    private File imagenSeleccionada = null;
    private boolean quitarImagen = false;

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
                String imagen = "";

                if (tipo.equals("Cámara")){
                    vista.cambiarTextoCampo("Resolución: ");
                    imagen = "camara.jpg";
                } else if (tipo.equals("Micrófono")){
                    vista.cambiarTextoCampo("Tipo de conexión: ");
                    imagen = "microfono.jpg";
                } else if (tipo.equals("Foco")){
                    vista.cambiarTextoCampo("Potencia (W): ");
                    imagen = "foco.jpg";
                } else if (tipo.equals("Altavoz")){
                    vista.cambiarTextoCampo("Potencia (W): ");
                    imagen = "altavoz.jpg";
                } else if (tipo.equals("Proyector")){
                    vista.cambiarTextoCampo("Luminosidad (lm): ");
                    imagen = "proyector.jpg";

                }
                vista.limpiarSeleccionTabla();
                vista.cambiarImagenMaterial(imagen);
            }
        });

        vista.alPulsarAnadir(new ActionListener() {
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
                        String rutaImagen = "camara.jpg";
                        if(imagenSeleccionada != null){
                            rutaImagen = imagenSeleccionada.getPath();
                        }
                        Camara camara = new Camara(id,marca,modelo,precio,fechaRegistro,valoracion,rutaImagen,campoEspecifico);
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
                        String rutaImagen = "microfono.jpg";
                        if(imagenSeleccionada != null){
                            rutaImagen = imagenSeleccionada.getPath();
                        }
                        Microfono microfono = new Microfono(id,marca,modelo,precio,fechaRegistro,valoracion,rutaImagen,campoEspecifico);
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
                        String rutaImagen = "foco.jpg";
                        if(imagenSeleccionada != null){
                            rutaImagen = imagenSeleccionada.getPath();
                        }
                        int potencia = Integer.parseInt(campoEspecifico);
                        if (potencia <= 0) {
                            vista.mostrarError("La potencia debe ser mayor que cero.");
                            return;
                        }
                        Foco foco = new Foco(id,marca,modelo,precio,fechaRegistro,valoracion,rutaImagen,potencia);

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
                        String rutaImagen = "altavoz.jpg";
                        if(imagenSeleccionada != null){
                            rutaImagen = imagenSeleccionada.getPath();
                        }
                        int potencia = Integer.parseInt(campoEspecifico);
                        if (potencia <= 0) {
                            vista.mostrarError("La potencia debe ser mayor que cero.");
                            return;
                        }
                        Altavoz altavoz = new Altavoz(id,marca,modelo,precio,fechaRegistro,valoracion,rutaImagen,potencia);
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
                        String rutaImagen = "proyector.jpg";
                        if(imagenSeleccionada != null){
                            rutaImagen = imagenSeleccionada.getPath();
                        }
                        int luminosidad = Integer.parseInt(campoEspecifico);
                        if (luminosidad <= 0) {
                            vista.mostrarError("La luminosidad debe ser mayor que cero.");
                            return;
                        }
                        Proyector proyector = new Proyector(id,marca,modelo,precio,fechaRegistro,valoracion,rutaImagen,luminosidad);
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
                MaterialAudiovisual materialActual = inventario.buscarMaterial(idMaterialSeleccionado);
                String rutaImagen = materialActual.getRutaImagen();
                if (quitarImagen){
                    rutaImagen = "";
                } else if (imagenSeleccionada != null) {
                    rutaImagen = imagenSeleccionada.getAbsolutePath();
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
                        Camara camara = new Camara(idMaterialSeleccionado,marca,modelo,precio,fechaRegistro,valoracion,rutaImagen,campoEspecifico);
                        boolean actualizando = inventario.actualizarMaterial(camara);

                        if (actualizando){
                            vista.actualizarTabla(inventario.obtenerMateriales());
                            vista.resetearCampos();

                        } else {
                            vista.mostrarError("No se ha encontrado el material");
                        }
                    }
                    if(tipoMaterial.equals("Micrófono")){
                        Microfono microfono = new Microfono(idMaterialSeleccionado,marca,modelo,precio,fechaRegistro,valoracion,rutaImagen,campoEspecifico);
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
                        Foco foco = new Foco(idMaterialSeleccionado,marca,modelo,precio,fechaRegistro,valoracion,rutaImagen,potencia);

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
                        Altavoz altavoz = new Altavoz(idMaterialSeleccionado,marca,modelo,precio,fechaRegistro,valoracion,rutaImagen,potencia);
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
                        Proyector proyector = new Proyector(idMaterialSeleccionado,marca,modelo,precio,fechaRegistro,valoracion,rutaImagen,luminosidad);
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
                    File carpeta = fichero.getAbsoluteFile().getParentFile();

                    Util.guardarUltimaRuta(carpeta);
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
                        String id = material.getId();
                        if(id != null && id.startsWith("MAT-")){
                            try{
                                int numero = Integer.parseInt(id.substring(4));

                                if(numero >= siguienteId){
                                    siguienteId = numero + 1;
                                }
                            }catch (NumberFormatException ex) {
                                System.out.println("ID no numérico: " + id);
                            }
                        }
                    }

                    vista.actualizarTabla(inventario.obtenerMateriales());
                    System.out.println(inventarioImportado.obtenerMateriales().size());
                } catch (ParserConfigurationException  | SAXException | IOException ex){
                    vista.mostrarError("No se ha podido imporar el archvio XML.");
                    ex.printStackTrace();
                }


            }
        });

        vista.alCambiarBusqueda(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscar();
            }

            private void buscar(){
                vista.filtrarTabla(vista.obtenerTextoBusqueda());
            }
        });

        vista.alPulsarSalir(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int respuesta = Util.mensajeConfirmacion("¿Seguro que quieres salir?", "Confirmar salida");

                if(respuesta == JOptionPane.YES_OPTION){
                    e.getWindow().dispose();
                }
            }
        });

        vista.alPulsarSeleccionarImagen(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File archivo = vista.elegirImagen();

                if(archivo == null){
                    return;
                }

                if(vista.mostrarImagenSeleccionada(archivo)){
                    imagenSeleccionada = archivo;
                }


            }
        });

        vista.alPulsarQuitarImagen(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = vista.obtenerTipoMaterial();
                String imagen = "";

                if (tipo == null) {
                    vista.mostrarError("Selecciona un tipo de material.");
                    return;
                }

                if (tipo.equals("Cámara")) {
                    imagen = "";
                } else if (tipo.equals("Micrófono")) {
                    imagen = "";
                } else if (tipo.equals("Foco")) {
                    imagen = "";
                } else if (tipo.equals("Altavoz")) {
                    imagen = "";
                } else if (tipo.equals("Proyector")) {
                    imagen = "";
                }

                imagenSeleccionada = null;
                quitarImagen = true;

                vista.cambiarImagenMaterial(imagen);

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



