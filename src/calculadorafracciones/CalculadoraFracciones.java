package calculadorafracciones;

import conversor.ConversorFracciones;
import fraccion.Fraccion;
import java.util.Scanner;
import conversor.Operador;

/**
 *
 * @author Timoteo
 */
public class CalculadoraFracciones {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        /*
        TODO

        1. Obtener el prompt del usuario
        2. Obtener el operador del prompt
        3. Separar el prompt en los operandos
        4. Convertir cada operando a una fraccion
        5. Calcular el resultado
        6. Convertir el resultado a texto

         */
        // TODO 1. Obtener el prompt del usuario
        String textoMenu = """
                           *************************************
                                 Calculadora de fracciones
                           *************************************

                           Ingrese el prompt para realizar operaciones con fracciones
                           Formato: un medio mas dos tercios
                           """;

        System.out.println(textoMenu);
        while (true)
        {
            System.out.println();
            System.out.print("-> ");
            String prompt = scanner.nextLine();

            if (prompt.equalsIgnoreCase("help"))
            {
                printHelp();
            }
            else {
                // TODO 2. Obtener el operador del prompt
                Operador operador = Operador.NULL;
                if (prompt.contains(Operador.SUMA.getOperador())) {
                    operador = Operador.SUMA;
                } else if (prompt.contains(Operador.RESTA.getOperador())) {
                    operador = Operador.RESTA;
                } else if (prompt.contains(Operador.MULTIPLICACION.getOperador())) {
                    operador = Operador.MULTIPLICACION;
                } else if (prompt.contains(Operador.DIVISION.getOperador())) {
                    operador = Operador.DIVISION;
                } else {
                    System.out.println("No se encontro ningun operador en el prompt proporcionado");
                    System.out.println();
                    break;
                }

                // TODO 3. Separar el prompt en los operandos
                String[] operandos = prompt.split(operador.getOperador());

                // Deben existir dos operandos unicamente
                if (operandos.length != 2) {
                    System.out.println("No se encontraron dos fracciones validas en el prompt");
                    System.out.println();
                    break;
                } else {
                    try {
                        Fraccion f1 = ConversorFracciones.toFraccion(operandos[0]);
                        Fraccion f2 = ConversorFracciones.toFraccion(operandos[1]);
                        Fraccion resultado = null;
                        switch (operador){
                            case SUMA:
                                resultado = f1.sumarFraccion(f2);
                                break;
                            case RESTA:
                                resultado = f1.restarFraccion(f2);
                                break;
                            case DIVISION:
                                resultado = f1.dividirFraccion(f2);
                                break;
                            case MULTIPLICACION:
                                resultado = f1.multiplicarFraccion(f2);
                                break;
                        }
                        System.out.println(ConversorFracciones.toString(resultado));
                    } catch (RuntimeException e) {
                        System.out.println(e.toString());
                    }
                }
            }
        }
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
