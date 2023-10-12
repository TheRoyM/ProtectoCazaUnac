package co.edu.poo2.ProtectoCazaUnac;

import java.util.ArrayList;

public class Listas {
    public static ArrayList<Usuario> crearUsuarios(String usuarios){
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();

        if (usuarios != null) {
            String[] usuariosSeparados = usuarios.split(";");
            for (String usuarioInfo : usuariosSeparados) {
                String[] atributos = usuarioInfo.split(",");
                if (atributos.length == 4) {
                    String nombreCompleto = atributos[0];
                    String correoElectronico = atributos[1];
                    String userName = atributos[2];
                    String password = atributos[3];
                    Usuario user = new Usuario(nombreCompleto, correoElectronico, userName, password);
                    listaUsuarios.add(user);
                } else {
                    // Manejo del error o registro de información de registro incorrecto
                    System.err.println("Error: Los datos del usuario no están completos en la entrada: " + usuarioInfo);
                }
            }
        } else {
            // Manejo del caso en que 'usuarios' es null
            System.err.println("Error: El contenido del archivo de usuarios es null.");
        }

        return listaUsuarios;
    }

    public static ArrayList<Animal> crearAnimales(String animales) {
        ArrayList<Animal> listaAnimales = new ArrayList<>();
        String[] Animales = animales.split(";");
        Animal a;
        String[] atributos;
        for (int i = 0; i < Animales.length; i++) {
            atributos = Animales[i].split(",");
            a = new Animal(atributos[0], atributos[1], atributos[2], atributos[3], atributos[4], atributos[5], atributos[6]);
            listaAnimales.add(a);

        }
        return listaAnimales;

    }
}
