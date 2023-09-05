package co.edu.poo2.ProtectoCazaUnac;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class LoginRegistro extends JFrame implements ActionListener {
    static JButton btnNuevoRegistro,btnIngresar, btnLeer;
    static JTextField txtNombre,txtUserName,txtCorreo;
    static String linea;
    static JLabel lblImagenUsuario, lblNombre, lblCorreo, lblUserName, lblPass;
    static JTextArea txtTablero;
    static ArrayList<Usuario> listaUsers = new ArrayList<>();
    private JPasswordField passwordField;

    public LoginRegistro(){

        lblImagenUsuario = new JLabel(new ImageIcon("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\img\\login.png"));
        lblImagenUsuario.setLocation(170, 40);
        lblImagenUsuario.setSize(150, 150);

        lblNombre = new JLabel("Nombres");
        lblNombre.setLocation(70,190);
        lblNombre.setSize(130,30);
        lblNombre.setForeground(Color.BLACK);
        txtNombre = new JTextField(10);
        txtNombre.setLocation(70,220);
        txtNombre.setSize(150,30);

        lblCorreo = new JLabel("Correo Electronico");
        lblCorreo.setLocation(260,190);
        lblCorreo.setSize(130,30);
        lblCorreo.setForeground(Color.BLACK);
        txtCorreo = new JTextField(10);
        txtCorreo.setLocation(260,220);
        txtCorreo.setSize(150,30);

        lblUserName = new JLabel("UserName");
        lblUserName.setLocation(70,250);
        lblUserName.setSize(130,30);
        lblUserName.setForeground(Color.BLACK);
        txtUserName = new JTextField(10);
        txtUserName.setLocation(70,280);
        txtUserName.setSize(150,30);

        lblPass = new JLabel("Contraseña");
        lblPass.setLocation(260,250);
        lblPass.setSize(130,30);
        lblPass.setForeground(Color.BLACK);
        passwordField = new JPasswordField(10);
        passwordField.setLocation(260, 280);
        passwordField.setSize(150, 30);
        // para que muestre asteriscos en lugar de los caracteres reales
        passwordField.setEchoChar('*');

        btnNuevoRegistro = new JButton("Registrarse");
        btnNuevoRegistro.setLocation(80,380);
        btnNuevoRegistro.setSize(160,30);
        btnNuevoRegistro.addActionListener(this);
        btnNuevoRegistro.setForeground(Color.BLACK);

        btnIngresar = new JButton("Iniciar sesión");
        btnIngresar.setLocation(250,380);
        btnIngresar.setSize(130,30);
        btnIngresar.addActionListener(this);
        btnIngresar.setForeground(Color.BLACK);

        btnLeer = new JButton("Leer AP");
        btnLeer.setLocation(50,30);
        btnLeer.setSize(90,30);
        btnLeer.addActionListener(this);
        btnLeer.setForeground(Color.BLACK);


        //botonera
        add(lblNombre);
        add(lblCorreo);
        add(lblUserName);
        add(lblPass);
        add(btnNuevoRegistro);
        add(btnIngresar);
        add(btnLeer);
        add(lblImagenUsuario);
        add(txtNombre);
        add(txtCorreo);
        add(txtUserName);
        add(passwordField);

        btnNuevoRegistro.addActionListener(this);
        btnIngresar.addActionListener(this);
        btnLeer.addActionListener(this);

        setLayout(null);
        setSize(500,500);
        setTitle("Registrarse en ProtectoCazaUnac");

        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setLocation(500,250);
        setVisible (true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(btnLeer)){
            try {
                txtTablero.append("");
                linea = LeerArchivo.readFile("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\usuario.txt");
                txtTablero.setText(linea);
                txtTablero.append("Archivo leido\n");
                listaUsers = Listas.crearLista(linea);
            }catch (IOException ioe){
                System.out.println(ioe);
            }
        }

        else if (e.getSource() == btnNuevoRegistro) {
            String nombre = txtNombre.getText();
            String correo = txtCorreo.getText();
            String userName = txtUserName.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);

            Usuario nuevoUsuario = new Usuario(nombre, correo, userName, password);
            listaUsers.add(nuevoUsuario);


            // Limpiar los campos después de registrar
            txtNombre.setText("");
            txtCorreo.setText("");
            txtUserName.setText("");
            passwordField.setText("");

            JOptionPane.showMessageDialog(null, "Registro exitoso.");

        }

        if (e.getSource().equals(btnNuevoRegistro)){
            String escribir="";
            for (Usuario user :listaUsers){
                escribir=escribir+";"+user.getNombreCompleto()
                        +","+user.getCorreoElectronico()+","+user.getUser()+","+ user.getPassword();
            }
            EscribirArchivo.writeFile(escribir,"C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\usuario.txt");
        }

        else if (e.getSource() == btnIngresar) {
            //ocultar ventana
            this.setVisible(false);
            //  ventana de inicio de sesión
            LoginUsuario inicioVentana = new LoginUsuario();
        }

    }


}
