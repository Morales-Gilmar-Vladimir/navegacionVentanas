import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ventana1 {
    JPanel rootPanel;
    private JButton guardarButton;
    private JButton cargarButton;
    private JTextField marcaField;
    private JTextField anioField;
    private JTextField cilindrajeField;
    private JButton backButton;

    public ventana1() {
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String marca = marcaField.getText();
                int año = Integer.parseInt(anioField.getText());
                int cilindraje = Integer.parseInt(cilindrajeField.getText());

                guardarDatos(marca, año, cilindraje);
            }
        });

        cargarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatos();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame2 = (JFrame) SwingUtilities.getWindowAncestor(rootPanel);
                frame2.setVisible(false);

                JFrame frame1 = new JFrame("Ventana 2");
                ventana2 segventana = new ventana2();
                frame1.setContentPane(segventana.rootPanel);
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.pack();
                frame1.setVisible(true);
            }
        });
    }

    private void guardarDatos(String marca, int año, int cilindraje) {
        String filePath = "datos.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String datos = marca + "," + año + "," + cilindraje + "\n";
            writer.write(datos);
            System.out.println("Datos guardados correctamente en el archivo.");
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar los datos en el archivo.", e);
        }
    }

    private void cargarDatos() {
        String filePath = "datos.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    String marca = datos[0];
                    int año = Integer.parseInt(datos[1]);
                    int cilindraje = Integer.parseInt(datos[2]);

                    marcaField.setText(marca);
                    anioField.setText(String.valueOf(año));
                    cilindrajeField.setText(String.valueOf(cilindraje));

                    System.out.println("Datos cargados correctamente desde el archivo.");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar los datos desde el archivo.", e);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana1");
        frame.setContentPane(new ventana1().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}