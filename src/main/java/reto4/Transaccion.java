package reto4;

import java.math.BigDecimal;
import java.util.List;

/**
 * Clase para representar una transacción de conversión de moneda, 
 * que incluye el monto original y las monedas destino a las que se desea convertir.
 */
public class Transaccion {
    private final Dinero original;
    private final List<Moneda> destinos;

    public Transaccion(Dinero original, List<Moneda> destinos) {
        if (original == null) throw new IllegalArgumentException("Dinero original null");
        if (destinos == null) throw new IllegalArgumentException("Destinos null");
        this.original = original;
        this.destinos = destinos;
    }

    /**
     * Método de fábrica para crear una transacción a partir de un monto, moneda de origen y lista de monedas destino.
     * @param monto Monto a convertir
     * @param origen Moneda de origen del monto
     * @param destinos Lista de monedas destino a las que se desea convertir
     * @return Nueva instancia de Transaccion con el dinero original y las monedas destino
     */
    public static Transaccion of(BigDecimal monto, Moneda origen, List<Moneda> destinos) {
        return new Transaccion(new Dinero(monto, origen), destinos);
    }

    public Dinero getOriginal() { return original; }
    public List<Moneda> getDestinos() { return destinos; }
}

