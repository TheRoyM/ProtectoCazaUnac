package co.edu.poo2.ProtectoCazaUnac;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginIGU extends JFrame implements ActionListener {

    static JButton btnNuevoRegistro,btnIngresar;
    static JLabel lblImagenUsuario, lblDatos;

    public LoginIGU(){
        lblDatos = new JLabel("Bienvenido");
        Font font = new Font("Forte", Font.BOLD, 20); // tipo de fuente
        lblDatos.setFont(font);
        lblDatos.setLocation(200,50);
        lblDatos.setSize(150,30);
        lblDatos.setForeground(Color.BLACK);

        lblImagenUsuario = new JLabel(new ImageIcon("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\img\\ardill.png"));
        lblImagenUsuario.setLocation(120, 60);
        lblImagenUsuario.setSize(250, 250);

        btnNuevoRegistro = new JButton("Registrarse");
        btnNuevoRegistro.setLocation(110,320);
        btnNuevoRegistro.setSize(130,30);
        btnNuevoRegistro.addActionListener(this);
        btnNuevoRegistro.setForeground(Color.BLACK);

        btnIngresar = new JButton("Iniciar sesión");
        btnIngresar.setLocation(270,320);
        btnIngresar.setSize(130,30);
        btnIngresar.addActionListener(this);
        btnIngresar.setForeground(Color.BLACK);



        add(lblDatos);
        add(btnNuevoRegistro);
        add(btnIngresar);
        add(lblImagenUsuario);

        setLayout(null);
        setSize(500,500);
        setTitle("ProtectoCazaUnac");

        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setLocation(500,250);
        setVisible (true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNuevoRegistro) {
            //ocultar ventana
            this.setVisible(false);
            //ventana de registro de usuario
            Registrarse registroVentana = new Registrarse();

        } else if (e.getSource() == btnIngresar) {
            //ocultar ventana
            this.setVisible(false);
            //  ventana de inicio de sesión
            InicioUsuario inicioVentana = new InicioUsuario();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginIGU();
            }
        });
    }
}
