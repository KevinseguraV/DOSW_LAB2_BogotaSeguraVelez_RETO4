package reto4;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Clase para calcular los totales convertidos por moneda a partir de una lista de resultados de conversiones.
 */
public class CalculadoraTotales {

    /**
     * Calcula los totales convertidos por moneda a partir de una lista de resultados de conversiones.
     * @param resultados Lista de resultados de conversiones
     * @return Mapa con la moneda como clave y el total convertido como valor
     */
    public Map<Moneda, Dinero> calcularTotales(List<ResultadoConversion> resultados) {
        Map<Moneda, BigDecimal> sumaPorMoneda = resultados.stream()
                .flatMap(r -> r.getConvertidos().entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                e -> e.getValue().getMonto(),
                                BigDecimal::add
                        )
                ));

        return sumaPorMoneda.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> new Dinero(e.getValue(), e.getKey())
                ));
    }
}
