package co.edu.poo2.ProtectoCazaUnac;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class RegistroIGU extends JFrame implements ActionListener {
    static JButton btnNuevoRegistro,btnIngresar;
    static JTextField txtNombre,txtUserName,txtCorreo;
    static String linea;
    static JLabel lblImagenUsuario, lblNombre, lblCorreo, lblUserName, lblPass,lblInformacion;
    static ArrayList<Usuario> listaUsers = new ArrayList<>();
    private final JPasswordField passwordField;

    public RegistroIGU() throws IOException {

        lblImagenUsuario = new JLabel(new ImageIcon("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\img\\login.png"));
        lblImagenUsuario.setLocation(160, 40);
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
        passwordField.setEchoChar('*');  // para que muestre asteriscos en lugar de los caracteres reales

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

        // Leer archivo plano
        String infoArchivo = LeerArchivo.readFile("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\usuarios.txt");
        lblInformacion = new JLabel("Archivo cargado");
        lblInformacion.setSize(100,100);
        lblInformacion.setLocation(20, 5);

        // Crear lista de usuarios
        listaUsers = Listas.crearUsuarios(infoArchivo);
        lblInformacion.setText(lblInformacion.getText() + " :: Lista creada");



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
        setSize(500,500);
        setTitle("Registrarse en ProtectoCazaUnac");

        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setLocation(500,250);
        setVisible (true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNuevoRegistro) {
            // Obtener los valores de los campos
            String nombre = txtNombre.getText();
            String correo = txtCorreo.getText();
            String userName = txtUserName.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);

            // Verificar que todos los campos obligatorios estén completos
            if (nombre.isEmpty() || correo.isEmpty() || userName.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios. Por favor, complete todos los campos.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
            } else {
                // Realizar el registro si todos los campos están completos
                Usuario nuevoUsuario = new Usuario(nombre, correo, userName, password);
                listaUsers.add(nuevoUsuario);

                // Limpiar los campos después de registrar
                txtNombre.setText("");
                txtCorreo.setText("");
                txtUserName.setText("");
                passwordField.setText("");

                JOptionPane.showMessageDialog(null, "Registro exitoso.");

                // Guardar la lista de usuarios en el archivo "usuarios.txt" sin sobrescribir
                guardarUsuariosEnArchivo("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\usuarios.txt", listaUsers);
            }
        }

         else if (e.getSource() == btnIngresar) {
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

    private void guardarUsuariosEnArchivo(String nombreArchivo, ArrayList<Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Usuario user : usuarios) {
                String linea = user.getNombreCompleto() + "," + user.getCorreoElectronico() + ","
                        + user.getUser() + "," + user.getPassword() + ";";
                writer.write(linea);
                writer.newLine(); // Agrega una nueva línea después de cada usuario

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
