package debug;

import java.io.File;

import javax.swing.JDialog;

import gameFiles.Country;
import gameFiles.Map;
import graphics.CountrySelectorDailog;

public class TestEnviorment {

	public static void main(String[] args) {
		try {
			Map.setFile(new File(new File("").getAbsolutePath() + "\\csv\\countries.csv"));
			Map.fillCountries();
			CountrySelectorDailog dialog = new CountrySelectorDailog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			Country c = dialog.getCountry();
			System.out.println(c);
			dialog.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
