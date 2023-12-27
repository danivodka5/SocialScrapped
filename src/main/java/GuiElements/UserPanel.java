package GuiElements;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class UserPanel extends JPanel{
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(228,228,228));
        g.fillRect(0, getHeight()-1, getWidth(), getHeight()-1); // Dibuja el borde negro en la parte superior del panel

    }
    public UserPanel() {
        setBorder(BorderFactory.createEmptyBorder(0, 0, 1, 0)); // Establece un espacio de 1 píxel en la parte superior del panel
		setBackground(new Color(255, 255, 255));
    }
}
