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

public class InstagramTextField extends JTextField {
	
	private Shape shape;
	private boolean focus;
	
	// Constructor
	public InstagramTextField() {

		//setOpaque(false); //?
		setFont(new Font("Arial", Font.PLAIN, 19));
		setColumns(20);
		
		// Borde Exterior
		LineBorder b1 = new LineBorder(new Color(226,226,226),1);
		
		// Borde Interior
		Border b2 = BorderFactory.createEmptyBorder(0,9,0,0);
		
		// Suma de Bordes
		Border b3 = BorderFactory.createCompoundBorder(b1,b2);
			
		//  setBorder(new LineBorder(new Color(226,226,226),1)); Borde depecrated
		setBorder(b3);
		
		setBackground(new Color(250,250,250));
				
		addFocusListener(new java.awt.event.FocusAdapter() {
		        public void focusGained(java.awt.event.FocusEvent evt) {
		            focus = true;
		            repaint();
		        }
		        public void focusLost(java.awt.event.FocusEvent evt) {
		            focus = false;
		            repaint();
		        }
		  });
	}
	// Interior del JTextField
	@Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());	
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 5, 5);
        super.paintComponent(g);
   }
	
   // Borde del JTextField 	
   protected void paintBorder(Graphics g) {
        //g.setColor(getForeground());
		if (focus) {
	        g.setColor(new Color(168,168,168));
		} else {
			g.setColor(new Color(226,226,226));
		}		
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 5, 5);
   }
   
   public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 5, 5);
        }
        return shape.contains(x, y);
   }
}
