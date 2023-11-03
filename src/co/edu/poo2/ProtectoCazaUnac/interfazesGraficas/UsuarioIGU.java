package co.edu.poo2.ProtectoCazaUnac.interfazesGraficas;

import co.edu.poo2.ProtectoCazaUnac.LeerArchivo;
import co.edu.poo2.ProtectoCazaUnac.Listas;
import co.edu.poo2.ProtectoCazaUnac.Usuario;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UsuarioIGU extends JFrame implements ActionListener {

    static JButton btnLeer,btnIngresar, btnRegresar,btnPassOlvidada;
    static JLabel lblImagenUsuario, lblUser,lblPass, lblInformacion;
    static String linea;
    static JTextField txtUsername;

    static ArrayList<Usuario> listaUsers = new ArrayList<>();
    private JPasswordField passwordField;



    public UsuarioIGU() throws IOException {
        getContentPane().setBackground(new Color(200, 200, 255));
        lblImagenUsuario = new JLabel(new ImageIcon("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\img\\LoginI.png"));
        lblImagenUsuario.setLocation(170, 50);
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

        btnRegresar = new JButton("Regresar");
        ImageIcon iconoRegresar = new ImageIcon("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\img\\atras2.png");
        btnRegresar.setIcon(iconoRegresar);
        btnRegresar.setLocation(100,340);
        btnRegresar.setSize(140,30);
        btnRegresar.addActionListener(this);
        btnRegresar.setForeground(Color.BLACK);

        btnIngresar = new JButton("Iniciar sesión");
        ImageIcon iconoIniciar = new ImageIcon("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\img\\iniciarS.png");
        btnIngresar.setIcon(iconoIniciar);
        btnIngresar.setLocation(250,340);
        btnIngresar.setSize(140,30);
        btnIngresar.addActionListener(this);
        btnIngresar.setForeground(Color.BLACK);



        btnPassOlvidada = new JButton("¿Olvidaste tu contraseña o usuario?");
        ImageIcon iconoOlvidar = new ImageIcon("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\img\\olvidar.png");
        btnPassOlvidada.setIcon(iconoOlvidar);
        btnPassOlvidada.setLocation(105, 380);
        btnPassOlvidada.setSize(280, 30);
        btnPassOlvidada.addActionListener(this);
        btnPassOlvidada.setForeground(Color.BLUE);


        // Leer archivo plano
        String infoArchivo = LeerArchivo.readFile("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\archivosplanos\\usuarios.txt");
        lblInformacion = new JLabel("Archivo cargado");
        lblInformacion.setSize(100,100);
        lblInformacion.setLocation(50, 30);

        // Crear lista de usuarios
        listaUsers = Listas.crearUsuarios(infoArchivo);
        lblInformacion.setText(lblInformacion.getText() + " :: Lista creada");
        lblInformacion.setVisible(false);




        //adicionar
        add(lblImagenUsuario);
        add(lblUser);
        add(lblPass);
        add(lblInformacion);
        add(txtUsername);
        add(passwordField);
        add(btnIngresar);
        add(btnRegresar);
        add(btnPassOlvidada);



        setLayout(null);
        setSize(500,500);
        setTitle("Login ProtectoUnacAnimal ");

        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setLocation(500,250);
        this.setResizable(false);
        setLocationRelativeTo(null);
        setVisible (true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPassOlvidada) {
            String correoElectronico = JOptionPane.showInputDialog(
                    this, "Por favor, ingresa tu dirección de correo electrónico:", "Recuperar contraseña o usuario", JOptionPane.PLAIN_MESSAGE
            );

            if (correoElectronico != null && !correoElectronico.isEmpty()) {
                String informacion = buscarUsuarioPorCorreo(correoElectronico); // Implementa esta función

                if (informacion != null) {
                    JOptionPane.showMessageDialog(
                            this, "Tus credenciales son: " + informacion, "Información de usuario", JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            this, "No se encontró ninguna cuenta con ese correo electrónico.", "Error", JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }

        if (e.getSource() == btnIngresar) {
            // Obtener el nombre de usuario o correo y la contraseña ingresados
            String nombreUsuario = txtUsername.getText();
            String contrasena = new String(passwordField.getPassword());

            // Validar el usuario y contraseña con el archivo de usuarios
            if (validarCredenciales(nombreUsuario, contrasena)) {
                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");

                // Redirigir a la ventana
                AnimalesIGU animales = null;
                try {
                    animales = new AnimalesIGU();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                animales.setVisible(true);

                // Cerrar la ventana actual
                this.dispose();
            } else {
                // Las credenciales no son válidas
                JOptionPane.showMessageDialog(null, "Credenciales incorrectas. Por favor, inténtelo de nuevo.");
                txtUsername.setText("");
                passwordField.setText("");
            }

        } else if (e.getSource() == btnRegresar) {
            // Ocultar ventana y volver a la ventana de inicio de sesión
            this.setVisible(false);
            LoginIGU inicioVentana = new LoginIGU();
        }
    }
    private boolean validarCredenciales(String nombreUsuario, String contrasena) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\archivosplanos\\usuarios.txt"));

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] usuarios = linea.split(";");
                for (String usuarioInfo : usuarios) {
                    String[] partes = usuarioInfo.split(",");
                    if (partes.length == 4) {
                        String usuarioGuardado = partes[2].trim();
                        String contrasenaGuardada = partes[3].trim();

                        // Compara las credenciales
                        if (usuarioGuardado.equals(nombreUsuario) && contrasenaGuardada.equals(contrasena)) {
                            br.close();
                            return true; // Credenciales válidas
                        }
                    }
                }
            }

            br.close(); // Cierra el archivo
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // Credenciales no válidas
    }

    private String buscarUsuarioPorCorreo(String correoElectronico) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\archivosplanos\\usuarios.txt"));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4 && partes[1].trim().equals(correoElectronico)) {
                    br.close();
                    return "Nombre Completo: " + partes[0] + "\nUsuario: " + partes[2] + "\nContraseña: " + partes[3];
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
