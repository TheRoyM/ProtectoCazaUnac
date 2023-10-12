package co.edu.poo2.ProtectoCazaUnac.interfazesGraficas;

import co.edu.poo2.ProtectoCazaUnac.Animal;
import co.edu.poo2.ProtectoCazaUnac.LeerArchivo;
import co.edu.poo2.ProtectoCazaUnac.Listas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class AnimalesIGU extends JFrame implements ActionListener {

    static JButton btnVerAnimales, btnAgregarAnimal, btnEliminarAnimal, btnConcientizar;
    static JTable tablaAnimales;
    static DefaultTableModel modeloTabla;
    static ArrayList<Animal> listaAnimales = new ArrayList<>();

    public AnimalesIGU() throws IOException {
        listaAnimales = new ArrayList<>();

        btnVerAnimales = new JButton("Ver Animales");
        btnVerAnimales.setBounds(20, 20, 120, 30);
        btnVerAnimales.addActionListener(this);

        btnAgregarAnimal = new JButton("Agregar Animal");
        btnAgregarAnimal.setBounds(20, 60, 120, 30);
        btnAgregarAnimal.addActionListener(this);

        btnEliminarAnimal = new JButton("Eliminar Animal");
        btnEliminarAnimal.setBounds(20, 100, 120, 30);
        btnEliminarAnimal.addActionListener(this);

        btnConcientizar = new JButton("Concientización");
        btnConcientizar.setBounds(20, 140, 120, 30);
        btnConcientizar.addActionListener(this);

        // Configuración de la tabla
        modeloTabla = new DefaultTableModel();
        tablaAnimales = new JTable(modeloTabla);

        // Agregar la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(tablaAnimales);
        scrollPane.setBounds(200, 20, 990, 550);
        // Agregar columnas a la tabla
        modeloTabla.addColumn("Nombre cientifico");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Conservación");
        modeloTabla.addColumn("Rol Ecologico");
        modeloTabla.addColumn("Habitat");
        modeloTabla.addColumn("Descripción del animal avistado");
        modeloTabla.addColumn("Lugar de avistamiento");


        // Leer archivo plano
        String infoArchivo = LeerArchivo.readFile("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\archivosplanos\\animales.txt");
        JLabel lblInformacion = new JLabel("Archivo cargado");
      lblInformacion.setBounds(20,700,100,30);

        // Crear lista de usuarios
        listaAnimales = Listas.crearAnimales(infoArchivo);
        lblInformacion.setText(lblInformacion.getText() + " :: Lista creada");
        lblInformacion.setVisible(false);


        add(btnAgregarAnimal);
        add(btnVerAnimales);
        add(btnEliminarAnimal);
        add(btnConcientizar);
        add(lblInformacion);
        add(scrollPane);
        setLayout(null);
        setTitle("Gestión de Animales ProtectoUnacAnimal");
        setSize(1300, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setVisible(true);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVerAnimales) {
            cargarAnimalesDesdeArchivo();
            mostrarAnimalesEnTabla();
        } else if (e.getSource() == btnAgregarAnimal) {
            agregarAnimal();
        } else if (e.getSource() == btnEliminarAnimal) {
            eliminarAnimal();
        }else if (e.getSource() == btnConcientizar){
            // Ocultar ventana actual
            this.setVisible(false);
            // Crear y mostrar la ventana de registro de usuario
            VentanaConcientizacion registroVentana = null;
            registroVentana = new VentanaConcientizacion();
            registroVentana.setVisible(true);
        }
    }

    private void cargarAnimalesDesdeArchivo() {
        listaAnimales.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\archivosplanos\\animales.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println("Leyendo línea: " + linea);
                String[] partes = linea.split(",");
                if (partes.length == 7) {
                    Animal animal = getAnimal(partes);
                    listaAnimales.add(animal);
                } else {
                    System.err.println("Línea no válida: " + linea);
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
        String habitat = partes[4].trim();
        String descripcion = partes[5].trim();
        String lugarAvistamiento = partes[6].trim();
        Animal animal = new Animal(nombreCientifico, nombre, conservacion, rolEcologico, habitat, descripcion, lugarAvistamiento);
        return animal;
    }

    private void mostrarAnimalesEnTabla() {
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos
        for (Animal animal : listaAnimales) {
            Object[] fila = {
                    animal.getNombreCientifico(),
                    animal.getNombre(),
                    animal.getConservacion(),
                    animal.getRolEcologico(),
                    animal.getHabitad(),
                    animal.getDescripsionAnimal(),
                    animal.getLugarAvistamiento()
            };
            modeloTabla.addRow(fila);
        }
        // Asegúrate de que la tabla se repinte
        modeloTabla.fireTableDataChanged();
    }

    private void agregarAnimal() {
        JTextField txtNombreCientifico = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtConservacion = new JTextField();
        JTextField txtRolEcologico = new JTextField();
        JTextField txtHabitat = new JTextField();
        JTextField txtDescripcion = new JTextField();
        JTextField txtLugarAvistamiento = new JTextField();

        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.add(new JLabel("Nombre Científico:"));
        panel.add(txtNombreCientifico);
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Conservación:"));
        panel.add(txtConservacion);
        panel.add(new JLabel("Rol Ecológico:"));
        panel.add(txtRolEcologico);
        panel.add(new JLabel("Habitat:"));
        panel.add(txtHabitat);
        panel.add(new JLabel("Descripción:"));
        panel.add(txtDescripcion);
        panel.add(new JLabel("Lugar de Avistamiento:"));
        panel.add(txtLugarAvistamiento);

        int result = JOptionPane.showConfirmDialog(null, panel, "Agregar Animal", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String nombreCientifico = txtNombreCientifico.getText();
            String nombre = txtNombre.getText();
            String conservacion = txtConservacion.getText();
            String rolEcologico = txtRolEcologico.getText();
            String habitat = txtHabitat.getText();
            String descripcion = txtDescripcion.getText();
            String lugarAvistamiento = txtLugarAvistamiento.getText();

            Animal nuevoAnimal = new Animal(nombreCientifico, nombre, conservacion, rolEcologico, habitat,
                    descripcion, lugarAvistamiento);

            listaAnimales.add(nuevoAnimal);

            guardarAnimalesEnArchivo();

            mostrarAnimalesEnTabla();
        }
    }

    private void eliminarAnimal() {
        int filaSeleccionada = tablaAnimales.getSelectedRow();
        if (filaSeleccionada != -1) {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar este animal?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                int indiceAnimal = tablaAnimales.convertRowIndexToModel(filaSeleccionada);
                listaAnimales.remove(indiceAnimal);
                guardarAnimalesEnArchivo();
                mostrarAnimalesEnTabla();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un animal para eliminar.");
        }
    }

    private void guardarAnimalesEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\archivosplanos\\animales.txt"))) {
            for (Animal animal : listaAnimales) {
                String linea = String.format("%s,%s,%s,%s,%s,%s,%s",animal.getNombreCientifico(), animal.getNombre(),
                        animal.getConservacion(), animal.getRolEcologico(), animal.getHabitad(),
                        animal.getDescripsionAnimal(), animal.getLugarAvistamiento());
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}