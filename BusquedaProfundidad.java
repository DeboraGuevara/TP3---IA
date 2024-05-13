import java.util.Stack;

public class BusquedaProfundidad {
    // Método de búsqueda en profundidad con objetivo aproximado y limitación de profundidad
    public static int buscarEnProfundidad(double posicionInicial, double objetivo, double tolerancia, int profundidadMaxima) {
        Stack<Double> pila = new Stack<>();
        pila.push(posicionInicial);
        int nodosExplorados = 0; // Contador de nodos explorados

        while (!pila.isEmpty()) {
            double posicion = pila.pop();
            nodosExplorados++;
         

            // Verificar si estamos cerca del objetivo dentro de la tolerancia
            if (Math.abs(posicion - objetivo) <= tolerancia) {
                System.out.println("¡Se encontró la solución!");
                return nodosExplorados;
            }

            // Verificar la profundidad máxima alcanzada
            if (nodosExplorados < profundidadMaxima) {
                // Generar sucesores y agregarlos a la pila
                double movimientoIzquierda = Math.max(-1.5, -12 + posicion); // Limitar movimiento a 12 unidades a la izquierda
                double movimientoDerecha = Math.min(1.5, 12 - posicion); // Limitar movimiento a 12 unidades a la derecha
                pila.push(posicion + movimientoIzquierda);
                pila.push(posicion + movimientoDerecha);
            }
        }

        System.out.println("No se encontró solución.");
        return -1; // Si no se encontró solución
    }

    public static void main(String[] args) {
        double posicionInicial = 0; // Posición inicial "B"
        double objetivo = 12; // Posición objetivo "A" más cercana
        double tolerancia = 0.1; // Tolerancia para considerar que se alcanzó el objetivo
        int profundidadMaxima = 1000; // Límite de profundidad de búsqueda

        int nodosExplorados = buscarEnProfundidad(posicionInicial, objetivo, tolerancia, profundidadMaxima);

        if (nodosExplorados >= 0) {
            System.out.println("Se encontró una solución en " + nodosExplorados + " nodos explorados.");
        } else {
            System.out.println("No se encontró solución.");
        }
    }
}
