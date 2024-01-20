package GuiElements;

import java.awt.Component;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PanelSlide extends JPanel {
	
	private Timer timer;
	private Component comExit;
	private Component comShow;
	private List<Component> list;
	
	private int currentShowing;
	private boolean animateRight;
	
	// Los tres puntos me permiten colocar varios argumentos de la misma variable ,lol.
	public void init(Component... com) {
		
		if (com.length > 0) {
			for (Component c : com) {
				list.add(c);
				c.setSize(getSize());
				c.setVisible(false);
				this.add(c);
				}
		}
		Component show = list.get(0);
		show.setVisible(true);
		show.setLocation(0, 0);
		
	}
	// https://www.youtube.com/watch?v=jkMEsPbghcE
	
	public static void main(String[] args){
	
		
	}
}
