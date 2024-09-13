package conversor;

import java.util.HashMap;

public class DiccionarioNumeros {

    private static HashMap<String, Integer> digitos = new HashMap<>();
    private static HashMap<String, Integer> especiales = new HashMap<>();
    private static HashMap<String, Integer> decenas = new HashMap<>();
    private static HashMap<String, Integer> multiplicadores = new HashMap<>();

    static {
        initDigitos();
        initEspeciales();
        initDecenas();
        initMultiplicadores();
    }

    public static int getDigito(String key) {
        return digitos.get(key);
    }

    public static int getEspecial(String key) {
        return especiales.get(key);
    }

    public static int getDecena(String key) {
        return decenas.get(key);
    }

    public static boolean existsDigito(String key) {
        return digitos.containsKey(key);
    }

    public static boolean existsEspecial(String key) {
        return especiales.containsKey(key);
    }

    public static boolean existsDecena(String key) {
        return decenas.containsKey(key);
    }

    private static void initDigitos() {
        digitos.put("cero", 0);
        digitos.put("uno", 1);
        digitos.put("dos", 2);
        digitos.put("tres", 3);
        digitos.put("cuatro", 4);
        digitos.put("cinco", 5);
        digitos.put("seis", 6);
        digitos.put("siete", 7);
        digitos.put("ocho", 8);
        digitos.put("nueve", 9);
    }

    private static void initEspeciales() {
        especiales.put("once", 11);
        especiales.put("doce", 12);
        especiales.put("trece", 13);
        especiales.put("catorce", 14);
        especiales.put("quince", 15);
        especiales.put("dieciseis", 16);
        especiales.put("diecisiete", 17);
        especiales.put("dieciocho", 18);
        especiales.put("diecinueve", 19);
    }

    private static void initDecenas() {
        decenas.put("diez", 10);
        decenas.put("veinte", 20);
        decenas.put("treinta", 30);
        decenas.put("cuarenta", 40);
        decenas.put("cincuenta", 50);
        decenas.put("sesenta", 60);
        decenas.put("setenta", 70);
        decenas.put("ochenta", 80);
        decenas.put("noventa", 90);
    }

    private static void initMultiplicadores() {
        multiplicadores.put("mil", 1000);
    }

}
