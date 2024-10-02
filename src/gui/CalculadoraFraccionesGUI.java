package gui;

import calculadorafracciones.CalculadoraFracciones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CalculadoraFraccionesGUI extends JFrame {
    private JTextArea inputTextArea;
    private JButton cmdCalcularOperacion;
    private JTextArea resultadoTextArea;
    private JFrame ventanaPrincipal;
    private int width;
    private int height;

    public CalculadoraFraccionesGUI() {
        super();

        // Inicializacion de variables
        ventanaPrincipal = null;
        inputTextArea = new JTextArea();
        cmdCalcularOperacion = new JButton("Calcular Operacion");
        resultadoTextArea = new JTextArea();

        width = 500;
        height = width;

        /* Configuraciones */
        init();
    }

    public CalculadoraFraccionesGUI(JFrame ventanaPrincipal) {
        this();
        this.ventanaPrincipal = ventanaPrincipal;
    }

    private void init() {
        /* Ventana */
        setTitle("Calculadora de Fracciones");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null); // Centrar la ventana
        setLayout(null); // Vamos a acomodar las posiciones manualmente

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (ventanaPrincipal != null) {
                    setVisible(false);
                    ventanaPrincipal.setVisible(true);
                }
                else
                    System.exit(0);
            }
        });

        // Titulo
        JLabel titulo = new JLabel("Calculadora de Fracciones", SwingConstants.CENTER);
        titulo.setBounds(0, 20, width, 30);
        add(titulo);

        // Input Text
        inputTextArea.setBounds(50,80,400,50);
        add(inputTextArea);

        // Boton Calcular
        cmdCalcularOperacion.setBounds(150, 150, 200, 30);
        cmdCalcularOperacion.addActionListener(e -> onClickCalcularOperacionFraccion());
        cmdCalcularOperacion.setBackground(new Color(0, 150, 0));
        cmdCalcularOperacion.setForeground(Color.white); // Color de la fuente
        cmdCalcularOperacion.setFocusable(false);
        add(cmdCalcularOperacion);

        // Resultado Text
        JScrollPane scrollPaneResultado = new JScrollPane(resultadoTextArea);
        scrollPaneResultado.setBounds(50,200,400,50);
        add(scrollPaneResultado);
    }

    private void onClickCalcularOperacionFraccion() {
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
