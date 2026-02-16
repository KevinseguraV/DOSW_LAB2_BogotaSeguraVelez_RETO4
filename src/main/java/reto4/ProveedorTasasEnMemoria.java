package reto4;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementación de ProveedorTasas que almacena las tasas de cambio en memoria.
 */
public class ProveedorTasasEnMemoria implements ProveedorTasas {

    private final Map<String, BigDecimal> tasas = new HashMap<>();

    public ProveedorTasasEnMemoria() {
        
        // 1 EUR = 1.10 USD
        put(Moneda.EUR, Moneda.USD, "1.10");
        // 1 EUR = 165 JPY
        put(Moneda.EUR, Moneda.JPY, "165");
        // 1 USD = 150 JPY
        put(Moneda.USD, Moneda.JPY, "150");

        // 1 COP = 0.00025 USD
        put(Moneda.COP, Moneda.USD, "0.00025");
        // 1 COP = 0.00023 EUR
        put(Moneda.COP, Moneda.EUR, "0.00023");
        // 1 COP = 0.037 JPY
        put(Moneda.COP, Moneda.JPY, "0.037");

        agregarInversas();
    }

    /**
     * Agrega una tasa de cambio entre dos monedas al mapa de tasas.
     * @param o Moneda de origen
     * @param d Moneda de destino
     * @param tasa Tasa de cambio como String para ser convertida a BigDecimal
     */
    private void put(Moneda o, Moneda d, String tasa) {
        tasas.put(key(o, d), new BigDecimal(tasa));
    }

    /**
     * Genera una clave única para el par de monedas origen y destino.
     * @param o Moneda de origen
     * @param d Moneda de destino
     * @return String clave en formato "ORIGEN->DESTINO"
     */
    private String key(Moneda o, Moneda d) {
        return o.name() + "->" + d.name();
    }

    /**
     * Agrega las tasas inversas al mapa de tasas para asegurar que se puedan convertir en ambas direcciones.
     * También agrega una tasa de 1 para la conversión de una moneda a sí misma.
     */
    private void agregarInversas() {
        Map<String, BigDecimal> copia = new HashMap<>(tasas);
        for (Map.Entry<String, BigDecimal> e : copia.entrySet()) {
            String[] parts = e.getKey().split("->");
            Moneda o = Moneda.valueOf(parts[0]);
            Moneda d = Moneda.valueOf(parts[1]);
            BigDecimal tasa = e.getValue();

            String invKey = key(d, o);
            if (!tasas.containsKey(invKey)) {
                tasas.put(invKey, BigDecimal.ONE.divide(tasa, 12, java.math.RoundingMode.HALF_UP));
            }
        }

        for (Moneda m : Moneda.values()) {
            tasas.putIfAbsent(key(m, m), BigDecimal.ONE);
        }
    }

    @Override
    public BigDecimal obtenerTasa(Moneda origen, Moneda destino) {
        BigDecimal tasa = tasas.get(key(origen, destino));
        if (tasa == null) {
            throw new IllegalArgumentException("No existe tasa para: " + origen + " -> " + destino);
        }
        return tasa;
    }
}
