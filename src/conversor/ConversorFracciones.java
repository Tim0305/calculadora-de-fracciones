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
        if (componentes[0].equalsIgnoreCase("uno")) {
            throw new Error("El numerador 'uno' no es un numerador valido");
        } else if (componentes[0].equalsIgnoreCase("un")) {
            denominadorString = buildStringFromIndex(componentes, 1);
            return 1;
        } else if (DiccionarioNumeros.existsDigito(componentes[0])) {
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

            if (digito.equalsIgnoreCase("uno")) {
                throw new RuntimeException("El numerador 'veintiuno' no es un numerador valido");
            } else if (digito.equalsIgnoreCase("un")) {
                numerador++;
            } else if (DiccionarioNumeros.existsDigito(digito)) {
                numerador += DiccionarioNumeros.getDigito(digito);
            }

            denominadorString = buildStringFromIndex(componentes, 1);
            return numerador;
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
                } else {
                    denominadorString = buildStringFromIndex(componentes, 1);
                }
            }

            return numerador;
        }

        throw new RuntimeException("El numerador no es valido'");
    }

    private static int getDenominador(int numerador, String denominadorString) {
        String[] componentes = denominadorString.split(" ");
        String terminacion = "";
        // Plural o Singular
        if (numerador != 1)
            terminacion = "s";

        // Es entero?
        if (componentes[0].equalsIgnoreCase("entero" + terminacion)) {
            if (componentes.length > 1) {
                throw new RuntimeException("El denominador: " + denominadorString + " no es un denominador valido");
            }
            return 1;
        }
        // Es unavo?
        else if (componentes[0].equalsIgnoreCase("unavo" + terminacion) {
            throw new RuntimeException("El denominador 'unavo" + terminacion + "' no es un denominador valido");
        }
        // Es otro digito?
        if (DiccionarioDenominador.existsDigito(componentes[0] + terminacion)) {
            if (componentes.length > 1) {
                throw new RuntimeException("El denominador: " + denominadorString + " no es un denominador valido");
            }
            return DiccionarioDenominador.getDigito(componentes[0] + terminacion);
        }

        // Es especial?

        if (DiccionarioDenominador.existsEspecial(componentes[0] + terminacion)) {
            if (componentes.length > 1) {
                throw new RuntimeException("El denominador: " + denominadorString + " no es un denominador valido");
            }
            return DiccionarioDenominador.getEspecial(componentes[0] + terminacion);
        }

        // Decimales


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
