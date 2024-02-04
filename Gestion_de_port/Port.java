package Gestion_de_port;

public class Port {
    private int x;
    private int y;
    private Quais quais; //gestionnaire de quai

    Port(int x1, int y1){
        x = x1;
        y = y1;
        quais = new Quais();
    }

    Port(int x1, int y1,int nbQuais){
        x = x1;
        y = y1;
        quais = new Quais(nbQuais);
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void retirerBateau(){
        quais.retraitBateau();
        System.out.println("Bateau retirer du port!");
    }

    public boolean ajouterBateau(){
        System.out.println("Bateau ajouter au port!");
        return quais.ajouterBateau(); 
    }

    public Quais getQuais() {
        return quais;
    }
}
