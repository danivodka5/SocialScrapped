package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;

public class CRUD {
	
	private JPanel mainPanel;

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CRUD window = new CRUD();
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
	public CRUD() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 671, 734);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Panel principal
		mainPanel = new JPanel();
		
		//Layout
		mainPanel.setLayout(new GridLayout(3, 0, 0, 0));
		
		mainPanel.setBackground(new Color(245, 245, 245));
		mainPanel.setBounds(0, 0, 657, 697);
		frame.getContentPane().add(mainPanel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10,10,10,10));
		
		
		panel.setBackground(new Color(0, 64, 64));
		panel.setForeground(new Color(64, 128, 128));
		mainPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel jpcuser = new JPanel();
		jpcuser.setBorder(new TitledBorder(null, "Create User", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton jb = new JButton("hola");
		JButton jb2 = new JButton("hola");
		JButton jb3 = new JButton("hola");
		JButton jb4 = new JButton("hola");
		
		GridBagConstraints gbc = new GridBagConstraints();
		jpcuser.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		jpcuser.add(jb, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		jpcuser.add(jb2, gbc);
		
		
		panel.add(jpcuser, BorderLayout.CENTER);

		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(64, 0, 0));
		mainPanel.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		mainPanel.add(panel_2);
		
		// 
		
	}
}
