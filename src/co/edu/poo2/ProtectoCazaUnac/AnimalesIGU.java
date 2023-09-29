package co.edu.poo2.ProtectoCazaUnac;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AnimalesIGU extends JFrame implements ActionListener {
    // Declaración de componentes de la interfaz
    private JButton btnVerAnimales;
    private JButton btnAgregarAnimal;
    private JTable tablaAnimales;
    private DefaultTableModel modeloTabla;
    private ArrayList<Animal> listaAnimales;

    public AnimalesIGU() {
        // Inicialización de componentes
        btnVerAnimales = new JButton("Ver Animales");
        btnAgregarAnimal = new JButton("Agregar Animal");
        modeloTabla = new DefaultTableModel();
        tablaAnimales = new JTable(modeloTabla);
        listaAnimales = new ArrayList<>();

        // Configuración de la tabla
        tablaAnimales.setLocation(10,70);
        modeloTabla.addColumn("Nombre cientifico");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Conservación");
        modeloTabla.addColumn("Rol Ecologico");
        modeloTabla.addColumn("Habitad");
        modeloTabla.addColumn("Descripsion del animal avistado");
        modeloTabla.addColumn("Lugar de avistamiento");

        add(tablaAnimales);

        // Configuración del diseño de la ventana
        setTitle("Gestión de Animales");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Agregar componentes a la ventana
        add(btnVerAnimales);
        add(btnAgregarAnimal);
        add(new JScrollPane(tablaAnimales));

        // Agregar listeners a los botones
        btnVerAnimales.addActionListener(this);
        btnAgregarAnimal.addActionListener(this);

        setVisible(true);
        setLayout(null);
        setSize(500,500);
        setTitle("Animales en ProtectoCazaUnac ");

        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setLocation(500,250);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVerAnimales) {
            // Cargar y mostrar la lista de animales desde el archivo
            cargarAnimalesDesdeArchivo();
            mostrarAnimalesEnTabla();
        } else if (e.getSource() == btnAgregarAnimal) {
            // Abrir una ventana para agregar un nuevo animal
        }
    }

    private void cargarAnimalesDesdeArchivo() {
        listaAnimales.clear(); // Limpiar la lista antes de cargar nuevos datos
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\animales.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 7) {
                    Animal animal = getAnimal(partes);
                    listaAnimales.add(animal);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static Animal getAnimal(String[] partes) {
        String nombreCientifico = partes[0].trim();
        String nombre = partes[1].trim();
        String conservacion = partes[2].trim();
        String rolEcologico = partes[3].trim();
        String habitad = partes[4].trim();
        String descripsion = partes[5].trim();
        String lugaravistamiento = partes[6].trim();
        Animal animal = new Animal(nombreCientifico, nombre,conservacion, rolEcologico,habitad,descripsion,lugaravistamiento);
        return animal;
    }

    private void mostrarAnimalesEnTabla() {
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos
        for (Animal animal : listaAnimales) {
            Object[] fila = {animal.getNombreCientifico(), animal.getNombre(), animal.getConservacion(), animal.getRolEcologico(),animal.getHabitad()+
            animal.getDescripsionAnimal(), animal.getLugarAvistamiento()};
            modeloTabla.addRow(fila);
        }
    }
    
}