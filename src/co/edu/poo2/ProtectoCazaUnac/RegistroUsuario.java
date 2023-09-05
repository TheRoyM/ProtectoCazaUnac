package co.edu.poo2.ProtectoCazaUnac;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroUsuario extends JFrame implements ActionListener {

    static JButton btnNuevoRegistro;
    static JLabel lblImagenUsuario, lblDatos, lblNombre, lblPassword, lblCorreoElectronico;
    static JTextArea txtTablero;
    static JTextField txtNombre,txtid,txtTipodeUsuario;

    public RegistroUsuario(){
        txtTablero = new JTextArea();
        txtTablero.setLocation(30,70);
        txtTablero.setSize(400,400);

        lblDatos = new JLabel("Digite sus datos");
        lblDatos.setLocation(40,40);
        lblDatos.setSize(130,30);
        lblDatos.setForeground(Color.WHITE);

        lblNombre = new JLabel("Digite su nombre");
        lblNombre.setLocation(80,80);
        lblNombre.setSize(130,30);
        lblNombre.setForeground(Color.WHITE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
