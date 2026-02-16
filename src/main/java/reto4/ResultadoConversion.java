package reto4;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Clase para representar el resultado de una conversión de moneda, incluyendo el monto original y los montos convertidos por moneda destino.
 */
public class ResultadoConversion {
    private final Dinero original;
    private final Map<Moneda, Dinero> convertidos = new LinkedHashMap<>();

    /**
     * Constructor para crear un resultado de conversión con el monto original.
     * @param original Dinero original que se va a convertir
     */
    public ResultadoConversion(Dinero original) {
        this.original = original;
    }

    public Dinero getOriginal() { return original; }
    public Map<Moneda, Dinero> getConvertidos() { return convertidos; }

    /**
     * Agrega un monto convertido para una moneda destino al resultado de la conversión.
     * @param destino Moneda destino a la que se ha convertido
     * @param dineroConvertido Dinero convertido en la moneda destino
     */
    public void add(Moneda destino, Dinero dineroConvertido) {
        convertidos.put(destino, dineroConvertido);
    }
}
