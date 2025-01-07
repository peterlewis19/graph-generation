import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MyCanvas extends JComponent {

    private ArrayList<Node>[] allNeighbours;

    public MyCanvas(ArrayList<Node>[] allNeighbours){
        this.allNeighbours = allNeighbours;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set rendering hints for smooth lines
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set line color and thickness
        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(2)); // Line thickness = 2

        // Draw a line from point (50, 50) to point (300, 300)
        //g2d.drawLine(50, 50, 300, 300);

        //Scanner input  = new Scanner(System.in);

        for (ArrayList<Node> nodeAndNeighbour: allNeighbours){
            //go to nodeAndNeighbour.get(0)
            int startX = nodeAndNeighbour.get(0).getCoords()[0];
            int startY = nodeAndNeighbour.get(0).getCoords()[1];

            // draw a line to nodeAndNeighbour.get(1,2,3etc)

            //add a pause in each generation
            //String pause = input.nextLine();

            for (int i = 1; i < nodeAndNeighbour.size(); i++) {
                int endX = nodeAndNeighbour.get(i).getCoords()[0];
                int endY = nodeAndNeighbour.get(i).getCoords()[1];

                g2d.drawLine(startX, startY, endX, endY);
            }

        }
    }

    /*public void paint(Graphics g) {
        setForeground(Color.BLUE);
        setBackground(Color.ORANGE);

        //setBounds(50,50,500,500);

        /*for (ArrayList<Node> nodeAndNeighbour: allNeighbours){
            //go to nodeAndNeighbour.get(0)
            int startX = nodeAndNeighbour.get(0).getCoords()[0];
            int startY = nodeAndNeighbour.get(0).getCoords()[1];

            // draw a line to nodeAndNeighbour.get(1,2,3etc)

            for (int i = 1; i < nodeAndNeighbour.size(); i++) {
                int endX = nodeAndNeighbour.get(i).getCoords()[0];
                int endY = nodeAndNeighbour.get(i).getCoords()[1];

                g.drawLine(startX, startY, endX, endY);
            }

        }


    }
    */
}
