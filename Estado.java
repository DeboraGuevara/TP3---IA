

class Estado implements Comparable<Estado> {
    int x, y, z; // Coordenadas tridimensionales
    double costo; // Costo acumulado

    public Estado(int x, int y, int z, double costo) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.costo = costo;
    }

    @Override
    public int compareTo(Estado otro) {
        return Double.compare(this.costo, otro.costo);
    }
}