package co.edu.poo2.ProtectoCazaUnac.interfazesGraficas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class VentanaConcientizacion extends JFrame {
    private ArrayList<String> frases = new ArrayList<>();
    private Random random = new Random();
    public VentanaConcientizacion() {
        this.setTitle("Concientización ProtectoUnacAnimal");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cargarFrases();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null); // Utilizamos un diseño nulo para posicionar los componentes manualmente
        mainPanel.setBackground(new Color(200, 200, 255));

        JLabel imagen = new JLabel();
        imagen.setIcon(new ImageIcon("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\img\\Imagen2.png"));
        imagen.setBounds(140, 0, 500, 500); // Ajusta la posición y el tamaño de la imagen

        JLabel etiqueta = new JLabel();
        etiqueta.setText("Los animales son nuestros compañeros, debemos cuidarlos.");
        etiqueta.setFont(new Font("Arial", Font.PLAIN, 20));
        etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
        etiqueta.setBounds(10, 450, 800, 50); // Ajusta la posición y el tamaño de la etiqueta

        JButton cambiarFraseBoton = new JButton("Cambiar frase");
        cambiarFraseBoton.setFont(new Font("Arial", Font.PLAIN, 20));
        cambiarFraseBoton.setBounds(300, 500, 200, 50);

        cambiarFraseBoton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Seleccionar una frase aleatoria de la lista de frases
                if (!frases.isEmpty()) {
                    int randomIndex = random.nextInt(frases.size());
                    etiqueta.setText(frases.get(randomIndex));
                }
            }
        });

        mainPanel.add(imagen);
        mainPanel.add(etiqueta);
        mainPanel.add(cambiarFraseBoton);

        this.add(mainPanel);
        setLocationRelativeTo(null);
        this.setVisible(true);
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


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaConcientizacion();
        });
    }
}
