package Gestion_de_port;


public class Bateau {
    private Port departPort;
    private Port arrivePort;
    private boolean enMer;
    private int x;
    private int y;


    Bateau(){
        enMer = true;
        departPort=null;
        arrivePort=null;
    }
    Bateau(Port dejaArrivePort){
        if (dejaArrivePort.ajouterBateau()) {
            enMer = false;
            departPort = null;
            arrivePort = dejaArrivePort;
        } else {
            enMer = true;
            departPort = dejaArrivePort;
            arrivePort = null;
        }
    }

    public void accoster(Port a){
        //reserver un quai au port a et a y accoster le bateau donc quaisOcc ++ 
        //bateau arrivePort = port a si possible d'accoster
        System.out.println(" est la nouvelle position du bateau! ");
        if (a.ajouterBateau()) {
            enMer = false;
            arrivePort = a;
        }
    }
    public void quitter( ){
        //quitter port quaisOcc--
        //port departPort()=arrivePort 
        //port arrivePort()=nouvelleDestination
        System.out.print("Le bateau se deplace de sa position! ");
        if (!enMer) {
            departPort = arrivePort;
            arrivePort.retirerBateau();
            enMer = true;
        }
    }
    public float distance(){
        float distance;
        if (enMer || departPort == null || arrivePort == null) {
            return -1; // Not applicable
        } else {
            double distanceX = departPort.getX() - arrivePort.getX();
            double distanceY = departPort.getY() - arrivePort.getY();
            distance = (float) Math.sqrt(distanceX * distanceX + distanceY * distanceY);
            System.out.println("Le bateau a fait une distance de " + distance +"!\n\n");
            return distance;
            
        }
    }

    // Constructor and other methods...

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


}
