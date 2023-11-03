package co.edu.poo2.ProtectoCazaUnac.interfazesGraficas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class VentanaConcientizacion extends JFrame implements ActionListener {
    static ArrayList<String> frases = new ArrayList<>();
    static JButton btnRegresar, btnSalir;
    static JLabel lblDatosCuriosos;
    static JEditorPane editorPane;
    static Random random = new Random();

    public VentanaConcientizacion() {
        this.setTitle("Concientización ProtectoUnacAnimal");
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel imagen2 = new JLabel();
        imagen2.setIcon(new ImageIcon("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\img\\seg.png"));
        imagen2.setBounds(730, 180, 250, 250);

        lblDatosCuriosos = new JLabel("Datos Curiosos");
        lblDatosCuriosos.setFont(new Font("Forte", Font.BOLD, 20));
        lblDatosCuriosos.setBounds(730, 40, 300, 30);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(200, 200, 255));

        JLabel imagen = new JLabel();
        imagen.setIcon(new ImageIcon("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\img\\Imagen2.png"));
        imagen.setBounds(120, -30, 500, 500);

        editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        String initialText = "<html><body style='font-family: Arial, sans-serif; font-size: 20px'>Sin exagerar, el clima de Medellín es el mejor del mundo. No por nada le dicen la ciudad de la eterna primavera.</body></html>";
        editorPane.setText(initialText);
        editorPane.setFont(new Font("Arial", Font.PLAIN, 20));
        editorPane.setBackground(new Color(200, 200, 255));
        editorPane.setBounds(650, 70, 300, 160);
        editorPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 80, 10));
        mainPanel.add(editorPane);
        editorPane.setEditable(false);

        JLabel etiqueta = new JLabel();
        etiqueta.setText("Los animales son nuestros compañeros, debemos cuidarlos.");
        etiqueta.setFont(new Font("Arial", Font.PLAIN, 20));
        etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
        etiqueta.setBounds(-100, 410, 980, 50);
        cargarFrases();

        JButton cambiarFraseBoton = new JButton("Cambiar frase");
        cambiarFraseBoton.setBounds(320, 480, 160, 30);
        ImageIcon iconoCambiarFrase = new ImageIcon("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\img\\cambiar.png");
        cambiarFraseBoton.setIcon(iconoCambiarFrase);

        btnRegresar = new JButton("Regresar");
        ImageIcon iconoAtras = new ImageIcon("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\img\\atras.png");
        btnRegresar.setIcon(iconoAtras);
        btnRegresar.setBounds(180, 480, 120, 30);
        btnRegresar.addActionListener(this);

        btnSalir = new JButton("Salir");
        ImageIcon iconoSalir = new ImageIcon("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\img\\salir.png");
        btnSalir.setIcon(iconoSalir);
        btnSalir.setBounds(500, 480, 120, 30);
        btnSalir.addActionListener(this);
        cambiarFraseBoton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!frases.isEmpty()) {
                    int randomIndex = random.nextInt(frases.size());
                    etiqueta.setText(frases.get(randomIndex));
                }
                cargarDatosCuriososRandom(); // Llama a la función para cargar datos curiosos al azar
            }
        });

        mainPanel.add(imagen);
        mainPanel.add(imagen2);
        mainPanel.add(etiqueta);
        mainPanel.add(cambiarFraseBoton);
        mainPanel.add(lblDatosCuriosos);
        add(btnRegresar);
        add(btnSalir);

        this.add(mainPanel);
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegresar) {
            this.setVisible(false);
            AnimalesIGU animalesIGU = null;
            try {
                animalesIGU = new AnimalesIGU();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            animalesIGU.setVisible(true);
        } else if (e.getSource()== btnSalir) {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que deseas salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Gracias por usar la aplicación. ¡Hasta luego!");
                System.exit(0); // Cierra la aplicación
            }
        }
    }

    private void cargarFrases() {
        try (Scanner scanner = new Scanner(new File("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\archivosplanos\\frases.txt"))) {
            while (scanner.hasNextLine()) {
                frases.add(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarDatosCuriososRandom() {
        try (Scanner scanner = new Scanner(new File("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\archivosplanos\\datosCuriosos.txt"))){
            ArrayList<String> datosCuriosos = new ArrayList<>();
            while (scanner.hasNextLine()) {
                datosCuriosos.add(scanner.nextLine());
            }
            if (!datosCuriosos.isEmpty()) {
                int randomIndex = random.nextInt(datosCuriosos.size());
                String datoCurioso = "<html><body style='font-family: Arial, sans-serif; font-size: 20px'>" + datosCuriosos.get(randomIndex) + "</body></html>";
                editorPane.setText(datoCurioso);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VentanaConcientizacion::new);
    }
}
