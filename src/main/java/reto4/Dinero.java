package reto4;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Clase que representa una cantidad de dinero en una moneda específica.
 */
public class Dinero {
    private final BigDecimal monto;
    private final Moneda moneda;

    public Dinero(BigDecimal monto, Moneda moneda) {
        if (monto == null) throw new IllegalArgumentException("Monto null");
        if (moneda == null) throw new IllegalArgumentException("Moneda null");
        this.monto = monto;
        this.moneda = moneda;
    }

    public BigDecimal getMonto() { return monto; }
    public Moneda getMoneda() { return moneda; }

    /**
     * Redondea el monto a un número específico de decimales utilizando el modo HALF_UP.
     * @param scale Número de decimales a los que se desea redondear
     * @return Un nuevo objeto Dinero con el monto redondeado
     */
    public Dinero redondear(int scale) {
        return new Dinero(monto.setScale(scale, RoundingMode.HALF_UP), moneda);
    }

    /**
     * Devuelve una representación en formato legible del monto y la moneda.
     * Para COP y JPY se muestra sin decimales, para otras monedas con 2 decimales.
     * @return String con el formato del dinero
     */
    public String formato() {
        int decimales = (moneda == Moneda.COP || moneda == Moneda.JPY) ? 0 : 2;
        return monto.setScale(decimales, RoundingMode.HALF_UP) + " " + moneda;
    }
}
