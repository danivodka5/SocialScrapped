package Gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import GuiElements.BotonPanel;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class UserGui {

	private JFrame frame;
	private ImageIcon icon1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserGui window = new UserGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setBounds(100, 100, 384, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final JLabel labelZoom = new JLabel("");
		labelZoom.setBounds(21, 340, 35, 34);
		
		icon1 = new ImageIcon(InstagramLoginGui.class.getResource("/Images/ZoomInsta2.png"));
		
		ImageIcon temp1 = icon1;
		temp1.setImage(temp1.getImage().getScaledInstance(labelZoom.getWidth(), labelZoom.getHeight(), Image.SCALE_SMOOTH));
		labelZoom.setIcon(temp1);
		frame.getContentPane().add(labelZoom);
	
		BotonPanel bp = new BotonPanel();
		bp.setBounds(19, 339, 40, 37);
		
		bp.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	labelZoom.setBounds(0, 0, 300, 300);
		    	
		    	ImageIcon temp2 = icon1;
		    	temp2.setImage(temp2.getImage().getScaledInstance(labelZoom.getWidth(), labelZoom.getHeight(), Image.SCALE_SMOOTH));
		    	/*
		    	Image image = loading.getImage().getScaledInstance(labelZoom.getWidth()+2, labelZoom.getHeight()+2, Image.SCALE_SMOOTH);
		    	ii = new ImageIcon(image);
		    	*/
		    	
		    	labelZoom.setIcon(icon1);

		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	labelZoom.setBounds(20, 341, 35, 34);
		    }
		});
		frame.getContentPane().add(bp);
		
		
		
		UserPanel userpanel = new UserPanel();
		userpanel.setBounds(0, 331, 370, 53);
		userpanel.setLayout(null);
		frame.getContentPane().add(userpanel);

		

	}
}
