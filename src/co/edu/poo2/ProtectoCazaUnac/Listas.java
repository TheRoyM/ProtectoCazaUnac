package co.edu.poo2.ProtectoCazaUnac;

import java.util.ArrayList;

public class Listas {
    static ArrayList<Usuario> crearLista(String usuarios){
        ArrayList<Usuario> listaUsuarios= new ArrayList<>();
        String[] Usuario=usuarios.split(";");
        Usuario user;
        String[] atributos;
        for (int i = 0; i < Usuario.length; i++) {
            atributos = Usuario[i].split(",");
            user = new Usuario (atributos[0],atributos[1],atributos[2],atributos[3]);
            listaUsuarios.add(user);
        }
        return listaUsuarios;

    }

    static ArrayList<Animal> crearListaA(String animales){
        ArrayList<Animal> listaAnimales= new ArrayList<>();
        String[] Animales= animales.split(";");
        Animal a;
        String[] atributos;
        for (int i = 0; i < Animales.length; i++) {
            atributos = Animales[i].split(",");
            a = new Animal (atributos[0],atributos[1],atributos[2],atributos[3],atributos[4],atributos[5], atributos[6]);
            listaAnimales.add(a);

        }
        return listaAnimales;

    }
}
