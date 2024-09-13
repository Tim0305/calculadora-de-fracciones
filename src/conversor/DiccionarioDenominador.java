package conversor;

import java.util.HashMap;
import java.util.Map;

public class DiccionarioDenominador {

    private static HashMap<String, Integer> digitos = new HashMap<>();
    private static HashMap<String, Integer> especiales = new HashMap<>();

    static {
        initDigitos();
        initEspeciales();
    }

    private static void initDigitos() {
        digitos.put("ceroavos", 0);
        digitos.put("enteros", 0);
        digitos.put("medios", 0);
        digitos.put("tercios", 0);
        digitos.put("cuartos", 0);
        digitos.put("quintos", 0);
        digitos.put("sextos", 0);
        digitos.put("septimos", 0);
        digitos.put("octavos", 0);
        digitos.put("novenos", 0);
    }

    private static void initEspeciales() {
        especiales.put("decimos", 10);
        especiales.put("centesimos", 100);
        especiales.put("milesimos", 1000);
    }
}
