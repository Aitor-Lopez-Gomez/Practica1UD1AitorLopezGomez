package com.aitorl.agenciamaterialaudiovisual;

import com.github.lgooddatepicker.components.DatePicker;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.time.LocalDate;

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
    private JTextField txtCampoEspecifico;
    private JLabel lblBanner;
    private JLabel lblImagenLateral;
    private JLabel lblImagenMaterial;
    private JLabel lblVistaPreviaImagen;
    private JButton btnSeleccionarImagen;
    private JButton btnQuitarImagen;
    private JButton btnAnadir;
    private JButton btnGuardar;
    private JButton btnEliminar;

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
        URL ruta = getClass().getResource("/" + nombreArchivo);

        if(ruta == null){
            lblVistaPreviaImagen.setIcon(null);
            lblVistaPreviaImagen.setText("Imagen no disponible");
            return;
        }
        ImageIcon icono = new ImageIcon(ruta);
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






    public void mostrarError(String mensaje){
        JOptionPane.showMessageDialog(panelPrincipal,mensaje,"Error", JOptionPane.ERROR_MESSAGE);
    }



}
