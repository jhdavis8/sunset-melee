package debug;

import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gameFiles.Country;
import gameFiles.Map;

public class TestEnviorment {

	public static void main(String[] args) {
		try {
			Map.setFile(new File(new File("").getAbsolutePath() + "\\csv\\countries.csv"));
			Map.fillCountries();
			JFrame frame = new JFrame();
			Object[] options = { "OK", "CANCEL" };
			JOptionPane.showOptionDialog(frame, "Click OK to continue", "Warning",
			             JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
			             null, options, options[0]);
			frame.setVisible(true);
			FrameTest dialog = new FrameTest();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
