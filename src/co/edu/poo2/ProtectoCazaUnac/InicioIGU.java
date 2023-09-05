package co.edu.poo2.ProtectoCazaUnac;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioIGU {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Seleccione el registro que desea realizar");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout());

            JButton buttonRegistro = new JButton("Registro Usuario");
            JButton buttonUsuario = new JButton("Inicio ");

            buttonUsuario.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openWindow(new InicioUsuario());
                }
            });

            buttonRegistro.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openWindow(new RegistroUsuario());
                }
            });


            frame.add(buttonUsuario);
            frame.add(buttonRegistro);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static void openWindow(JFrame window) {
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(500, 500);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}