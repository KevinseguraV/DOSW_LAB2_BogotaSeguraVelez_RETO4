package reto4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Fachada para el sistema de casa de cambio, que coordina la conversión de monedas y el cálculo de totales.
 */
public class CasaCambioFacade {

    private final ConversorMonedas conversor;
    private final CalculadoraTotales calculadoraTotales;

    public CasaCambioFacade(ProveedorTasas proveedorTasas) {
        this.conversor = new ConversorMonedas(proveedorTasas);
        this.calculadoraTotales = new CalculadoraTotales();
    }

    /**
     * Procesa una lista de transacciones, realizando las conversiones y calculando los totales por moneda.
     * @param transacciones Lista de transacciones a procesar
     */
    public void procesarTransacciones(List<Transaccion> transacciones) {
        if (transacciones == null || transacciones.isEmpty()) {
            System.out.println("No hay transacciones para procesar.");
            return;
        }

        List<ResultadoConversion> resultados = transacciones.stream()
                .map(this::convertirTransaccion)
                .collect(Collectors.toList());

        for (int i = 0; i < resultados.size(); i++) {
            imprimirResultado(i + 1, resultados.get(i));
        }

        Map<Moneda, Dinero> totales = calculadoraTotales.calcularTotales(resultados);

        System.out.println("\n--- Totales por moneda ---");
        totales.forEach((moneda, dinero) ->
                System.out.println(moneda + ": " + dinero.formato())
        );
    }

    /**
     * Convierte una transacción utilizando el conversor de monedas.
     * @param t Transacción a convertir
     * @return Resultado de la conversión
     */
    private ResultadoConversion convertirTransaccion(Transaccion t) {
        if (t.getDestinos().isEmpty()) {
            throw new IllegalArgumentException("La transacción debe tener al menos una moneda destino.");
        }
        return conversor.convertir(t);
    }

    /**
     * Imprime el resultado de una transacción de manera legible.
     * @param num Número de la transacción
     * @param res Resultado de la conversión a imprimir
     */
    private void imprimirResultado(int num, ResultadoConversion res) {
        System.out.println("\nTransacción " + num + ": " + res.getOriginal().formato());
        res.getConvertidos().forEach((moneda, dinero) ->
                System.out.println("  Convertido a " + moneda + ": " + dinero.formato())
        );
    }
}
