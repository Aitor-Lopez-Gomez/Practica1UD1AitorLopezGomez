package com.aitorl.agenciamaterialaudiovisual;

import com.github.lgooddatepicker.components.DatePicker;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;

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

}
