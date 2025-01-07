import javax.swing.*;
import java.awt.*;

public class LinePanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Set color for the lines
        g.setColor(Color.BLUE);
        setBackground(Color.black);

        g.drawLine(1,1,300,300);
    }
}
