package Gestion_de_port;

public class Quais {
    private int nbQuais; //nombre de quais d'un port
    private int quaisOcc; //nombre de quais déjà occupés dans le port

    public Quais( ){
        nbQuais = 3;
        quaisOcc = 0;
    }
    public Quais(int nbQuais){
        this( );
        if(nbQuais>0){
            this.nbQuais=nbQuais;
        }
    }


    public boolean ajouterBateau(){
        if(quaisOcc < nbQuais){
            System.out.println("Bateau ajouter au quais!");
            quaisOcc++;
            System.out.println("Nombre de Quai Occupe = "+quaisOcc + "/3");
            return true;
        }else{
            System.out.println("Les quais sont rempli"+ quaisOcc+"/3");
            return false;
        }
    }

    public void retraitBateau(){
        if (quaisOcc > 0) {
            System.out.println("Bateau retirer du quais!");
            quaisOcc--;
            System.out.println("Nombre de quais occupe : "+quaisOcc+"/3");
        }
    }

    public int getQuaisOcc() {
        return quaisOcc;
    }
}
