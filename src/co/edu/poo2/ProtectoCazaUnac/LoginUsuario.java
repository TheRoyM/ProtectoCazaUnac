package co.edu.poo2.ProtectoCazaUnac;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginUsuario extends JFrame implements ActionListener {

    static JButton btnLeer,btnIngresar, btnRegresar;
    static JLabel lblImagenUsuario, lblCorreo,lblPass;
    static String linea;
    static JTextField txtCorreo;

    static ArrayList<Usuario> listaUsers = new ArrayList<>();
    private JPasswordField passwordField;



    public LoginUsuario(){

        lblImagenUsuario = new JLabel(new ImageIcon("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\img\\login.png"));
        lblImagenUsuario.setLocation(170, 40);
        lblImagenUsuario.setSize(150, 150);

        lblCorreo = new JLabel("UserName o Correo Electronico");
        lblCorreo.setLocation(50,220);
        lblCorreo.setSize(190,30);
        lblCorreo.setForeground(Color.BLACK);
        txtCorreo = new JTextField(10);
        txtCorreo.setLocation(70,250);
        txtCorreo.setSize(150,30);


        lblPass = new JLabel("Contraseña");
        lblPass.setLocation(290,220);
        lblPass.setSize(130,30);
        lblPass.setForeground(Color.BLACK);
        passwordField = new JPasswordField(10);
        passwordField.setLocation(260, 250);
        passwordField.setSize(150, 30);
        // para que muestre asteriscos en lugar de los caracteres reales
        passwordField.setEchoChar('*');

        btnIngresar = new JButton("Iniciar sesión");
        btnIngresar.setLocation(100,340);
        btnIngresar.setSize(130,30);
        btnIngresar.addActionListener(this);
        btnIngresar.setForeground(Color.BLACK);


        btnRegresar = new JButton("Regresar");
        btnRegresar.setLocation(250,340);
        btnRegresar.setSize(130,30);
        btnRegresar.addActionListener(this);
        btnRegresar.setForeground(Color.BLACK);



        //adicionar
        add(lblImagenUsuario);
        add(lblCorreo);
        add(lblPass);
        add(txtCorreo);
        add(passwordField);
        add(btnIngresar);
        add(btnRegresar);


        btnIngresar.addActionListener(this);
        btnRegresar.addActionListener(this);



        setLayout(null);
        setSize(500,500);
        setTitle("Login ProtectoCazaUnac ");

        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setLocation(500,250);
        setVisible (true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnRegresar) {
            //ocultar ventana
            this.setVisible(false);
            //  ventana de inicio de sesión
            LoginIGU inicioVentana = new LoginIGU();
        }
    }
}
