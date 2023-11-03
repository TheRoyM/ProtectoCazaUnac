package co.edu.poo2.ProtectoCazaUnac.interfazesGraficas;

import co.edu.poo2.ProtectoCazaUnac.Animal;
import co.edu.poo2.ProtectoCazaUnac.LeerArchivo;
import co.edu.poo2.ProtectoCazaUnac.Listas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class AnimalesIGU extends JFrame implements ActionListener {

    static JButton btnVerAnimales, btnAgregarAnimal, btnEliminarAnimal,btnEditar, btnConcientizar;
    static JTable tablaAnimales;
    static DefaultTableModel modeloTabla;
    static ArrayList<Animal> listaAnimales = new ArrayList<>();
    static JLabel lblTabla;

    public AnimalesIGU() throws IOException {
        lblTabla = new JLabel(new ImageIcon("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\img\\listado.png"));
        lblTabla.setBounds(-50,-60,300,300);

        getContentPane().setBackground(new Color(200, 200, 255));
        listaAnimales = new ArrayList<>();

        btnVerAnimales = new JButton("Ver Animales");
        ImageIcon iconoVer = new ImageIcon("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\img\\ver.png");
        btnVerAnimales.setIcon(iconoVer);
        btnVerAnimales.setBounds(20, 180, 160, 30);
        btnVerAnimales.addActionListener(this);

        btnAgregarAnimal = new JButton("Agregar Animal");
        ImageIcon iconoAgregar = new ImageIcon("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\img\\agregar.png");
        btnAgregarAnimal.setIcon(iconoAgregar);
        btnAgregarAnimal.setBounds(20, 220, 160, 30);
        btnAgregarAnimal.addActionListener(this);

        btnEditar = new JButton("Editar Animal");
        ImageIcon iconoEditar = new ImageIcon("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\img\\editar.png");
        btnEditar.setIcon(iconoEditar);
        btnEditar.setBounds(20, 260, 160, 30);
        btnEditar.addActionListener(this);

        btnEliminarAnimal = new JButton("Eliminar Animal");
        ImageIcon iconoEliminar = new ImageIcon("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\img\\eliminar.png");
        btnEliminarAnimal.setIcon(iconoEliminar);
        btnEliminarAnimal.setBounds(20, 300, 160, 30);
        btnEliminarAnimal.addActionListener(this);

        btnConcientizar = new JButton("Concientización");
        ImageIcon iconoConcientizar = new ImageIcon("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\img\\eco.png");
        btnConcientizar.setIcon(iconoConcientizar);
        btnConcientizar.setBounds(20, 340, 160, 30);
        btnConcientizar.addActionListener(this);

        // Configuración de la tabla
        modeloTabla = new DefaultTableModel();
        tablaAnimales = new JTable(modeloTabla);

        // Agregar la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(tablaAnimales);
        scrollPane.setBackground(new Color(200, 200, 255));
        scrollPane.setBounds(220, 20, 1080, 510);
        // Agregar columnas a la tabla
        modeloTabla.addColumn("Nombre cientifico");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Conservación");
        modeloTabla.addColumn("Rol Ecologico");
        modeloTabla.addColumn("Habitat");
        modeloTabla.addColumn("Breve Descripción");
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
        add(btnEditar);
        add(lblTabla);
        add(lblInformacion);
        add(scrollPane);


        setLayout(null);
        setTitle("Gestión de Animales ProtectoUnacAnimal");
        setSize(1360, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(200, 200, 255));
        this.setResizable(false);
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
        } else if (e.getSource() == btnConcientizar) {
            // Ocultar ventana actual
            this.setVisible(false);
            // Crear y mostrar la ventana de registro de usuario
            VentanaConcientizacion concientizacion = null;
            concientizacion = new VentanaConcientizacion();
            concientizacion.setVisible(true);
        }else if(e.getSource() == btnEditar){
            editarAnimal();
        }
    }

    private void cargarAnimalesDesdeArchivo() {
        listaAnimales.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\RoyMR\\Documents\\POO2-2023\\ProtectoCazaUnac\\src\\co\\edu\\poo2\\ProtectoCazaUnac\\archivosplanos\\animales.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                //System.out.println("Leyendo línea: " + linea);
                String[] partes = linea.split(",");
                if (partes.length == 7) {
                    Animal animal = getAnimal(partes);
                    listaAnimales.add(animal);
                } /*else {
                    System.err.println("Línea no válida: " + linea);
                }*/
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
    private void editarAnimal() {
        int filaSeleccionada = tablaAnimales.getSelectedRow();
        if (filaSeleccionada != -1) {
            int indiceAnimal = tablaAnimales.convertRowIndexToModel(filaSeleccionada);
            Animal animalSeleccionado = listaAnimales.get(indiceAnimal);

            JTextField txtNombreCientifico = new JTextField(animalSeleccionado.getNombreCientifico());
            JTextField txtNombre = new JTextField(animalSeleccionado.getNombre());
            JTextField txtConservacion = new JTextField(animalSeleccionado.getConservacion());
            JTextField txtRolEcologico = new JTextField(animalSeleccionado.getRolEcologico());
            JTextField txtHabitat = new JTextField(animalSeleccionado.getHabitad());
            JTextField txtDescripcion = new JTextField(animalSeleccionado.getDescripsionAnimal());
            JTextField txtLugarAvistamiento = new JTextField(animalSeleccionado.getLugarAvistamiento());

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

            int result = JOptionPane.showConfirmDialog(null, panel, "Editar Animal", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                // Actualiza la información del animal con los nuevos valores
                animalSeleccionado.setNombreCientifico(txtNombreCientifico.getText());
                animalSeleccionado.setNombre(txtNombre.getText());
                animalSeleccionado.setConservacion(txtConservacion.getText());
                animalSeleccionado.setRolEcologico(txtRolEcologico.getText());
                animalSeleccionado.setHabitad(txtHabitat.getText());
                animalSeleccionado.setDescripsionAnimal(txtDescripcion.getText());
                animalSeleccionado.setLugarAvistamiento(txtLugarAvistamiento.getText());

                // Guarda los cambios en el archivo
                guardarAnimalesEnArchivo();

                // Actualiza la tabla
                mostrarAnimalesEnTabla();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un animal para editar.");
        }
    }
}