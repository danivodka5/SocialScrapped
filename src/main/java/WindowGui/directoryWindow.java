package WindowGui;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class directoryWindow {
	// Atributos
	private JFileChooser jfw;
	private JFrame jf;

	public directoryWindow() {
		jfw = new JFileChooser();
		jfw.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jfw.setDialogTitle("Escoge un directorio");
		
		jf = new JFrame("panel");
		jf.setAlwaysOnTop(true);
	}
	public String getWindow() {
		String path = "";
		int value = jfw.showOpenDialog(jf);
		
		if ( value == JFileChooser.APPROVE_OPTION) {
			path = jfw.getSelectedFile().getAbsolutePath();
		}		
		return path;
	}
}

