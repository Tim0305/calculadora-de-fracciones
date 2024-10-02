package main;

import gui.CalculadoraFraccionesGUI;

public class Main {
    public static void main(String[] args) {

//        Scanner scanner = new Scanner(System.in);
//
//        /*
//        TODO
//
//        1. Obtener el prompt del usuario
//        2. Obtener el operador del prompt
//        3. Separar el prompt en los operandos
//        4. Convertir cada operando a una calculadorafracciones.fraccion
//        5. Calcular el resultado
//        6. Convertir el resultado a texto
//
//         */
//        // TODO 1. Obtener el prompt del usuario
//        String textoMenu = """
//                           *************************************
//                                 Calculadora de fracciones
//                           *************************************
//
//                           Ingrese el prompt para realizar operaciones con fracciones
//                           Formato: un medio mas dos tercios
//                           """;
//
//        System.out.print(textoMenu);
//        while (true)
//        {
//            System.out.println();
//            System.out.print("-> ");
//            String prompt = scanner.nextLine();
//
//            if (prompt.equalsIgnoreCase("q"))
//                break;
//            if (prompt.equalsIgnoreCase("help"))
//                printHelp();
//            else {
//                try {
//                    String resultado = CalculadoraFracciones.calcular(prompt);
//                    System.out.println(resultado);
//                }
//                catch (RuntimeException e) {
//                    System.out.println();
//                    System.out.println(e.getMessage());
//                }
//            }
//        }


        CalculadoraFraccionesGUI ventana = new CalculadoraFraccionesGUI();
        ventana.setVisible(true);
    }

    private static void printHelp()
    {
        String help = """
                
                ########################################################################################################
                        USO: Estre programa es una calculadora de fracciones que recibe fracciones a traves de texto
                        es decir, no se escribe mediante numeros sino por palabras.
                        
                        Operaciones:
                        * SUMA: "mas"
                        * RESTA: "menos"
                        * MULTIPLICACION: "por"
                        * DIVISION: "entre"
                        
                        Ejemplo:
                        1/2 -> un medio
                        10/1 -> un entero
                        
                        NOTA: El programa solo permite valores del 0 -> 99 en el numerador y denominador
                ########################################################################################################
                """;
        System.out.println(help);
    }
}
