package Gestion_de_port;

public class Port {
    private int x, y;
    private Docks docks; // gestionnaire de quai

    Port(int x1, int y1) {
        x = x1;
        y = y1;
        docks = new Docks();
    }

    Port(int x1, int y1, int nbDocks) {
        x = x1;
        y = y1;
        docks = new Docks(nbDocks);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void takeBoat() {
        docks.takeBoat();
        System.out.println("Bateau retire du port!");
    }

    public boolean addBoat() {
        System.out.println("Bateau ajoute au port!");
        return docks.addBoat();
    }

    public Docks getDocks() {
        return docks;
    }
}
