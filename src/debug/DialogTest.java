package debug;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DialogTest extends JDialog implements ActionListener{

	/**
	 * Create the dialog.
	 */
	public DialogTest(FrameTest parent) {
		JButton doneButton = new JButton(new AbstractAction("Done") {
	         public void actionPerformed(ActionEvent e) {
	            dispose();
	         }
	      });

	      JPanel panel = new JPanel();
	      panel.setPreferredSize(new Dimension(100, 100));
	      panel.add(doneButton);

	      new JDialog(parent, "Dialog", true);
	      this.add(panel);
	      this.pack();
	      this.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("H");
		this.dispose();
		System.out.println("ello");
		
	}
	
	

}
