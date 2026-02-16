package reto4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Enum que representa las monedas disponibles para conversión.
 */
public enum Moneda {
    USD, EUR, JPY, COP;

    /**
     * Convierte una cadena de texto a un valor del enum Moneda.
     * @param s Cadena de texto que representa la moneda (ej. "USD", "EUR")
     * @return El valor del enum Moneda correspondiente a la cadena de texto
     * @throws IllegalArgumentException Si la cadena no corresponde a ninguna moneda válida
     */
    public static Moneda fromString(String s) {
        if (s == null) throw new IllegalArgumentException("Moneda null");
        String v = s.trim().toUpperCase();
        try {
            return Moneda.valueOf(v);
        } catch (Exception e) {
            throw new IllegalArgumentException("Moneda inválida: " + s + " (use USD, EUR, JPY, COP)");
        }
    }

    /**
     * Convierte una cadena de texto con monedas separadas por comas a una lista de objetos Moneda.
     * @param csv Cadena de texto con monedas separadas por comas (ej. "USD,JPY")
     * @return Lista de objetos Moneda correspondientes a las monedas en la cadena de texto
     */
    public static List<Moneda> parseList(String csv) {
        if (csv == null || csv.trim().isEmpty()) return java.util.Collections.emptyList();
        return Arrays.stream(csv.split(","))
                .map(String::trim)
                .filter(x -> !x.isEmpty())
                .map(Moneda::fromString)
                .distinct()
                .collect(Collectors.toList());
    }
}
