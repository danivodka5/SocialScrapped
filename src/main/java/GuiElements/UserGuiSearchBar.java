package GuiElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class UserGuiSearchBar extends JTextField {

	private Shape shape;
	private Color color = new Color(239,239,239);
	
	public UserGuiSearchBar() {
		// Hace el campo de texto transparente para ver el fondo
		setOpaque(false);
		
		setColumns(10);
		setFont(new Font("Arial", Font.PLAIN, 19));

		LineBorder b1 = new LineBorder(new Color(5,5,5),0);
		
		// Borde Interior
		Border b2 = BorderFactory.createEmptyBorder(0,9,0,0);
		
		// Suma de Bordes
		Border b3 = BorderFactory.createCompoundBorder(b1,b2);	
		setBorder(b3);
	}
	@Override
    protected void paintComponent(Graphics g) {
        g.setColor(color);	
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 11,11);
        super.paintComponent(g);
   }
	 
	 protected void paintBorder(Graphics g) {
	      g.setColor(color);
	      g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 11, 11);
	 }
	 public boolean contains(int x, int y) {
	      if (shape == null || !shape.getBounds().equals(getBounds())) {
	          shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 5, 5);
	      }
	      return shape.contains(x, y);
	   }
}
