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
        System.out.println();
        System.out.print("-> ");
        String prompt = scanner.nextLine();

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
        }

        // TODO 3. Separar el prompt en los operandos
        String[] operandos = prompt.split(operador.getOperador());

        // Deben existir dos operandos unicamente
        if (operandos.length != 2) {
            System.out.println("No se encontraron dos fracciones validas en el prompt");
            System.out.println();
        } else {
            for (String operando : operandos) {
                ConversorFracciones.toFraccion(operando);
            }
        }
    }
}
