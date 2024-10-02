package calculadorafracciones.conversor;

import java.util.HashMap;
import java.util.Map;

public class DiccionarioDenominador {

    private static HashMap<String, Integer> digitos = new HashMap<>();
    private static HashMap<String, Integer> decimales = new HashMap<>();
    private static HashMap<String, Integer> multiplicadores = new HashMap<>();

    static {
        initDigitos();
        initDecimales();
        initMultiplicadores();
    }

    private static void initDigitos() {
        digitos.put("ceroavos", 0);
        digitos.put("enteros", 1);
        digitos.put("medios", 2);
        digitos.put("tercios", 3);
        digitos.put("cuartos", 4);
        digitos.put("quintos", 5);
        digitos.put("sextos", 6);
        digitos.put("septimos", 7);
        digitos.put("octavos", 8);
        digitos.put("novenos", 9);
    }

    private static void initDecimales() {
        decimales.put("decimos", 10);
        decimales.put("veinteavos", 20);
        decimales.put("treintavos", 30);
        decimales.put("cuarentavos", 40);
        decimales.put("cincuentavos", 50);
        decimales.put("sesentavos", 60);
        decimales.put("setentavos", 70);
        decimales.put("ochentavos", 80);
        decimales.put("noventavos", 90);
    }

    private static void initMultiplicadores() {
        multiplicadores.put("centesimos", 100);
        multiplicadores.put("milesimos", 1000);
    }

    public static int getDigito(String key) {
        return digitos.get(key.toLowerCase());
    }

    public static int getDecimal(String key){
        return decimales.get(key.toLowerCase());
    }

    public static boolean existsDigito(String key)
    {
        return digitos.containsKey(key.toLowerCase());
    }

    public static boolean existsDecimal(String key) {
        return decimales.containsKey(key.toLowerCase());
    }

    public static String getDigito(int value) {
        for(Map.Entry<String, Integer> entry : digitos.entrySet())
        {
            if (entry.getValue() == value)
                return entry.getKey();
        }
        return "";
    }

    public static String getDecimal(int value) {
        for(Map.Entry<String, Integer> entry : decimales.entrySet())
        {
            if (entry.getValue() == value)
                return entry.getKey();
        }
        return "";
    }

    public static String getMultiplicador(int value) {
        for(Map.Entry<String, Integer> entry : multiplicadores.entrySet())
        {
            if (entry.getValue() == value)
                return entry.getKey();
        }
        return "";
    }
}
