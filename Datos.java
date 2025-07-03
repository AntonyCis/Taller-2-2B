package Datos;

// Antony Cisneros

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Datos extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JPanel panelHoja;
    private JCheckBox viajarCheckBox;
    private JCheckBox leerCheckBox;
    private JCheckBox deportesCheckBox;
    private JTextArea textArea1;
    private JComboBox comboBox1;
    private JButton guardarHojaDeVidaButton;
    private JTable table1;


    private void cargarTablaDatos(){
        String[] columnas = {"Titulo", "Institucion"};

        Object[][] datos = {
                {},
                {},
                {},
                {},
                {},
        };

        DefaultTableModel modelo = new DefaultTableModel(datos, columnas);

        table1.setModel(modelo);

    }

    private void guardarInformacionEnTXT(String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {

            writer.write("Nombre: " + textField1.getText());
            writer.newLine();
            writer.write("Apellido: " + textField2.getText());
            writer.newLine();
            writer.write("email: " + textField3.getText());
            writer.newLine();
            writer.write("Telefono: " + textField4.getText());
            writer.newLine();

            writer.write("Género: ");
            if (radioButton1.isSelected()) writer.write("Hombre");
            else if (radioButton2.isSelected()) writer.write("Mujer");
            else if (radioButton3.isSelected()) writer.write("Otros");
            else writer.write("Ninguno");
            writer.newLine();

            writer.write("Hobbies: ");
            if (viajarCheckBox.isSelected()) writer.write("Viajar ");
            if (leerCheckBox.isSelected()) writer.write("Leer ");
            if (deportesCheckBox.isSelected()) writer.write("Deportes ");
            writer.newLine();

            writer.write("Descripción: " + textArea1.getText());
            writer.newLine();

            writer.write("Nivel de estudios: " + comboBox1.getSelectedItem());
            writer.newLine();

            writer.write("Estudios realizados:");
            writer.newLine();
            DefaultTableModel modelo = (DefaultTableModel) table1.getModel();
            int filas = modelo.getRowCount();
            int columnas = modelo.getColumnCount();

            for (int col = 0; col < columnas; col++) {
                writer.write(modelo.getColumnName(col) + "\t");
            }
            writer.newLine();

            for (int fila = 0; fila < filas; fila++) {
                for (int col = 0; col < columnas; col++) {
                    Object valor = modelo.getValueAt(fila, col);
                    writer.write((valor != null ? valor.toString() : "") + "\t");
                }
                writer.newLine();
            }

            JOptionPane.showMessageDialog(this, "Datos guardados en " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar el archivo.");
        }
    }

    public Datos() {
        setTitle("");
        setSize(600, 540);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panelHoja);
        setLocationRelativeTo(null);
        cargarTablaDatos();

        ButtonGroup group = new ButtonGroup();
        group.add(radioButton1);
        group.add(radioButton2);
        group.add(radioButton3);

        guardarHojaDeVidaButton.addActionListener(e -> {
            guardarInformacionEnTXT("C:\\Users\\Usuario\\Documents\\POO\\Salidas txt\\hoja_de_vida.txt");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Datos().setVisible(true);
        });
    }
}
