package co.edu.poo2.ProtectoCazaUnac.interfazesGraficas;

import co.edu.poo2.ProtectoCazaUnac.LeerArchivo;
import co.edu.poo2.ProtectoCazaUnac.Listas;
import co.edu.poo2.ProtectoCazaUnac.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegistroIGU extends JFrame implements ActionListener {
    static JButton btnNuevoRegistro, btnIngresar;
    static JTextField txtNombre, txtUserName, txtCorreo;
    static JLabel lblImagenUsuario, lblNombre, lblCorreo, lblUserName, lblPass, lblInformacion;
    static ArrayList<Usuario> listaUsers = new ArrayList<>();
    private final JPasswordField passwordField;

    public RegistroIGU() throws IOException {
        getContentPane().setBackground(new Color(200, 200, 255));
        lblImagenUsuario = new JLabel(new ImageIcon("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\img\\login.png"));
        lblImagenUsuario.setLocation(160, 40);
        lblImagenUsuario.setSize(150, 150);

        lblNombre = new JLabel("Nombres");
        lblNombre.setLocation(70, 190);
        lblNombre.setSize(130, 30);
        lblNombre.setForeground(Color.BLACK);
        txtNombre = new JTextField(10);
        txtNombre.setLocation(70, 220);
        txtNombre.setSize(150, 30);

        lblCorreo = new JLabel("Correo Electronico");
        lblCorreo.setLocation(260, 190);
        lblCorreo.setSize(130, 30);
        lblCorreo.setForeground(Color.BLACK);
        txtCorreo = new JTextField(10);
        txtCorreo.setLocation(260, 220);
        txtCorreo.setSize(150, 30);

        lblUserName = new JLabel("UserName");
        lblUserName.setLocation(70, 250);
        lblUserName.setSize(130, 30);
        lblUserName.setForeground(Color.BLACK);
        txtUserName = new JTextField(10);
        txtUserName.setLocation(70, 280);
        txtUserName.setSize(150, 30);

        lblPass = new JLabel("Contraseña");
        lblPass.setLocation(260, 250);
        lblPass.setSize(130, 30);
        lblPass.setForeground(Color.BLACK);
        passwordField = new JPasswordField(10);
        passwordField.setLocation(260, 280);
        passwordField.setSize(150, 30);
        passwordField.setEchoChar('*');  // para que muestre asteriscos en lugar de los caracteres reales

        btnNuevoRegistro = new JButton("Registrarse");
        ImageIcon iconoRegistrase = new ImageIcon("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\img\\registrarse2.png");
        btnNuevoRegistro.setIcon(iconoRegistrase);
        btnNuevoRegistro.setLocation(80, 380);
        btnNuevoRegistro.setSize(160, 30);
        btnNuevoRegistro.addActionListener(this);
        btnNuevoRegistro.setForeground(Color.BLACK);

        btnIngresar = new JButton("Iniciar sesión");
        ImageIcon iconoIniciarS = new ImageIcon("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\img\\iniciar2.png");
        btnIngresar.setIcon(iconoIniciarS);
        btnIngresar.setLocation(250, 380);
        btnIngresar.setSize(160, 30);
        btnIngresar.addActionListener(this);
        btnIngresar.setForeground(Color.BLACK);

        // Leer archivo plano
        String infoArchivo = LeerArchivo.readFile("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\archivosplanos\\usuarios.txt");
        lblInformacion = new JLabel("Archivo cargado");
        lblInformacion.setSize(100, 100);
        lblInformacion.setLocation(20, 5);

        // Crear lista de usuarios
        listaUsers = Listas.crearUsuarios(infoArchivo);
        lblInformacion.setText(lblInformacion.getText() + " :: Lista creada");
        lblInformacion.setVisible(false);


        //botonera
        add(lblNombre);
        add(lblCorreo);
        add(lblUserName);
        add(lblPass);
        add(lblInformacion);
        add(btnNuevoRegistro);
        add(btnIngresar);
        add(lblImagenUsuario);
        add(txtNombre);
        add(txtCorreo);
        add(txtUserName);
        add(passwordField);


        setLayout(null);
        setSize(500, 500);
        setTitle("Registrarse en ProtectoUnacAnimal");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(500, 250);
        setLocationRelativeTo(null);
        this.setResizable(false);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNuevoRegistro) {

            String nombre = txtNombre.getText();
            String correo = txtCorreo.getText();
            String userName = txtUserName.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);

            // Verificar que todos los campos obligatorios estén completos
            if (nombre.isEmpty() || correo.isEmpty() || userName.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios. Por favor, complete todos los campos.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
            }else if (!validarCorreo(correo)) { // Verificar si el correo es válido
                JOptionPane.showMessageDialog(null, "Correo electrónico no válido. Por favor, ingrese una dirección de correo válida.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
                txtNombre.setText("");
                txtCorreo.setText("");
                txtUserName.setText("");
                passwordField.setText("");

            } else {
                // Realizar el registro si todos los campos están completos
                Usuario nuevoUsuario = new Usuario(nombre, correo, userName, password);
                listaUsers.add(nuevoUsuario);

                // Limpiar los campos
                txtNombre.setText("");
                txtCorreo.setText("");
                txtUserName.setText("");
                passwordField.setText("");

                JOptionPane.showMessageDialog(null, "Registro exitoso.");

                guardarUsuariosEnArchivo(listaUsers);
            }
        } else if (e.getSource() == btnIngresar) {
            // Ocultar ventana
            this.setVisible(false);
            // Ventana de inicio de sesión
            try {
                UsuarioIGU inicioVentana = new UsuarioIGU();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    private boolean validarCorreo(String correo) {
        // Expresión regular para validar un correo electrónico
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);

        return matcher.matches();
    }
    private void guardarUsuariosEnArchivo(ArrayList<Usuario> usuarios) {
        String rutaArchivo = "C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\archivosplanos\\usuarios.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo));
             BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            ArrayList<String> lineasArchivo = new ArrayList<>();
            String linea;
            while ((linea = reader.readLine()) != null) {
                lineasArchivo.add(linea);
            }

            // Crear una nueva lista para almacenar los usuarios nuevos
            ArrayList<String> usuariosNuevos = new ArrayList<>();

            for (Usuario user : usuarios) {
                boolean existe = false;
                for (String lineaArchivoActual : lineasArchivo) {
                    if (lineaArchivoActual.split(",")[0].equals(user.getNombreCompleto())) {
                        existe = true;
                        break;
                    }
                }

                // Si el usuario no existe en el archivo existente, se agrega a la lista de usuarios nuevos
                if (!existe) {
                    usuariosNuevos.add(user.toString());
                }
            }

            // Se agregan los usuarios nuevos al archivo existente
            for (String usuarioNuevo : usuariosNuevos) {
                writer.write(usuarioNuevo + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
