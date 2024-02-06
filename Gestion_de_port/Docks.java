package Gestion_de_port;

public class Docks {
    private int nbDocks; // nombre de Docks d'un port
    private int docksOcc; // nombre de Docks déjà occupés dans le port

    public Docks() {
        nbDocks = 3;
        docksOcc = 0;
    }

    public Docks(int nbDocks) {
        this();
        if (nbDocks > 0) {
            this.nbDocks = nbDocks;
        }
    }

    public boolean addBoat() {
        if (docksOcc < nbDocks) {
            System.out.println("           Bateau ajoute au quais!                                        +1");
            docksOcc++;
            System.out.println("           Nombre de Quai Occupe = " + docksOcc + "/3");
            return true;
        } else {
            System.out.println("           Les quais sont rempli" + docksOcc + "/3! Acces refuse.");
            return false;
        }
    }

    public void takeBoat() {
        if (docksOcc > 0) {
            System.out.println("           Bateau retire du quais!                                        -1");
            docksOcc--;
            System.out.println("           Nombre de quais occupe : " + docksOcc + "/3");
        }
    }

    public int getdocksOcc() {
        return docksOcc;
    }
}
