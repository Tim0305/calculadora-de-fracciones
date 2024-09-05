/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fraccion;

/**
 *
 * @author Timoteo
 */
public class Fraccion {

    public int numerador;
    public int denominador;

    public Fraccion(int numerador, int denominador) {
        this.numerador = numerador;
        if (denominador == 0) {
            throw new ErrorFraccionImposible("No se puede crear una fraccion con denumerador 0");
        }
        this.denominador = denominador;
    }

    public Fraccion sumarFraccion(Fraccion f2) {
        int num;
        int den;

        if (denominador == f2.denominador) {
            num = numerador + f2.numerador;
            den = denominador;
        } else {
            den = denominador * f2.denominador;
            num = (numerador * f2.denominador) + (f2.numerador * denominador);
        }

        return new Fraccion(num, den);
    }

    public Fraccion restarFraccion(Fraccion f2) {
        int num;
        int den;

        if (denominador == f2.denominador) {
            num = numerador - f2.numerador;
            den = denominador;
        } else {
            num = (numerador * f2.denominador) - (f2.numerador * denominador);
            den = denominador * f2.denominador;
        }

        return new Fraccion(num, den);
    }

    public Fraccion multiplicarFraccion(Fraccion f2) {
        return new Fraccion((numerador * f2.numerador), (denominador * f2.denominador));
    }

    public Fraccion dividirFraccion(Fraccion f2) {
        return new Fraccion((numerador * f2.denominador), (f2.numerador * denominador));
    }

    @Override
    public String toString() {
        if (numerador != 0) {
            return numerador + "/" + denominador;
        } else {
            return "0";
        }
    }
}
