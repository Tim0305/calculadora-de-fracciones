package conversor;

import fraccion.Fraccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConversorFracciones {

    private static String denominadorString;

    public static Fraccion toFraccion(String fraccionString) {
        int numerador = getNumerador(fraccionString.trim());
        int denominador = getDenominador(numerador, denominadorString);
        return new Fraccion(numerador, denominador);
    }

    public static String toString(Fraccion fraccion) {
        StringBuilder sb = new StringBuilder();
        sb.append(getNumeradorString(fraccion.numerador));
        sb.append(" ");
        sb.append(getDenominadorString(fraccion));
        return sb.toString().trim();
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
                    if (!componentes[2].equalsIgnoreCase("cero")) {
                        if (DiccionarioNumerador.existsDigito(componentes[2])) {
                            numerador += DiccionarioNumerador.getDigito(componentes[2]);
                        } else {
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
        throw new RuntimeException("El numerador <" + componentes[0] + "> no es valido'");
    }

    private static int getDenominador(int numerador, String denominadorString) {
        /*
        Si numerador es igual a 1 entonces se agrega una 's' para obtener el valor del numero
        Si no es igual, entonces no se agrega nada.
        Ejemplo
        un tercios
        tercioss -> esto no se encuentra por lo que da un error
        un tercio
        tercios -> esto si se encuentra en el diccionario
         */

        if (numerador == 1) {
            denominadorString += "s";
        }

        // Es digito?
        if (DiccionarioDenominador.existsDigito(denominadorString)) {
            return DiccionarioDenominador.getDigito(denominadorString);
        }

        // Es decimal
        if (DiccionarioDenominador.existsDecimal(denominadorString)) {
            return DiccionarioDenominador.getDecimal(denominadorString);
        }

        // Es veinti
        if (denominadorString.toLowerCase().contains("veinti")) {
            String digito = denominadorString.replace("veinti", "");
            digito = digito.replace("avos", "");
            if (DiccionarioNumerador.existsDigito(digito)) {
                return 20 + DiccionarioNumerador.getDigito(digito);
            }
        }

        // Es decimal con digito
        StringBuilder decenaSB = new StringBuilder();
        int denominador = 0;
        StringBuilder digitoSB = new StringBuilder();
        boolean isDigito = false;
        for (int i = 0, l = denominadorString.length(); i < l; i++) {
            if (!isDigito) {
                decenaSB.append(denominadorString.charAt(i));
                if (DiccionarioNumerador.existsDecena(decenaSB.toString())) {
                    if (denominadorString.toLowerCase().charAt(i + 1) == 'i') {
                        denominador = DiccionarioNumerador.getDecena(decenaSB.toString());
                        i++;
                        isDigito = true;
                    } else {
                        throw new RuntimeException("El denominador <" + denominadorString + "> no es valido");
                    }
                }
            } else {
                digitoSB.append(denominadorString.charAt(i));
            }
        }

        String digito = digitoSB.toString().replace("avos", "");
        // Si el digito es cero no es valido
        if (!digito.equalsIgnoreCase("cero")) {
            if (DiccionarioNumerador.existsDigito(digito)) {
                return denominador + DiccionarioNumerador.getDigito(digito);
            }
        }

        throw new RuntimeException("El denominador <" + denominadorString + "> no es valido");
    }

    private static String getNumeradorString(int numerador) {

        boolean isNegative = numerador < 0;
        StringBuilder sb = new StringBuilder();

        if (isNegative) {
            numerador = Math.abs(numerador);
            sb.append("menos ");
        }

        List<Integer> digitsNumerador = getListOfDigits(numerador);

        int number = 0;
        boolean isCien = false;

        if (digitsNumerador.size() == 0) {
            sb.append("cero ");
        }

        for (int i = 0, l = digitsNumerador.size(), j = l - 1; i < l; i++, j--) {
            int p = j % 3; // 0, 1, 2 -> potencias
            String multiplicador = "";

            // Multiplicador
            int index = l - i - 1;

            number = (int) Math.pow(10, p) * digitsNumerador.get(i);

            switch (p) {
                case 0:
                    // Digito
                    if (number != 0) {
                        if (isCien) {
                            sb.append("ciento");
                            isCien = false;
                        }

                        multiplicador = getMultiplicador(index);
                        if (!multiplicador.isEmpty()) {
                            if (number != 1) {
                                sb.append(DiccionarioNumerador.getDigito(number));
                                sb.append(" ");

                            }
                            sb.append(multiplicador);
                        } else {
                            sb.append(DiccionarioNumerador.getDigito(number));
                        }

                        sb.append(" ");
                    }

                    if (isCien) {
                        sb.append("cien ");
                    }
                    break;
                case 1:
                    // Especial y Decena

                    if (number != 0) {
                        if (isCien) {
                            sb.append("ciento ");
                            isCien = false;
                        }

                        int digito = digitsNumerador.get(++i);
                        index = l - i - 1;
                        j--;
                        multiplicador = getMultiplicador(index);

                        if (number == 10) // especial o decena
                        {
                            if (digito == 0) // Diez
                            {
                                sb.append(DiccionarioNumerador.getDecena(number));
                            } else // Especial
                            {
                                sb.append(DiccionarioNumerador.getEspecial(10 + digito));
                            }
                            sb.append(" ");
                        } else {
                            if (digito != 0) {
                                // Veinti
                                if (number == 20) {
                                    sb.append("veinti");
                                } else {
                                    sb.append(DiccionarioNumerador.getDecena(number));
                                    sb.append(" y ");
                                }

                                sb.append(DiccionarioNumerador.getDigito(digito));
                            } else {
                                sb.append(DiccionarioNumerador.getDecena(number));
                            }

                            sb.append(" ");
                        }

                        if (!multiplicador.isEmpty()) {
                            sb.append(multiplicador);
                            sb.append(" ");
                        }
                    }
                    break;
                case 2:
                    //Centena

                    if (number != 100) {
                        isCien = false;
                        sb.append(DiccionarioNumerador.getCentena(number));
                        sb.append(" ");
                    } else {
                        isCien = true;
                    }
                    break;
            }
        }

        return sb.toString().trim();
    }

    private static String getDenominadorString(Fraccion fraccion) {
        List<Integer> digitsDenominador = getListOfDigits(fraccion.denominador);
        String denominadorString = "";
        char terminacion = fraccion.numerador != 1 ? 's' : (char) 0;

        // Digito unico
        if (digitsDenominador.size() == 1) {
            denominadorString = DiccionarioDenominador.getDigito(digitsDenominador.get(0));
            if (terminacion == 0) // Eliminar el ultimo caracter que es la 's'
            {
                denominadorString = denominadorString.substring(0, denominadorString.length() - 1);
            }
            return denominadorString;
        } else {
            denominadorString = getNumeradorString(fraccion.denominador);

            if (denominadorString.equalsIgnoreCase("diez")) {
                return "decimo" + terminacion;
            } else if (denominadorString.equalsIgnoreCase("cien")) {
                return "centesimo" + terminacion;
            } else if (denominadorString.equalsIgnoreCase("mil")) {
                return "milesimo" + terminacion;
            } else {
                denominadorString = denominadorString.replace(" y ", "i");
                denominadorString = denominadorString.replace(" ", "");
                denominadorString += "avo" + terminacion;
            }
        }

        return denominadorString;

    }

    private static List<Integer> getListOfDigits(int number) {
        List<Integer> digits = new ArrayList<>();
        while (number != 0) {
            int digit = number % 10;
            number /= 10;
            // Siempre agregarlo al inicio porque empezamos con el ultimo digito
            digits.add(0, digit);
        }

        return digits;
    }

    private static String getMultiplicador(int index) {
        if (index % 3 == 0) {
            return DiccionarioNumerador.getMultiplicador((int) Math.pow(10, index));
        }
        return "";
    }
}
