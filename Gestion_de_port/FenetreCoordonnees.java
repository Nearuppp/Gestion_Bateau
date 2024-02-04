package Gestion_de_port;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FenetreCoordonnees extends JFrame {

    private JPanel panel;
    private JLabel portCountLabel;
    
    private int dragOffsetX, dragOffsetY; // Offset between mouse click and rectangle corner

    // Image positions
    private int imageIleX = 500;
    private int imageIleY = 300;
    private int imageIle2X = 100;
    private int imageIle2Y = 100;
    private int imageBateauX = 400;
    private int imageBateauY = 300;

    Port port1 = new Port(600, 400); // Define the coordinates of port1 relative of the center of Island 1
    Port port2 = new Port(200, 200); // Define the coordinates of port2 relative to the center of Island 2 
    
    Bateau bateau = new Bateau(); //Create one boat

    public FenetreCoordonnees() {
        setTitle("Gestion de Bateau");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Quais quais = port1.getQuais();

        portCountLabel = new JLabel("Number of Ports: ");
        getContentPane().setLayout(new BorderLayout());//cree la delimentation le panel
        getContentPane().add(portCountLabel, BorderLayout.NORTH);

        JButton addBoatButton = new JButton("Add Boat"); // Button to add a new boat
        addBoatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new boat and add it to the ArrayList
                // Call repaint to update the panel with the new boat
                panel.repaint();
            }
        });
        
        getContentPane().add(addBoatButton, BorderLayout.SOUTH); // Add button to the bottom of the frame
        
        
        panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Load the image from a file
                ImageIcon imageIcon = new ImageIcon("D:\\LOCAL\\Java\\src_cours1\\Gestion_de_port\\800x800.jpg");
                Image image = imageIcon.getImage();
                ImageIcon Ile = new ImageIcon("D:\\LOCAL\\Java\\src_cours1\\Gestion_de_port\\Ile.png");
                Image imageIle = Ile.getImage();
                ImageIcon Ile2 = new ImageIcon("D:\\LOCAL\\Java\\src_cours1\\Gestion_de_port\\Ile2.png");
                Image imageIle2 = Ile2.getImage();
                ImageIcon BateauIcon = new ImageIcon("D:\\LOCAL\\Java\\src_cours1\\Gestion_de_port\\Petit_Bateau.png");
                Image imageBateau = BateauIcon.getImage();

                // Draw the image at the panel's upper left corner
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

                //draw images
                g.drawImage(imageIle, imageIleX, imageIleY, 200, 200, this);
                g.drawImage(imageIle2, imageIle2X, imageIle2Y, 200, 200, this);
                g.drawImage(imageBateau, imageBateauX, imageBateauY, 100, 100, this);

                
                
                
            }

            
        };
        
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int x = e.getX();
                int y = e.getY();
                
                System.out.println("Coordonnées : (" + x + ", " + y + ")");
                if (imageBateauX >= imageIleX && imageBateauX <= imageIleX + 100 && imageBateauY >= imageIleY && imageBateauY <= imageIleY + 200) {
                    System.out.println("Position : Ile une\n\n");
                    bateau.quitter();

                }else if(imageBateauX >= imageIle2X && imageBateauX <= imageIle2X + 100 && imageBateauY >= imageIle2Y && imageBateauY <= imageIle2Y + 200)
                {
                    bateau.quitter();
                    System.out.println("Position : Ile deux\n\n");

                }else{
                    bateau.quitter();
                    System.out.println("Position : En mer\n\n");
                    


                }
                
                if (x >= imageBateauX && x <= imageBateauX + 100 && y >= imageBateauY && y <= imageBateauY + 100) {
                    // Calculate offset between mouse click and image corner
                    dragOffsetX = x - imageBateauX;
                    dragOffsetY = y - imageBateauY;
                }
                
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int x = e.getX();
                int y = e.getY();
                // Check if boat is over an island and print the corresponding message
                if (imageBateauX >= imageIleX && imageBateauX <= imageIleX + 100 && imageBateauY >= imageIleY && imageBateauY <= imageIleY + 200) {
                    System.out.print("L'Ile une");
                    bateau.accoster(port1);
                    

                }else if(imageBateauX >= imageIle2X && imageBateauX <= imageIle2X + 100 && imageBateauY >= imageIle2Y && imageBateauY <= imageIle2Y + 200)
                {
                    System.out.print("L'Ile deux");
                    bateau.accoster(port2);
                    
                }else{

                    Port portClic = new Port(x, y);
                    System.out.print("La mer");
                    bateau.accoster(portClic);
                        
                
                }
                // Reset drag offset
                dragOffsetX = -1;
                dragOffsetY = -1;
                System.out.println("Coordonnées : (" + x + ", " + y + ")");
                bateau.distance();
                updatePortCountLabel();
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if (dragOffsetX != -1 && dragOffsetY != -1) {
                    // Update boat image position while dragging
                    imageBateauX = e.getX() - dragOffsetX;
                    imageBateauY = e.getY() - dragOffsetY;
                    panel.repaint(); // Repaint panel to update image position
                }
            }
        });
        /*panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if (dragOffsetX != -1 && dragOffsetY != -1) {
                    // Update the positions of all boats while dragging
                    for (Bateau boat : boats) {
                        boat.setX(boat.getX() + e.getX() - dragOffsetX);
                        boat.setY(boat.getY() + e.getY() - dragOffsetY);
                    }
                    panel.repaint(); // Repaint panel to update image positions
                }
            }
        });*/


        
         
        getContentPane().add(panel, BorderLayout.CENTER); //centre  


        setVisible(true);//affiche la fenetre
    }

    

    private void updatePortCountLabel() {
        portCountLabel.setText("Ile une : " + port1.getQuais().getQuaisOcc() + "                    Ile deux : " + port2.getQuais().getQuaisOcc()+" ");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {//garanti operation interface utilisateur sont execute
            new FenetreCoordonnees();
        });
    }
}
