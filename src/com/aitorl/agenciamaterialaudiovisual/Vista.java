package com.aitorl.agenciamaterialaudiovisual;

import com.aitorl.agenciamaterialaudiovisual.modelo.*;
import com.github.lgooddatepicker.components.DatePicker;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.regex.Pattern;

public class Vista {
    private JPanel panelPrincipal;
    private JPanel panelMenu;
    private JButton btnMaterial;
    private JButton btnNuevo;
    private JPanel panelContenido;
    private JPanel panelCabecera;
    private JPanel panelTabla;
    private JTextField txtBuscar;
    private JTable tablaMateriales;
    private JLabel lblMaterialRegistra;
    private JScrollPane scrollTabla;
    private JPanel panelFormulario;
    private JLabel lblNuevoMaterial;
    private JPanel panelCuerpo;
    private JPanel panelTipos;
    private JPanel panelDatos;
    private JPanel panelImagen;
    private JLabel lblTipoMaterial;
    private JRadioButton rbCamara;
    private JRadioButton rbMicrofono;
    private JRadioButton rbFoco;
    private JRadioButton rbAltavoz;
    private JRadioButton rbProyector;
    private JLabel lblDatosMaterial;
    private JTextField txtMarca;
    private JTextField txtModelo;
    private JTextField txtPrecio;
    private JLabel lblMarca;
    private JLabel lblModelo;
    private JLabel lblPrecio;
    private JLabel lblFechaRegistro;
    private DatePicker datePickerRegistro;
    private JLabel lblValoracion;
    private JLabel lblCampoEspecifico;
    private JPanel panelEstrellas;
    private JButton btnEstrella1;
    private JButton btnEstrella2;
    private JButton btnEstrella3;
    private JButton btnEstrella4;
    private JButton btnEstrella5;
    private JTextArea txtCampoEspecifico;
    private JLabel lblBanner;
    private JLabel lblImagenLateral;
    private JLabel lblImagenMaterial;
    private JLabel lblVistaPreviaImagen;
    private JButton btnSeleccionarImagen;
    private JButton btnQuitarImagen;
    private JButton btnAnadir;
    private JButton btnGuardar;
    private JButton btnEliminar;
    private JMenuItem menuImportar = new JMenuItem("Importar XML");
    private JMenuItem menuExportar = new JMenuItem("Exportar XML");
    private TableRowSorter<DefaultTableModel> ordenadorTabla;

