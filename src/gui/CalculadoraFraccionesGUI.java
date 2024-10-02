package gui;

import calculadorafracciones.CalculadoraFracciones;

import javax.swing.*;
import java.awt.*;

public class CalculadoraFraccionesGUI extends JFrame {
    private JLabel titulo = new JLabel("Calculadora de Fracciones", SwingConstants.CENTER);
    private JTextArea inputTextArea = new JTextArea();
    private JButton cmdCalcularOperacion = new JButton("Calcular Operacion");
    private JTextArea resultadoTextArea = new JTextArea();

    public CalculadoraFraccionesGUI() {
        super();

        /* Inicializacion de variables */
        /* Configuraciones */
        init();
    }

    private void init() {
        /* Ventana */
        setTitle("Calculadora de Fracciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null); // Centrar la ventana
        setLayout(null); // Vamos a acomodar las posiciones manualmente

        // Titulo
        titulo.setBounds(150, 10, 200, 30);
        add(titulo);

        // Input Text
        inputTextArea.setBounds(50,80,400,50);
        add(inputTextArea);

        // Boton Calcular
        cmdCalcularOperacion.setBounds(150, 150, 200, 30);
        cmdCalcularOperacion.addActionListener(e -> calcularOperacionFraccion());
        cmdCalcularOperacion.setBackground(new Color(0, 150, 0));
        cmdCalcularOperacion.setForeground(Color.white); // Color de la fuente
        add(cmdCalcularOperacion);

        // Resultado Text
        JScrollPane scrollPaneResultado = new JScrollPane(resultadoTextArea);
        scrollPaneResultado.setBounds(50,200,400,50);
        add(scrollPaneResultado);
    }

    private void calcularOperacionFraccion() {
        String prompt = inputTextArea.getText();
        String resultado = "";
        try {
            resultado = CalculadoraFracciones.calcular(prompt);
        }
        catch (RuntimeException e) {
            resultado = e.getMessage();
        }
        resultadoTextArea.setText(resultado);
    }
}
