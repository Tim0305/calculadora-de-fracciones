package conversor;

public class ConversorFracciones {

    private static String denominadorString;

    public static void toFraccion(String fraccionString) {
        // Numerador
        int numerador = getNumerador(fraccionString.trim());
        int denominador = getDenominador(numerador, denominadorString);
        System.out.println("Numerador: " + numerador);
        System.out.println("Denominador: " + denominador);
    }

    /*
    Metodo para obtener el numerador en entero y al mismo tiempo el denominador en string
    Cuando se ejecuta correctamente el metodo el denominador se almacena en la variable denominadorString
     */
    private static int getNumerador(String fraccion) {

        String[] componentes = fraccion.split(" ");
        // Obtenemos el denominador por la ultima posicion porque el denominador siempre es una unica palabra
        denominadorString = componentes[componentes.length - 1];

        // Digito
        if (DiccionarioNumerador.existsDigito(componentes[0])) {
            return DiccionarioNumerador.getDigito(componentes[0]);
        }

        // Especiales
        if (DiccionarioNumerador.existsEspecial(componentes[0])) {
            return DiccionarioNumerador.getEspecial(componentes[0]);
        }

        // Veinti
        if (componentes[0].toLowerCase().contains("veinti")) {
            int numerador = 20;

            String digito = componentes[0].replace("veinti", "");

            if (DiccionarioNumerador.existsDigito(digito)) {
                numerador += DiccionarioNumerador.getDigito(digito);
                return numerador;
            }
        }

        // Decenas
        if (DiccionarioNumerador.existsDecena(componentes[0])) {
            int numerador = DiccionarioNumerador.getDecena(componentes[0]);

            // si hay mas de dos componentes puedo acceder a la proxima pos
            if (componentes.length > 1) {
                if (componentes[1].equalsIgnoreCase("y")) {
                    if(!componentes[2].equalsIgnoreCase("cero"))
                        if (DiccionarioNumerador.existsDigito(componentes[2])) {
                            numerador += DiccionarioNumerador.getDigito(componentes[2]);
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

        if (numerador == 1)
            denominadorString += "s";

        System.out.println(denominadorString);

        // Es digito?
        if (DiccionarioDenominador.existsDigito(denominadorString))
            return DiccionarioDenominador.getDigito(denominadorString);

        // Es decimal
        if (DiccionarioDenominador.existsDecimal(denominadorString))
            return DiccionarioDenominador.getDecimal(denominadorString);

        // Es veinti
        if (denominadorString.toLowerCase().contains("veinti"))
        {
            String digito = denominadorString.replace("veinti", "");
            digito = digito.replace("avos", "");
            if (DiccionarioNumerador.existsDigito(digito))
                return 20 + DiccionarioNumerador.getDigito(digito);
        }

        // Es decimal con digito
        StringBuilder decenaSB = new StringBuilder();
        int denominador = 0;
        StringBuilder digitoSB = new StringBuilder();
        boolean isDigito = false;
        for (int i = 0, l = denominadorString.length(); i < l; i++){
            if(!isDigito)
            {
                decenaSB.append(denominadorString.charAt(i));
                if (DiccionarioNumerador.existsDecena(decenaSB.toString()))
                    if(denominadorString.toLowerCase().charAt(i + 1) == 'i')
                    {
                        denominador = DiccionarioNumerador.getDecena(decenaSB.toString());
                        i++;
                        isDigito = true;
                    }
                    else
                        throw new RuntimeException("El denominador <" + denominadorString + "> no es valido");
            }
            else
            {
                digitoSB.append(denominadorString.charAt(i));
            }
        }

        String digito = digitoSB.toString().replace("avos", "");
        // Si el digito es cero no es valido
        if(!digito.equalsIgnoreCase("cero"))
            if (DiccionarioNumerador.existsDigito(digito))
                return denominador + DiccionarioNumerador.getDigito(digito);

        throw new RuntimeException("El denominador <" + denominadorString + "> no es valido");
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