    private void createUIComponents() {

    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void alPulsarNuevo(ActionListener listener){
        btnNuevo.addActionListener(listener);
    }

    public void alPulsarMateriales(ActionListener listener){
        btnMaterial.addActionListener(listener);
    }

    public void mostrarFormulario(boolean mostrar){
        panelFormulario.setVisible(mostrar);
        panelContenido.revalidate();
        panelContenido.repaint();
    }

    public void alCambiarTipoMaterial(ActionListener listener){
        rbAltavoz.addActionListener(listener);
        rbCamara.addActionListener(listener);
        rbFoco.addActionListener(listener);
        rbMicrofono.addActionListener(listener);
        rbProyector.addActionListener(listener);
    }

    public void cambiarTextoCampo(String texto){
        lblCampoEspecifico.setText(texto);
    }

    public void cambiarImagenMaterial(String nombreArchivo){
        ImageIcon icono;
        File archivo = new File(nombreArchivo);
        if(archivo.isFile()){
            icono = new ImageIcon(archivo.getAbsolutePath());
        } else{
            URL ruta = getClass().getResource("/" + nombreArchivo);

            if(ruta == null){
                lblVistaPreviaImagen.setIcon(null);
                lblVistaPreviaImagen.setText("Imagen no disponible");
                return;
            }
            icono = new ImageIcon(ruta);
        }
        
        Image imagen = icono.getImage().getScaledInstance(210,180, Image.SCALE_SMOOTH);

        lblVistaPreviaImagen.setIcon(new ImageIcon(imagen));
        lblVistaPreviaImagen.setText("");
    }

    public void alPulsarAñadir(ActionListener listener){
        btnAnadir.addActionListener(listener);
    }

    public String obtenerMarca(){
        return txtMarca.getText().trim();

    }

    public String obtenerModelo(){
        return txtModelo.getText().trim();
    }

    public String obtenerPrecio(){
        return txtPrecio.getText().trim();
    }

    public LocalDate obtenerFechaRegistro(){
        return datePickerRegistro.getDate();
    }

    public void alPulsarEstrellas(ActionListener listener){
        btnEstrella1.setActionCommand("1");
        btnEstrella2.setActionCommand("2");
        btnEstrella3.setActionCommand("3");
        btnEstrella4.setActionCommand("4");
        btnEstrella5.setActionCommand("5");

        btnEstrella1.addActionListener(listener);
        btnEstrella2.addActionListener(listener);
        btnEstrella3.addActionListener(listener);
        btnEstrella4.addActionListener(listener);
        btnEstrella5.addActionListener(listener);
    }

    public void pintarEstrellas(int valoracion){
        JButton[] estrellas = {
                btnEstrella1, btnEstrella2, btnEstrella3, btnEstrella4, btnEstrella5
        };

        for (int i = 0; i < estrellas.length; i++) {
            if (i < valoracion){
                estrellas[i].setForeground(new Color(255, 191, 40));
            } else {
                estrellas[i].setForeground(new Color(130,145,155));
            }
        }
    }

    public String obtenerCampoEspecifico(){
        return txtCampoEspecifico.getText().trim();
    }

    public String obtenerTipoMaterial(){
        if(rbCamara.isSelected()) return "Cámara";
        if(rbMicrofono.isSelected()) return  "Micrófono";
        if(rbFoco.isSelected()) return "Foco";
        if(rbProyector.isSelected()) return "Proyector";
        if(rbAltavoz.isSelected()) return "Altavoz";

        return null;
    }

    public void configurarTabla(){
        String[] columnas = {
            "ID", "Tipo", "Marca", "Modelo", "Precio", "Fecha", "Valoración", "Características"
        };
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas,0){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaMateriales.setModel(modeloTabla);
        ordenadorTabla = new TableRowSorter<>(modeloTabla);
        tablaMateriales.setRowSorter(ordenadorTabla);
        Color fondoTabla = new Color(25, 44, 61);
        scrollTabla.getViewport().setBackground(fondoTabla);


    }

    public void actualizarTabla(Collection<MaterialAudiovisual> materiales){
        DefaultTableModel modeloTabla = (DefaultTableModel) tablaMateriales.getModel();
        modeloTabla.setRowCount(0);


        for(MaterialAudiovisual material: materiales){
            String caracterisitca = "";
            if (material instanceof Camara){
                Camara camara = (Camara) material;
                caracterisitca = camara.getResolucion();
            }
            if (material instanceof Microfono){
                Microfono microfono = (Microfono) material;
                caracterisitca = microfono.getTipoConexion();
            }
            if(material instanceof  Foco){
                Foco foco = (Foco) material;
                caracterisitca = foco.getPotenciaVatios() + " W";
            }
            if(material instanceof Altavoz){
                Altavoz altavoz = (Altavoz) material;
                caracterisitca = altavoz.getPotenciaVatios() + " W";
            }
            if(material instanceof  Proyector){
                Proyector proyector = (Proyector) material;
                caracterisitca = proyector.getLuminosidad() + " lm";
            }
            modeloTabla.addRow(new Object[]{
                    material.getId(),
                    material.getClass().getSimpleName(),
                    material.getMarca(),
                    material.getModelo(),
                    material.getPrecioPorDia(),
                    material.getFechaRegistro(),
                    material.getValoracion(),
                    caracterisitca
            });


        }


    }

    public void filtrarTabla(String texto) {
        if (texto.isEmpty()){
            ordenadorTabla.setRowFilter(null);
        } else {
            ordenadorTabla.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(texto)));
        }

    }

    public void alCambiarBusqueda(DocumentListener listener){
        txtBuscar.getDocument().addDocumentListener(listener);
    }

    public String obtenerTextoBusqueda(){
        return txtBuscar.getText().trim();
    }

    public void resetearCampos(){
        JButton[] estrellas = {
                btnEstrella1, btnEstrella2, btnEstrella3, btnEstrella4, btnEstrella5
        };
        txtMarca.setText("");
        txtModelo.setText("");
        txtPrecio.setText("");
        datePickerRegistro.setDate(null);
        pintarEstrellas(0);
        txtCampoEspecifico.setText("");

    }


    public void alSeleccionarMaterial(ListSelectionListener listener){
        tablaMateriales.getSelectionModel().addListSelectionListener(listener);


    }

    public String obtenerIdSeleccionado(){
        int fila = tablaMateriales.getSelectedRow();
        if(fila == -1){
            return null;
        }
        int filaModelo = tablaMateriales.convertRowIndexToModel(fila);
        return tablaMateriales.getModel().getValueAt(filaModelo,0).toString();
    }

    public void cargarMateriales(MaterialAudiovisual material){

        txtMarca.setText(material.getMarca());
        txtModelo.setText(material.getModelo());
        txtPrecio.setText(String.valueOf(material.getPrecioPorDia()));
        datePickerRegistro.setDate(material.getFechaRegistro());
        cambiarImagenMaterial(material.getRutaImagen());

        if (material instanceof  Camara){
            Camara camara = (Camara) material;
            rbCamara.setSelected(true);
            txtCampoEspecifico.setText(camara.getResolucion());
            pintarEstrellas(material.getValoracion());
            cambiarTextoCampo("Resolucion");

        } else if (material instanceof  Microfono){
            Microfono microfono = (Microfono) material;
            rbMicrofono.setSelected(true);
            txtCampoEspecifico.setText((microfono.getTipoConexion()));
            pintarEstrellas(material.getValoracion());
            cambiarTextoCampo("Tipo de conexion: ");
        } else if (material instanceof  Foco){
            Foco foco = (Foco) material;
            rbFoco.setSelected(true);
            txtCampoEspecifico.setText(String.valueOf(foco.getPotenciaVatios()));
            cambiarTextoCampo("Potencia (W): ");
            pintarEstrellas(material.getValoracion());
        } else if (material instanceof Altavoz){
            Altavoz altavoz = (Altavoz) material;
            txtCampoEspecifico.setText(String.valueOf(altavoz.getPotenciaVatios()));
            rbAltavoz.setSelected(true);
            cambiarTextoCampo("Potencia (W): ");
            pintarEstrellas(material.getValoracion());
        } else if(material instanceof Proyector){
            Proyector proyector = (Proyector) material;
            rbProyector.setSelected(true);
            txtCampoEspecifico.setText(String.valueOf(proyector.getLuminosidad()));
            cambiarTextoCampo("Luminosidad (lm): ");
            pintarEstrellas(material.getValoracion());
        }

    }

    public void alPulsarGuardar(ActionListener listener){
        btnGuardar.addActionListener(listener);

    }

    public void alPulsarEliminar(ActionListener listener){
        btnEliminar.addActionListener(listener);
    }

    public boolean confirmarEliminacion(String id){
        int opcion = JOptionPane.showConfirmDialog(panelPrincipal,
                "¿Seguro que quieres eliminar el material " + id + " ?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        return opcion == JOptionPane.YES_OPTION;
    }

    public JMenuBar crearBarraMenu(){
        JMenuBar barraMenu = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");


        menuArchivo.add(menuImportar);
        menuArchivo.add(menuExportar);
        barraMenu.add(menuArchivo);
        return barraMenu;
    }

    public void alPulsarExportar(ActionListener listener){
        menuExportar.addActionListener(listener);
    }

    public File elegirArchvioExportacion(){
        JFileChooser selector = new JFileChooser();
        int opcion = selector.showSaveDialog(panelPrincipal);

        if(opcion == JFileChooser.APPROVE_OPTION){
            return selector.getSelectedFile();
        }
        return null;
    }
    public void alPulsarImportar(ActionListener listener){
        menuImportar.addActionListener(listener);
    }
    public File elegirArchivoImportacion(){
        JFileChooser selector = new JFileChooser();
        selector.setFileFilter(new FileNameExtensionFilter("Archivos XML (*.xml)", "xml"));
        selector.setAcceptAllFileFilterUsed(false);
        int opcion = selector.showOpenDialog(panelPrincipal);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            return selector.getSelectedFile();
        }

        return null;
    }

    public void alPulsarSalir(WindowListener listener){
        JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(panelPrincipal);
        ventana.addWindowListener(listener);
    }
    public void alPulsarSeleccionarImagen(ActionListener listener){
        btnSeleccionarImagen.addActionListener(listener);
    }

    public void alPulsarQuitarImagen(ActionListener listener){
        btnQuitarImagen.addActionListener(listener);
    }

    public File elegirImagen(){
        JFileChooser selector = new JFileChooser();

        selector.setFileFilter(new FileNameExtensionFilter("Imágenes JPG Y PNG", "jpg", "jpeg", "png"));
        selector.setAcceptAllFileFilterUsed(false);

        int opcion = selector.showOpenDialog(panelPrincipal);
        if(opcion == JFileChooser.APPROVE_OPTION){
            return selector.getSelectedFile();
        }
        return null;
    }

    public boolean mostrarImagenSeleccionada(File archivo){
        ImageIcon icono = new ImageIcon(archivo.getAbsolutePath());
        if(icono.getIconWidth() <= 0){
            mostrarError("El archvio sleccionado no es una imagen válida.");
            return false;
        }

        Image imagen = icono.getImage().getScaledInstance(210,180,Image.SCALE_SMOOTH);

        lblVistaPreviaImagen.setIcon(new ImageIcon(imagen));
        lblVistaPreviaImagen.setText("");
        return true;
    }


    public void mostrarError(String mensaje){
        JOptionPane.showMessageDialog(panelPrincipal,mensaje,"Error", JOptionPane.ERROR_MESSAGE);
    }

    public void limpiarSeleccionTabla() {
        tablaMateriales.clearSelection();
    }



}
