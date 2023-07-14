import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ventana2 {
    JPanel rootPanel;
    JButton backButton;
    private JButton nextButton;
    private JButton guardarButton;
    private JButton cargarButton;
    private JTextField marcaField;
    private JTextField añoField;
    private JTextField cilindrajeField;

    public ventana2() {
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String marca = marcaField.getText();
                int año = Integer.parseInt(añoField.getText());
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
                JFrame frame1 = (JFrame) SwingUtilities.getWindowAncestor(rootPanel);
                frame1.setVisible(false);

                JFrame frame2 = new JFrame("Ventana 1");
                ventana1 ventanaPrincipal = new ventana1();
                frame2.setContentPane(ventanaPrincipal.rootPanel);
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.pack();
                frame2.setVisible(true);
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = (JFrame) SwingUtilities.getWindowAncestor(rootPanel);
                frame1.setVisible(false);

                JFrame frame2 = new JFrame("Ventana 3");
                ventana3 terventana = new ventana3();
                frame2.setContentPane(terventana.rootPanel);
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.pack();
                frame2.setVisible(true);
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
                    añoField.setText(String.valueOf(año));
                    cilindrajeField.setText(String.valueOf(cilindraje));

                    System.out.println("Datos cargados correctamente desde el archivo.");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar los datos desde el archivo.", e);
        }
    }
}