package debug;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import debug.MainWelcomeGui2;

public class FrameTest extends JFrame {
	public FrameTest() {
	}
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public static void main(String[] args) {
      final JFrame frame = new JFrame("Main GUI");

      JButton addDeptButtonLaunchJFrame = new JButton(
            "Add a New Department, Launch JFrame");
      JButton addDeptButtonLaunchJDialog = new JButton(
            "Add a New Department, Launch JDialog");

      addDeptButtonLaunchJDialog.addActionListener(new LaunchJDialogListener(
            frame));
      addDeptButtonLaunchJFrame.addActionListener(new LaunchJFrameListener());

      JPanel panel = new JPanel();
      panel.add(addDeptButtonLaunchJDialog);
      panel.add(addDeptButtonLaunchJFrame);

      frame.getContentPane().add(panel);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);

   }
}