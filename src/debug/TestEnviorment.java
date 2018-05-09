package debug;

import java.io.File;

import javax.swing.JDialog;

import gameFiles.Country;
import gameFiles.Map;

public class TestEnviorment {

	public static void main(String[] args) {
		try {
			Map.setFile(new File(new File("").getAbsolutePath() + "\\csv\\countries.csv"));
			Map.fillCountries();
			FrameTest dialog = new FrameTest();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
