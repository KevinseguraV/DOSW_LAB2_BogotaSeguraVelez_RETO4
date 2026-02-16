package reto4;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal para ejecutar el programa de conversión de monedas.
 */
public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ProveedorTasas proveedor = new ProveedorTasasEnMemoria();
        CasaCambioFacade facade = new CasaCambioFacade(proveedor);

        System.out.print("Ingrese número de transacciones: ");
        int n = leerEntero(sc);

        List<Transaccion> transacciones = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            System.out.println("\n--- Transacción " + i + " ---");
            System.out.print("Ingrese monto: ");
            BigDecimal monto = leerBigDecimal(sc);

            System.out.print("Ingrese moneda de origen (USD, EUR, JPY, COP): ");
            Moneda origen = Moneda.fromString(sc.nextLine());

            System.out.print("Ingrese monedas destino (separadas por coma, ej: USD,JPY): ");
            List<Moneda> destinos = Moneda.parseList(sc.nextLine());

            transacciones.add(Transaccion.of(monto, origen, destinos));
        }

        facade.procesarTransacciones(transacciones);
        sc.close();
    }

    /**
     * Lee un número entero desde la consola, validando la entrada.
     * @param sc Scanner para leer la entrada del usuario
     * @return El número entero ingresado por el usuario
     */
    private static int leerEntero(Scanner sc) {
        while (true) {
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (Exception e) {
                System.out.print("Número inválido. Intente de nuevo: ");
            }
        }
    }

    /**
     * Lee un número decimal (BigDecimal) desde la consola, validando la entrada.
     * @param sc Scanner para leer la entrada del usuario
     * @return El número decimal ingresado por el usuario como BigDecimal
     */
    private static BigDecimal leerBigDecimal(Scanner sc) {
        while (true) {
            String s = sc.nextLine().trim().replace(",", ".");
            try {
                return new BigDecimal(s);
            } catch (Exception e) {
                System.out.print("Monto inválido. Intente de nuevo: ");
            }
        }
    }
}
