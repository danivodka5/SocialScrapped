package GuiElements;	
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

public class Boton extends JButton{
	
	private Shape shape;
	private Color fcolor; 
	private Color lcolor;
	private Color rcolor;
	private boolean empty = true;
	private boolean mousee = false;
	private boolean blocked = false;
		
	public Boton(String title,Color fcolor,Color lcolor) {
		this.fcolor = fcolor;
		this.lcolor = lcolor;
		this.rcolor = new Color(40,121,226);
		
		setContentAreaFilled(false);	// Elimina Color relleno
		setRolloverEnabled(false);		// Elimina el glow del boton	
		setText(title);					// Establece el titulo del boton
		setFocusPainted(false);
		setBorderPainted(false);
		setForeground(Color.WHITE);
		
		addMouseListener(new MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	/*
		    	System.out.println("empty="+empty);
		    	System.out.println("mousee="+mousee);
		    	System.out.println("blocked="+blocked);
		    	System.out.println("-----------------");
		    	*/
		    	if (blocked) {
		    		repaint();
		    	} else {
			    	if (!empty) {
				    	mousee = true;
				        repaint();
			    	}
		    	}
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
			    if (!empty && !blocked) {
				    mousee = false;
				    repaint();
			    }
		    }
		});
		
	}	
	// Interior del JTextField
	@Override
    protected void paintComponent(Graphics g) {
	    if (blocked) {
	    	g.setColor(lcolor);
	    } else {
			if (empty) {
		        g.setColor(fcolor);
			} else {		
				if (mousee) {
					g.setColor(rcolor);
				}else {
					g.setColor(lcolor);	
				}
			}			
	    }		
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 18, 18);
        super.paintComponent(g);
   }
	
   // Borde del JTextField 	
   protected void paintBorder(Graphics g) {
        //g.setColor(getForeground());
	   
	    if (blocked) {
	    	g.setColor(lcolor);
	    } else {
			if (empty) {
		        g.setColor(fcolor);
			} else {		
				if (mousee) {
					g.setColor(rcolor);
				}else {
					g.setColor(lcolor);	
				}
			}			
	    }
        //g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 18, 18);
   }
   
   public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 5, 5);
        }
        return shape.contains(x, y);
   }
   
   public void setBooleanEmpty(boolean a) {
	   empty = a;
	   repaint();
   }
   public void setCursor(boolean c) {
	   if (c) {
	       setCursor(new Cursor(Cursor.HAND_CURSOR));
	   }else {
	       setCursor(null);
	   }
   }
   public void setBooleanBlocked(boolean b) {
	   blocked = b;
	   // repaint();¿?
   }
}
