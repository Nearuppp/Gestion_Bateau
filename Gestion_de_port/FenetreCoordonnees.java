package Gestion_de_port;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreCoordonnees extends JFrame {

    private JPanel panel;
    private JLabel portCountLabel;

    // Offset between mouse click and rectangle corner
    // Declare arrays to store the drag offset for each image
    private int[] dragOffsetXArray = new int[10];
    private int[] dragOffsetYArray = new int[10];
    private int draggingBoatIndex = -1; // Variable to store the index of the boat being dragged

    // Image positions
    private int imageIslandX = 500;
    private int imageIslandY = 100;
    private int imageIsland2X = 500;
    private int imageIsland2Y = 500;

    private int[] imageBoatXArray = { 100, 100, 100, 100, 100, 100, 100, 100, 100, 100 };
    private int[] imageBoatYArray = { 100, 150, 200, 250, 300, 350, 400, 450, 500, 550 };

    Port port1 = new Port(600, 400); // Define the coordinates of port1 relative of the center of Island 1
    Port port2 = new Port(200, 200); // Define the coordinates of port2 relative to the center of Island 2

    Boat boat = new Boat(); // Create one boat
    Boat boatTableau[] = new Boat[10];
    private int numberPressedButton = 0;
    private int x1, x2, y1, y2; // coordinates to make distance the right way

    public FenetreCoordonnees() {
        setTitle("Gestion de Bateau");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        portCountLabel = new JLabel("Ile une = 0 /3                       Ile deux = 0 /3");
        getContentPane().setLayout(new BorderLayout());// Returns the <code>contentPane</code> object for this frame.//
                                                       // This method changes layout-related information, and therefore,
                                                       // invalidates the component hierarchy.
        getContentPane().add(portCountLabel, BorderLayout.NORTH);

        JButton addBoatButton = new JButton("Add Boat"); // Button to add a new boat
        addBoatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset the initial coordinates of the new boat
                imageBoatXArray[numberPressedButton] = 100; // Set the initial X coordinate
                imageBoatYArray[numberPressedButton] = 100 + (numberPressedButton * 50); // Set the initial Y coordinate

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
                ImageIcon imageIconBackground = new ImageIcon(
                        "D:\\LOCAL\\Java\\src_cours1\\Gestion_de_port\\800x800.jpg");
                Image imageBackground = imageIconBackground.getImage();
                ImageIcon Island = new ImageIcon("D:\\LOCAL\\Java\\src_cours1\\Gestion_de_port\\Ile.png");
                Image imageIsland = Island.getImage();
                ImageIcon Island2 = new ImageIcon("D:\\LOCAL\\Java\\src_cours1\\Gestion_de_port\\Ile2.png");
                Image imageIsland2 = Island2.getImage();
                ImageIcon[] boatArrayIcon = new ImageIcon[10];
                Image[] imageBoatArray = new Image[10];

                for (int i = 0; i < 10; i++) {
                    boatArrayIcon[i] = new ImageIcon(
                            "D:\\LOCAL\\Java\\src_cours1\\Gestion_de_port\\Image Bateau\\Bateau" + i + ".png");
                    imageBoatArray[i] = boatArrayIcon[i].getImage();
                }

                // Draw the image at the panel's upper left corner
                g.drawImage(imageBackground, 0, 0, getWidth(), getHeight(), this);

                // Draw images
                g.drawImage(imageIsland, imageIslandX, imageIslandY, 200, 200, this);
                g.drawImage(imageIsland2, imageIsland2X, imageIsland2Y, 200, 200, this);

                for (int i = 0; i < numberPressedButton; i++) {
                    g.drawImage(imageBoatArray[i], imageBoatXArray[i], imageBoatYArray[i], 100, 100, this);
                }

            }

        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);// function inside Jframe Mouseadapter.class
                int x = e.getX();// Take the X coordinates of the window
                int y = e.getY();// Take the Y coordinates of the window
                x1 = x;
                y1 = y;
                System.out.println("\n------------------------------------------------------------------------");
                System.out.println("First Coordinates: (" + x + ", " + y + ")");

                // Iterate through the array of image coordinates
                for (int i = 0; i < imageBoatXArray.length; i++) {
                    // finding the boat that has been clicked on
                    if (x >= imageBoatXArray[i] && x <= imageBoatXArray[i] + 100 && y >= imageBoatYArray[i]
                            && y <= imageBoatYArray[i] + 200) {
                        System.out.println("Boat : " + i);
                        // Store the index of the boat whose being clicked on
                        draggingBoatIndex = i;
                        if (x >= imageBoatXArray[i] && x <= imageBoatXArray[i] + 100 && y >= imageBoatYArray[i]
                                && y <= imageBoatYArray[i] + 200) {

                            if (imageBoatXArray[i] >= imageIslandX && imageBoatXArray[i] <= imageIslandX + 100
                                    && imageBoatYArray[i] >= imageIslandY && imageBoatYArray[i] <= imageIslandY + 200) {
                                System.out.println("Position : Ile une");
                                boat.quitter(port1);

                            } else if (imageBoatXArray[i] >= imageIsland2X && imageBoatXArray[i] <= imageIsland2X + 100
                                    && imageBoatYArray[i] >= imageIsland2Y
                                    && imageBoatYArray[i] <= imageIsland2Y + 100) {
                                boat.quitter(port2);
                                System.out.println("Position : Ile deux ");
                            } else {
                                System.out.println("Position : En mer ");

                                // boat.quitter();
                                // System.out.println("Position : En mer\n\n");
                            }
                            if (x >= imageBoatXArray[i] && x <= imageBoatXArray[i] + 100 && y >= imageBoatYArray[i]
                                    && y <= imageBoatYArray[i] + 100) {
                                // Calculate offset between mouse click and image corner
                                dragOffsetXArray[i] = x - imageBoatXArray[i];
                                dragOffsetYArray[i] = y - imageBoatYArray[i];
                                // break out the loop to handle one image click
                                break;
                            }
                        }
                    }
                }
                // Update the Port number on screen
                updatePortCountLabel();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int x = e.getX();
                int y = e.getY();
                x2 = x;
                y2 = y;
                System.out.println("Last Coordinates: (" + x + ", " + y + ")");
                boat.distance(x1, x2, y1, y2);

                // Iterate through the array of image coordinates
                if (draggingBoatIndex != -1) {

                    // Check if boat is over an island and print the corresponding message
                    if (imageBoatXArray[draggingBoatIndex] >= imageIslandX
                            && imageBoatXArray[draggingBoatIndex] <= imageIslandX + 100
                            && imageBoatYArray[draggingBoatIndex] >= imageIslandY
                            && imageBoatYArray[draggingBoatIndex] <= imageIslandY + 200) {
                        System.out.print("Position 2 : L'Ile une : ");
                        boat.accoster(port1);
                        boat.distance();

                    } else if (imageBoatXArray[draggingBoatIndex] >= imageIsland2X
                            && imageBoatXArray[draggingBoatIndex] <= imageIsland2X + 100
                            && imageBoatYArray[draggingBoatIndex] >= imageIsland2Y
                            && imageBoatYArray[draggingBoatIndex] <= imageIsland2Y + 200) {
                        System.out.print("Position 2 : L'Ile deux");
                        boat.accoster(port2);
                        boat.distance();
                    } else {

                        System.out.print("Position 2 : En mer");
                        // Boat.quitter();
                        boat.distance(x, y);

                    }
                    if (dragOffsetXArray[draggingBoatIndex] != -1 && dragOffsetYArray[draggingBoatIndex] != -1) {
                        // Update boat image position while dragging
                        imageBoatXArray[draggingBoatIndex] = x - dragOffsetXArray[draggingBoatIndex];
                        imageBoatYArray[draggingBoatIndex] = y - dragOffsetYArray[draggingBoatIndex];
                        panel.repaint(); // Repaint panel to update image position
                        // Reset drag offsets after releasing the mouse
                        dragOffsetXArray[draggingBoatIndex] = -1;
                        dragOffsetYArray[draggingBoatIndex] = -1;

                    }
                }
                updatePortCountLabel();
                System.out.println("\n------------------------------------------------------------------------");
            }

        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {// dragging function
                super.mouseDragged(e);
                // Iterate through the array of image coordinates
                for (int i = 0; i < imageBoatXArray.length; i++) {
                    if (dragOffsetXArray[i] != -1 && dragOffsetYArray[i] != -1) {
                        // Update boat image position while dragging
                        imageBoatXArray[i] = e.getX() - dragOffsetXArray[i];
                        imageBoatYArray[i] = e.getY() - dragOffsetYArray[i];
                        panel.repaint(); // Repaint panel to update image position
                    }
                }
            }
        });

        getContentPane().add(panel, BorderLayout.CENTER); // centre

        setVisible(true);// affiche la fenetre
    }

    private void updatePortCountLabel() {
        portCountLabel.setText("Ile une : " + port1.getDocks().getdocksOcc() + "/3                    Ile deux : "
                + port2.getDocks().getdocksOcc() + "/3 ");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {// garanti operation interface utilisateur sont execute dans l'EDT
            new FenetreCoordonnees();
        });
    }
}
