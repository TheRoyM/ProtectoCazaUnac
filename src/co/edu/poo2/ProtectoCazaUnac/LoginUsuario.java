package co.edu.poo2.ProtectoCazaUnac;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoginUsuario extends JFrame implements ActionListener {

    static JButton btnLeer,btnIngresar, btnRegresar,btnPassOlvi;
    static JLabel lblImagenUsuario, lblUser,lblPass;
    static String linea;
    static JTextField txtUsername;

    static ArrayList<Usuario> listaUsers = new ArrayList<>();
    private JPasswordField passwordField;



    public LoginUsuario(){
        btnLeer = new JButton("Leer AP");
        btnLeer.setLocation(50,30);
        btnLeer.setSize(90,30);
        btnLeer.addActionListener(this);
        btnLeer.setForeground(Color.BLACK);

        lblImagenUsuario = new JLabel(new ImageIcon("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\img\\LoginI.png"));
        lblImagenUsuario.setLocation(170, 80);
        lblImagenUsuario.setSize(150, 150);

        lblUser = new JLabel("UserName");
        lblUser.setLocation(100,220);
        lblUser.setSize(190,30);
        lblUser.setForeground(Color.BLACK);
        txtUsername = new JTextField(10);
        txtUsername.setLocation(70,250);
        txtUsername.setSize(150,30);


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

        btnPassOlvi = new JButton("Haz olvidado tu contraseña");
        btnPassOlvi.setLocation(170,380);
        btnPassOlvi.setSize(130,30);
        btnPassOlvi.addActionListener(this);
        btnPassOlvi.setForeground(Color.BLACK);



        //adicionar
        add(lblImagenUsuario);
        add(lblUser);
        add(lblPass);
        add(txtUsername);
        add(passwordField);
        add(btnIngresar);
        add(btnRegresar);
        add(btnPassOlvi);
        add(btnLeer);



        btnLeer.addActionListener(this);



        setLayout(null);
        setSize(500,500);
        setTitle("Login ProtectoCazaUnac ");

        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setLocation(500,250);
        setVisible (true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIngresar) {
            //ocultar ventana
            this.setVisible(false);
            //  ventana de inicio de sesión
            LoginAnimal inicioVentana = new LoginAnimal();

        }


        else if (e.getSource() == btnRegresar) {
            //ocultar ventana
            this.setVisible(false);
            //  ventana de inicio de sesión
            LoginIGU inicioVentana = new LoginIGU();

        }else if (e.getSource() == btnIngresar) {
            // Obtener el nombre de usuario o correo y la contraseña ingresados

            String nombreUsuario = txtUsername.getText();
            String contrasena = new String(passwordField.getPassword());

            // Validar el usuario y contraseña con el archivo de usuarios
            if (validarCredenciales(nombreUsuario, contrasena)) {


                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");
            }
            else {
                // Las credenciales no son válidas
                JOptionPane.showMessageDialog(null, "Credenciales incorrectas. Por favor, inténtelo de nuevo.");
            }
        }
    }
    private boolean validarCredenciales(String nombreUsuario, String contrasena) {
        // Leo el archivo de usuarios y comparo con las credenciales ingresadas
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\usuario.txt"));

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    String usuarioGuardado = partes[2].trim();
                    String contrasenaGuardada = partes[3].trim();

                    // Compara las credenciales
                    if (usuarioGuardado.equals(nombreUsuario)&& contrasenaGuardada.equals(contrasena))  {
                        br.close();
                        return true; // Credenciales válidas
                    }
                }
            }

            br.close(); // Cierra el archivo
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // Credenciales no válidas
    }
}
