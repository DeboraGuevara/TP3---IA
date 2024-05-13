import java.util.*;



public class PrimeroElMejor {
    static final int MAX_MOVIMIENTO = 6; // Máximo de movimientos en la horizontal "H"

    public static void main(String[] args) {
        // Definir estado inicial, objetivo y otros parámetros
        Estado inicial = new Estado(0, 0, 0, 0);
        Estado objetivo = new Estado(10, 10, 10, 0); // Objetivo con costo 0

        // Crear cola de prioridad para la búsqueda
        PriorityQueue<Estado> colaPrioridad = new PriorityQueue<>(Comparator.comparingDouble(e -> e.costo));
        colaPrioridad.add(inicial);

        // Mapa para almacenar padres y costos acumulados
        Map<Estado, Estado> padres = new HashMap<>();
        Map<Estado, Double> costosAcumulados = new HashMap<>();
        padres.put(inicial, null);
        costosAcumulados.put(inicial, 0.0);

        // Búsqueda Primero el Mejor
        Estado actual = null;
        while (!colaPrioridad.isEmpty()) {
            actual = colaPrioridad.poll();
            System.out.println("Explorando nodo: [" + actual.x + ", " + actual.y + ", " + actual.z + "]");
            if (actual.equals(objetivo)) {
                System.out.println("¡Estado objetivo alcanzado!");
                break; // Llegamos al estado objetivo
            }

            // Generar nodos vecinos en la misma horizontal "H" que el estado actual
            List<Estado> vecinos = generarVecinosEnHorizontal(actual);
            for (Estado vecino : vecinos) {
                double costoAcumulado = costosAcumulados.get(actual) + calcularCosto(actual, vecino);
                if (!costosAcumulados.containsKey(vecino) || costoAcumulado < costosAcumulados.get(vecino)) {
                    costosAcumulados.put(vecino, costoAcumulado);
                    double costoHeuristico = calcularHeuristica(vecino, objetivo); // Calcular heurística
                    vecino.costo = costoAcumulado + costoHeuristico; // Actualizar costo total
                    colaPrioridad.add(vecino);
                    padres.put(vecino, actual);
                    System.out.println("Añadido vecino: [" + vecino.x + ", " + vecino.y + ", " + vecino.z + "]");
                }
            }
        }

        // Reconstruir el camino y mostrar resultados
        List<Estado> camino = new ArrayList<>();
        while (actual != null) {
            camino.add(actual);
            actual = padres.get(actual);
        }
        Collections.reverse(camino);

        System.out.println("Camino recorrido:");
        for (Estado estado : camino) {
            System.out.println("[" + estado.x + ", " + estado.y + ", " + estado.z + "]");
        }

        System.out.println("Cantidad de nodos explorados: " + padres.size());
    
    }

    // Método para generar nodos vecinos en la misma horizontal "H" que el estado actual
    private static List<Estado> generarVecinosEnHorizontal(Estado estado) {
        List<Estado> vecinos = new ArrayList<>();
        // Generar nodos vecinos siguiendo la horizontal "H" desde el estado actual
        for (int i = 1; i <= MAX_MOVIMIENTO; i++) {
            // Se asume que el movimiento se realiza siempre sobre la horizontal "H"
            int nuevaX = estado.x + i; // Movimiento en la dirección positiva de x
            int nuevaY = estado.y; // Mantiene la misma coordenada y
            int nuevaZ = estado.z; // Mantiene la misma coordenada z
            if (nuevaX <= MAX_MOVIMIENTO) { // Limitación de movimiento
                vecinos.add(new Estado(nuevaX, nuevaY, nuevaZ, 0)); // El costo se calcula después
            }
        }
        return vecinos;
    }

    // Método para calcular el costo entre dos estados vecinos
    private static double calcularCosto(Estado estadoActual, Estado estadoVecino) {
        // En este ejemplo, el costo es la distancia Manhattan
        return Math.abs(estadoVecino.x - estadoActual.x) +
               Math.abs(estadoVecino.y - estadoActual.y) +
               Math.abs(estadoVecino.z - estadoActual.z);
    }

    // Método para calcular la heurística (distancia Manhattan)
    private static double calcularHeuristica(Estado estado, Estado objetivo) {
        // Distancia en cada dimensión (x, y, z) sumada
        return Math.abs(estado.x - objetivo.x) +
               Math.abs(estado.y - objetivo.y) +
               Math.abs(estado.z - objetivo.z);
    }
}
