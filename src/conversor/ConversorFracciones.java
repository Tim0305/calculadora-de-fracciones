package conversor;

import fraccion.Fraccion;

public class ConversorFracciones {

    public static void toFraccion(String fraccionString) {
        // Numerador
        int numerador = getNumerador(fraccionString.trim());
        System.out.println("Numerador: " + numerador);
    }

    private static int getNumerador(String fraccion) {
        String[] componentes = fraccion.split(" ");

        // Digito
        if (componentes[0].equalsIgnoreCase("uno")) {
            throw new Error("El numerador 'uno' no es un numerador valido");
        } else if (componentes[0].equalsIgnoreCase("un")) {
            return 1;
        } else if (DiccionarioNumerador.existsDigito(componentes[0])) {
            int numerador = DiccionarioNumerador.getDigito(componentes[0]);

            return numerador;
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
            }

            return numerador;
        }

        // Decenas
        if (DiccionarioNumerador.existsDecena(componentes[0])) {
            int numerador = DiccionarioNumerador.getDecena(componentes[0]);

            // si hay mas de dos componentes puedo acceder a la proxima pos
            if (componentes.length > 1) {
                if (componentes[1].equalsIgnoreCase("y")) {
                    if (DiccionarioNumerador.existsDigito(componentes[2])) {
                        numerador += DiccionarioNumerador.getDigito(componentes[2]);
                    }
                }
            }

            return numerador;
        }
        return -1;
    }
}
