package Daniel;

import javax.swing.*;
import java.awt.*;

public class VentanaTextos extends JFrame {

    public VentanaTextos() {
        super("Textos con Fuentes Diferentes");

        // Crear un panel para los textos
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Obtener todas las fuentes de texto disponibles en JAVA
        String[] fuentes = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        

        DefaultListModel<String> modelo = new DefaultListModel<String>();
        for (String fuente : fuentes) {
            modelo.addElement(fuente);
        }
        
        for (int i=0; i<modelo.getSize(); i++) {
        	JLabel text = new JLabel("TikTok "+modelo.getElementAt(i));
        	text.setFont(new Font(modelo.getElementAt(i),Font.PLAIN, 20));
        	panel.add(text);
        	panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        // Crear un JScrollPane y agregar el panel a él
        JScrollPane scrollPane = new JScrollPane(panel);

        // Agregar el JScrollPane al contenido de la ventana
        getContentPane().add(scrollPane);

        // Configurar el tamaño y la operación de cierre de la ventana
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Mostrar la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        // Crear y mostrar la ventana
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VentanaTextos();
            }
        });
    }
}
