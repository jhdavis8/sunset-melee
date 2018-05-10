package debug;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * MainWelcomeGui2.java: for comparing dialogs and frames 
 * @author Josh Davis
 * @author Mark Wolgin
 *
 */
public class MainWelcomeGui2 {
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

		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
}
/**
 * Dialog listener class
 * @author Josh Davis
 * @author Mark Wolgin
 */
class LaunchJDialogListener implements ActionListener {
	JDialog dialog;

	/**
	 * Launches dialog listener
	 * @param parentFrame the dialog's parent
	 */
	public LaunchJDialogListener(JFrame parentFrame) {
		JButton doneButton = new JButton(new AbstractAction("Done") {
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(100, 100));
		panel.add(doneButton);

		dialog = new JDialog(parentFrame, "Dialog", true);
		dialog.add(panel);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("called before setting dialog visible");
		dialog.setVisible(true);
		System.out
		.println("called after setting dialog visible. Note that this line doesn't show until the dialog disappears");
	}
}

/**
 * JFrame object
 * @author Josh Davis
 * @author Mark Wolgin
 */
class LaunchJFrameListener implements ActionListener {
	JFrame frame;

	/**
	 * Launches the JFrame
	 */
	public LaunchJFrameListener() {
		JButton doneButton = new JButton(new AbstractAction("Done") {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(100, 100));
		panel.add(doneButton);

		frame = new JFrame("JFrame");
		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("called before setting frame visible");
		frame.setVisible(true);
		System.out
		.println("called after setting frame visible.  Note that this line shows up immediately.");
	}
}