package co.edu.poo2.ProtectoCazaUnac;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EscribirArchivo {
     static public void writeFile(String texto, String archivo) throws IOException {
         FileWriter fileWriter = new FileWriter(archivo);
         BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
         bufferedWriter.write(texto);
         bufferedWriter.close();
     }
}
