package reto4;

import java.math.BigDecimal;

/**
 * Clase principal para convertir monedas utilizando un proveedor de tasas.
 */
public interface ProveedorTasas {
    BigDecimal obtenerTasa(Moneda origen, Moneda destino);
}
