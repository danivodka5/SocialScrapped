package Gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class UserPanel extends JPanel{
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(228,228,228));
        g.fillRect(0, 0, getWidth(), 1); // Dibuja el borde negro en la parte superior del panel
    }
    
    public UserPanel() {
        setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0)); // Establece un espacio de 1 píxel en la parte superior del panel
		setBackground(new Color(255, 255, 255));
    }
}
