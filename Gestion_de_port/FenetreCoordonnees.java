package Gestion_de_port;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class FenetreCoordonnees extends JFrame {

    private JPanel panel;
    private JLabel portCountLabel;
    
    // Offset between mouse click and rectangle corner
    // Declare arrays to store the drag offset for each image
    private int[] dragOffsetXTableau = new int[10];
    private int[] dragOffsetYTableau = new int[10];

    // Image positions
    private int imageIleX = 500;
    private int imageIleY = 100;
    private int imageIle2X = 500;
    private int imageIle2Y = 500;

    private int[] imageBateauXTableau = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
    private int[] imageBateauYTableau = {100, 150, 200, 250, 300, 350, 400, 450, 500, 550};

    private int draggingBoatIndex = -1; // Variable to store the index of the boat being dragged

    Port port1 = new Port(600, 400); // Define the coordinates of port1 relative of the center of Island 1
    Port port2 = new Port(200, 200); // Define the coordinates of port2 relative to the center of Island 2 
    
    Bateau bateau = new Bateau(); //Create one boat
    Bateau bateauTableau[]= new Bateau[10];
    private int numberPressedButton = 0;
    private int x1,x2,y1,y2; // coordinates to make distance the right way

    public FenetreCoordonnees() {
        setTitle("Gestion de Bateau");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        portCountLabel = new JLabel("Ile une = 0 /3                       Ile deux = 0 /3");
        getContentPane().setLayout(new BorderLayout());//cree la delimentation le panel
        getContentPane().add(portCountLabel, BorderLayout.NORTH);

        JButton addBoatButton = new JButton("Add Boat"); // Button to add a new boat
        addBoatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset the initial coordinates of the new boat
                imageBateauXTableau[numberPressedButton] = 100; // Set the initial X coordinate
                imageBateauYTableau[numberPressedButton] = 100 + (numberPressedButton * 50); // Set the initial Y coordinate

                // Number of time the button is pressed
                numberPressedButton++;
                
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
                ImageIcon[] BateauTableauIcon = new ImageIcon[10];
                Image[] imageBateauTableau = new Image[10];

                for (int i = 0; i < 10; i++) {
                    BateauTableauIcon[i] = new ImageIcon("D:\\LOCAL\\Java\\src_cours1\\Gestion_de_port\\Image Bateau\\Bateau" + i + ".png");
                    imageBateauTableau[i] = BateauTableauIcon[i].getImage();
                }

                // Draw the image at the panel's upper left corner
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

                //draw images
                g.drawImage(imageIle, imageIleX, imageIleY, 200, 200, this);
                g.drawImage(imageIle2, imageIle2X, imageIle2Y, 200, 200, this);

                for (int i = 0; i < numberPressedButton; i++) {
                    g.drawImage(imageBateauTableau[i], imageBateauXTableau[i], imageBateauYTableau[i], 100, 100, this);
                }
                                 
            }

            
        };
        
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                System.out.println("Voici le Port 1 :"+port1.getQuais().getQuaisOcc());
                int x = e.getX();
                int y = e.getY();
                x1=x;
                y1=y;
                
                System.out.println("First Coordinates: (" + x + ", " + y + ")");
                
                // Iterate through the array of image coordinates
                for (int i = 0; i < imageBateauXTableau.length; i++) {
                    System.out.println("\n\nBateau : "+i);
                    if (x >= imageBateauXTableau[i] && x <= imageBateauXTableau[i] + 100 && y >= imageBateauYTableau[i] && y <= imageBateauYTableau[i] + 200) {
                        // Store the index of the boat being clicked
                        draggingBoatIndex = i;
                    if(x >= imageBateauXTableau[i]  && x <= imageBateauXTableau[i]  + 100 && y  >= imageBateauYTableau[i] && y <= imageBateauYTableau[i] + 200){
                        // Store the index of the boat being dragged
                        

                        if (imageBateauXTableau[i] >= imageIleX && imageBateauXTableau[i] <= imageIleX + 100 && imageBateauYTableau[i] >= imageIleY && imageBateauYTableau[i] <= imageIleY + 200) {
                        System.out.println("Position : Ile une");
                        bateau.quitter(port1);
    
                        }else if(imageBateauXTableau[i] >= imageIle2X && imageBateauXTableau[i] <= imageIle2X + 100 && imageBateauYTableau[i] >= imageIle2Y && imageBateauYTableau[i] <= imageIle2Y + 100)
                        {
                        bateau.quitter(port2);
                        System.out.println("Position : Ile deux");
                        }else{
                        System.out.print("La mer");
                        
                        //bateau.quitter();
                        //System.out.println("Position : En mer\n\n");
                        }
                        if (x >= imageBateauXTableau[i] && x <= imageBateauXTableau[i] + 100 && y >= imageBateauYTableau[i] && y <= imageBateauYTableau[i] + 100) {
                            // Calculate offset between mouse click and image corner
                            dragOffsetXTableau[i] = x - imageBateauXTableau[i];
                            dragOffsetYTableau[i] = y - imageBateauYTableau[i];
                            // break out the loop to handle one image click
                            System.out.println("Index = " + i);
                            break;
                        }}
                }}
                //Update the Port number on screen
                updatePortCountLabel();
            }
        
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int x = e.getX();
                int y = e.getY();
                x2=x;
                y2=y;
                System.out.println("Last Coordinates: (" + x + ", " + y + ")");
                bateau.distance(x1, x2, y1, y2);

                 
                // Iterate through the array of image coordinates
                if (draggingBoatIndex != -1) {
                    
                    // Check if boat is over an island and print the corresponding message
                    if (imageBateauXTableau[draggingBoatIndex] >= imageIleX && imageBateauXTableau[draggingBoatIndex] <= imageIleX + 100 && imageBateauYTableau[draggingBoatIndex] >= imageIleY && imageBateauYTableau[draggingBoatIndex] <= imageIleY + 200) {
                        System.out.print("L'Ile une : " + draggingBoatIndex);
                        bateau.accoster(port1);
                        bateau.distance();
                        

                    }else if(imageBateauXTableau[draggingBoatIndex] >= imageIle2X && imageBateauXTableau[draggingBoatIndex] <= imageIle2X + 100 && imageBateauYTableau[draggingBoatIndex] >= imageIle2Y && imageBateauYTableau[draggingBoatIndex] <= imageIle2Y + 200)
                    {
                        System.out.print("L'Ile deux");
                        bateau.accoster(port2);
                        bateau.distance();
                    }else{
                        
                        System.out.print("La mer");
                        //bateau.quitter();
                        bateau.distance(x,y);
                        
                                               
                    }
                        if (dragOffsetXTableau[draggingBoatIndex] != -1 && dragOffsetYTableau[draggingBoatIndex] != -1) {
                            // Update boat image position while dragging
                            imageBateauXTableau[draggingBoatIndex] = x - dragOffsetXTableau[draggingBoatIndex];
                            imageBateauYTableau[draggingBoatIndex] = y - dragOffsetYTableau[draggingBoatIndex];
                            panel.repaint(); // Repaint panel to update image position
                            // Reset drag offsets after releasing the mouse
                            dragOffsetXTableau[draggingBoatIndex] = -1;
                            dragOffsetYTableau[draggingBoatIndex] = -1;
                            
                        }
                    }
                    /*
                    for (int i = 0; i < imageBateauXTableau.length; i++) {
                        // Check if the boat was being dragged and released within the boundaries of a port
                        if (dragOffsetXTableau[i] != -1 && dragOffsetYTableau[i] != -1) {
                            if (x >= imageIleX && x <= imageIleX + 200 && y >= imageIleY && y <= imageIleY + 200) {
                                bateau.accoster(port1);
                            } else if (x >= imageIle2X && x <= imageIle2X + 200 && y >= imageIle2Y && y <= imageIle2Y + 200) {
                                bateau.accoster(port2);
                            } else {
                                bateau.quitter();
                            }
                            
                            // Reset drag offsets after releasing the mouse
                            dragOffsetXTableau[i] = -1;
                            dragOffsetYTableau[i] = -1;
                            break;
                        }
                    }
                    */
                    //Update the Port number on screen
                    updatePortCountLabel();
                    System.out.println("------------------------------------------------------------------------");
            }
            
        });
        
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                // Iterate through the array of image coordinates
                for (int i = 0; i < imageBateauXTableau.length; i++) {
                    if (dragOffsetXTableau[i] != -1 && dragOffsetYTableau[i] != -1) {
                        // Update boat image position while dragging
                        imageBateauXTableau[i] = e.getX() - dragOffsetXTableau[i];
                        imageBateauYTableau[i] = e.getY() - dragOffsetYTableau[i];
                        panel.repaint(); // Repaint panel to update image position
                    }
                }
            }
        });
          
         
        getContentPane().add(panel, BorderLayout.CENTER); //centre  


        setVisible(true);//affiche la fenetre
    }

    

    private void updatePortCountLabel() {
        portCountLabel.setText("Ile une : " + port1.getQuais().getQuaisOcc() + "/3                    Ile deux : " + port2.getQuais().getQuaisOcc()+"/3 ");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {//garanti operation interface utilisateur sont execute
            new FenetreCoordonnees();
        });
    }
}
