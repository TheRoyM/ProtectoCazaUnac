package co.edu.poo2.ProtectoCazaUnac;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeerArchivo {
    public static String readFile (String file) throws IOException {

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String linea = br.readLine();
        return linea;
    }
}
