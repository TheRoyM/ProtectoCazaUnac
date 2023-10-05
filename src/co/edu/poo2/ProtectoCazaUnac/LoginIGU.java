package co.edu.poo2.ProtectoCazaUnac;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginIGU extends JFrame implements ActionListener {

    static JButton btnNuevoRegistro,btnIngresar;
    static JLabel lblImagenUsuario, lblDatos;

    public LoginIGU(){
        lblDatos = new JLabel("Bienvenido A ProtectoUnacAnimal");
        Font font = new Font("Forte", Font.BOLD, 20); // tipo de fuente
        lblDatos.setFont(font);
        lblDatos.setLocation(100,30);
        lblDatos.setSize(290,30);
        lblDatos.setForeground(Color.BLACK);

        lblImagenUsuario = new JLabel(new ImageIcon("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\img\\Inicio.png"));
        lblImagenUsuario.setLocation(120, 60);
        lblImagenUsuario.setSize(250, 250);

        btnNuevoRegistro = new JButton("Registrarse");
        btnNuevoRegistro.setLocation(110,320);
        btnNuevoRegistro.setSize(130,30);
        btnNuevoRegistro.addActionListener(this);
        btnNuevoRegistro.setForeground(Color.BLACK);

        btnIngresar = new JButton("Ingresar");
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
        setTitle("ProtectoUnacAnimal");

        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setLocation(500,250);
        setVisible (true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNuevoRegistro) {
            // Ocultar ventana actual
            this.setVisible(false);
            // Crear y mostrar la ventana de registro de usuario
            RegistroIGU registroVentana = null;
            try {
                registroVentana = new RegistroIGU();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            registroVentana.setVisible(true);
        } else if (e.getSource() == btnIngresar) {
            // Ocultar ventana actual
            this.setVisible(false);
            // Crear y mostrar la ventana de inicio de sesión
            UsuarioIGU inicioVentana = null;
            try {
                inicioVentana = new UsuarioIGU();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            inicioVentana.setVisible(true);
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
