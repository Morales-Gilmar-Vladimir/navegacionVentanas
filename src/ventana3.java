import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ventana3 {
    JPanel rootPanel;
    JButton backButton;
    private JButton nextButton;
    private JButton cargarButton;
    private JButton guardarButton;
    private JTextField añoField;
    private JTextField cilindrajeField;
    private JTextField marcaField;

    public ventana3() {
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
                JFrame frame3 = (JFrame) SwingUtilities.getWindowAncestor(rootPanel);
                frame3.setVisible(false);

                JFrame frame1 = new JFrame("Ventana 2");
                ventana2 ventana2 = new ventana2();
                frame1.setContentPane(ventana2.rootPanel);
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.pack();
                frame1.setVisible(true);
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame2 = (JFrame) SwingUtilities.getWindowAncestor(rootPanel);
                frame2.setVisible(false);

                JFrame frame5 = new JFrame("Ventana 4");
                ventana4 ventana4 = new ventana4();
                frame5.setContentPane(ventana4.rootPanel);
                frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame5.pack();
                frame5.setVisible(true);
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
