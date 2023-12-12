package GuiElements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

public class BotonPanel extends JButton{

	private boolean mouse = false;
	
	public BotonPanel() {
		setContentAreaFilled(false);	// Elimina Color relleno
		setRolloverEnabled(false);		// Elimina el glow del boton	
		setFocusPainted(false);
		setBorderPainted(false);
		setForeground(Color.WHITE);
		addMouseListener(new MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	mouse = true;
		    	repaint();
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	mouse = false;
		    	repaint();
		    }
		});
	}
	@Override
    protected void paintComponent(Graphics g) {
        if (mouse) {
        	g.setColor(new Color(239,239,239));
        } else {
        	g.setColor(new Color(255,255,255));
        }
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20,20);
        super.paintComponent(g);
   }
   @Override
   protected void paintBorder(Graphics g) {
	   g.setColor(new Color(219,219,219));
	   Graphics2D g2 = (Graphics2D) g;
	   g2.setStroke(new BasicStroke(2));
	   g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20,20);
	   //g2.setStroke(new BasicStroke(1));
       //g.drawRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
   }
}
