/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package conversor;

/**
 *
 * @author Timoteo
 */
public enum Operador {
    SUMA("mas"),
    RESTA("menos"),
    MULTIPLICACION("por"),
    DIVISION("entre"),
    NULL("null");

    private String operador;

    Operador(String operador) {
        this.operador = operador;
    }

    public static Operador fromString(String text) {
        for (Operador operador : Operador.values()) {
            if (operador.operador.equalsIgnoreCase(text)) {
                return operador;
            }
        }
        throw new IllegalArgumentException("Ningun operador encontrado: " + text);
    }

    public String getOperador() {
        return operador;
    }
}
