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

    public static String calcular(String prompt) {
        // TODO 1. Obtener el operador
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
            throw new RuntimeException("No se encontro ningun operador en el prompt proporcionado");
        }

        // TODO 2. Separar el prompt en los operandos
        String[] operandos = prompt.split(operador.getOperador());

        // Deben existir dos operandos unicamente
        if (operandos.length != 2) {
            throw new RuntimeException("No se encontraron dos fracciones validas en el prompt");
        }

        // TODO 3. Convertir los operandos a fracciones manipulables
        Fraccion f1 = ConversorFracciones.toFraccion(operandos[0]);
        Fraccion f2 = ConversorFracciones.toFraccion(operandos[1]);

        // TODO 4. Calcular el resultado de la operacion
        Fraccion resultado = null;
        switch (operador) {
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

        // Retornar el resultado convertido a string
        return (ConversorFracciones.toString(resultado));
    }
}
