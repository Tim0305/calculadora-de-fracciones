package conversor;

import fraccion.Fraccion;

public class ConversorFracciones {

    private static String denominadorString;

    public static void toFraccion(String fraccionString) {
        // Numerador
        int numerador = getNumerador(fraccionString.trim());
        System.out.println("Numerador: " + numerador);
        System.out.println(denominadorString);
    }

    /*
    Metodo para obtener el numerador en entero y al mismo tiempo el denominador en string
    Cuando se ejecuta correctamente el metodo el denominador se almacena en la variable denominadorString
     */
    private static int getNumerador(String fraccion) {

        String[] componentes = fraccion.split(" ");

        // Digito
        if (DiccionarioNumeros.existsDigito(componentes[0])) {
            denominadorString = buildStringFromIndex(componentes, 1);
            return DiccionarioNumeros.getDigito(componentes[0]);
        }

        // Especiales
        if (DiccionarioNumeros.existsEspecial(componentes[0])) {
            denominadorString = buildStringFromIndex(componentes, 1);
            return DiccionarioNumeros.getEspecial(componentes[0]);
        }

        // Veinti
        if (componentes[0].toLowerCase().contains("veinti")) {
            int numerador = 20;

            String digito = componentes[0].replace("veinti", "");

            if (DiccionarioNumeros.existsDigito(digito)) {
                numerador += DiccionarioNumeros.getDigito(digito);
                denominadorString = buildStringFromIndex(componentes, 1);
                return numerador;
            }
        }

        // Decenas
        if (DiccionarioNumeros.existsDecena(componentes[0])) {
            int numerador = DiccionarioNumeros.getDecena(componentes[0]);

            // si hay mas de dos componentes puedo acceder a la proxima pos
            if (componentes.length > 1) {
                if (componentes[1].equalsIgnoreCase("y")) {
                    if (DiccionarioNumeros.existsDigito(componentes[2])) {
                        numerador += DiccionarioNumeros.getDigito(componentes[2]);
                        denominadorString = buildStringFromIndex(componentes, 3);
                    }
                    else
                    {
                        StringBuilder sb = new StringBuilder();
                        sb.append("El numerador <");
                        sb.append(componentes[0]);
                        sb.append(" ");
                        sb.append(componentes[1]);
                        sb.append(" ");
                        sb.append(componentes[2]);
                        sb.append("> no es valido");
                        throw new RuntimeException(sb.toString());
                    }
                } else {
                    denominadorString = buildStringFromIndex(componentes, 1);
                }
            }
            return numerador;
        }
        throw new RuntimeException("El numerador " + componentes[0] + " no es valido'");
    }

    private static int getDenominador(int numerador, String denominadorString)
    {
        /*
        Si numerador es igual a 1 entonces se agrega una 's' para obtener el valor del numero
        Si no es igual, entonces no se agrega nada.
        Ejemplo
        un tercios
        tercioss -> esto no se encuentra por lo que da un error
        un tercio
        tercios -> esto si se encuentra en el diccionario
         */
        String terminacion = numerador == 1 ? "s" : "";

        String [] componentes = denominadorString.split(" ");

        return 0;
    }

    /*
    Este metodo recibe un arreglo de strings y un indice a partir del cual se va a construir un nuevo string
    Ejemplo: si el indice es 1 se construira un string a partir del indice 1 del arreglo de strings hasta el ultimo elemento del array
     */

    private static String buildStringFromIndex(String[] arrayStrings, int index) {

        StringBuilder completeString = new StringBuilder();

        for (int i = index, l = arrayStrings.length; i < l; i++) {
            completeString.append(arrayStrings[i]);
            completeString.append(" ");
        }

        return completeString.toString();
    }
}
