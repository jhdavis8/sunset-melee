package debug;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gameFiles.*;

import graphics.*;

public class TestEnviorment {

	public static void main(String[] args) {
		
		Board game = new Board(); 
		Controller.callInitialize(game);
		Effects.setCurrentBoard(game);
//		UIGraphic UI = new UIGraphic(); // TODO unify constructor signatures
		UIText UI = new UIText(game);
		Effects.setUI(UI);
		game.dealCards();
		
		String[] hand = new String[game.getPlayer(Side.USA).getHand().size()];
		for (int k = 0; k < hand.length; k ++) {
			hand[k] = game.getPlayer(Side.USA).getHand().get(k).name;
		}
		
		try {
			Map.setFile(new File(new File("").getAbsolutePath() + "\\csv\\countries.csv"));
			Map.fillCountries();
      
			Object input = JOptionPane.showInputDialog(null, "Pick", "Pick1", JOptionPane.QUESTION_MESSAGE, null, hand, hand[0]);
			System.out.println(Deck.getCard((String)input));
			
			ArrayList<Object> t = new ArrayList<Object>();
			t.addAll(game.getPlayer(Side.USSR).getHand());
			
			System.out.println(Window.popupDropDownWindow("Test", t));
			System.out.println(Window.popupButtonWindow("Testers", t));
      
      //I have all the power
      
	

			FrameTest dialog = new FrameTest();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
