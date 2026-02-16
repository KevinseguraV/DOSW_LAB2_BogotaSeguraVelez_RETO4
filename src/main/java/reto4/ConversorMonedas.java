package reto4;

import java.math.BigDecimal;

/**
 * Clase principal para convertir monedas utilizando un proveedor de tasas.
 */
public class ConversorMonedas {

    private final ProveedorTasas proveedorTasas;

    public ConversorMonedas(ProveedorTasas proveedorTasas) {
        this.proveedorTasas = proveedorTasas;
    }

    /**
     * Convierte una transacción a las monedas destino especificadas.
     * @param t Transacción que contiene el monto original y las monedas destino
     * @return Resultado de la conversión con los montos convertidos por cada moneda destino
     */
    public ResultadoConversion convertir(Transaccion t) {
        Dinero original = t.getOriginal();
        ResultadoConversion resultado = new ResultadoConversion(original);

        for (Moneda destino : t.getDestinos()) {
            Dinero conv = convertir(original, destino);
            resultado.add(destino, conv);
        }
        return resultado;
    }

    /**
     * Convierte un monto de una moneda origen a una moneda destino utilizando 
     * la tasa de cambio proporcionada.
     * @param original Dinero original con monto y moneda de origen
     * @param destino Moneda destino a la que se desea convertir
     * @return Dinero convertido con el monto en la moneda destino
     */
    public Dinero convertir(Dinero original, Moneda destino) {
        if (original.getMoneda() == destino) {
            return new Dinero(original.getMonto(), destino);
        }

        BigDecimal tasa = proveedorTasas.obtenerTasa(original.getMoneda(), destino);
        BigDecimal convertido = original.getMonto().multiply(tasa);

        return new Dinero(convertido, destino);
    }
}
