package gameFiles;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class Window {

	private JFrame frmWindowTest;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					Controller c = new Controller();
					c.main(args);
					window.frmWindowTest.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWindowTest = new JFrame();
		frmWindowTest.setTitle("Window Test");
		frmWindowTest.setBounds(100, 100, 2500, 1950);
		frmWindowTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWindowTest.getContentPane().setLayout(null);
		
		ImagePanel usa1 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\usa_tokens\\usaControl1.png");
		usa1.setBounds(11, 11, 44, 44);
		frmWindowTest.getContentPane().add(usa1);
		
		ImagePanel usa2 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\usa_tokens\\usaControl2.png");
		usa2.setBounds(81, 11, 44, 44);
		frmWindowTest.getContentPane().add(usa2);
		
		ImagePanel usa3 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\usa_tokens\\usaControl3.png");
		usa3.setBounds(151, 11, 44, 44);
		frmWindowTest.getContentPane().add(usa3);
		
		ImagePanel usa4 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\usa_tokens\\usaControl4.png");
		usa4.setBounds(11, 83, 44, 44);
		frmWindowTest.getContentPane().add(usa4);
		
		ImagePanel usa5 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\usa_tokens\\usaControl5.png");
		usa5.setBounds(81, 83, 44, 44);
		frmWindowTest.getContentPane().add(usa5);
		
		ImagePanel usa6 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\usa_tokens\\usaControl6.png");
		usa6.setBounds(151, 83, 44, 44);
		frmWindowTest.getContentPane().add(usa6);
		
		ImagePanel usa7 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\usa_tokens\\usaControl7.png");
		usa7.setBounds(11, 151, 44, 44);
		frmWindowTest.getContentPane().add(usa7);
		
		ImagePanel usa8 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\usa_tokens\\usaControl8.png");
		usa8.setBounds(81, 151, 44, 44);
		frmWindowTest.getContentPane().add(usa8);
		
		ImagePanel usaInf1 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\usa_tokens\\usaInfluence1.png");
		usaInf1.setBounds(221, 11, 44, 44);
		frmWindowTest.getContentPane().add(usaInf1);
		
		ImagePanel usaInf2 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\usa_tokens\\usaInfluence2.png");
		usaInf2.setBounds(291, 11, 44, 44);
		frmWindowTest.getContentPane().add(usaInf2);
		
		ImagePanel usaInf3 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\usa_tokens\\usaInfluence3.png");
		usaInf3.setBounds(361, 11, 44, 44);
		frmWindowTest.getContentPane().add(usaInf3);
		
		ImagePanel usaInf4 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\usa_tokens\\usaInfluence4.png");
		usaInf4.setBounds(221, 83, 44, 44);
		frmWindowTest.getContentPane().add(usaInf4);
		
		ImagePanel usaInf5 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\usa_tokens\\usaInfluence5.png");
		usaInf5.setBounds(291, 83, 44, 44);
		frmWindowTest.getContentPane().add(usaInf5);
		
		ImagePanel usaInf6 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\usa_tokens\\usaInfluence6.png");
		usaInf6.setBounds(361, 83, 44, 44);
		frmWindowTest.getContentPane().add(usaInf6);
		
		ImagePanel usaInf7 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\usa_tokens\\usaInfluence7.png");
		usaInf7.setBounds(291, 151, 44, 44);
		frmWindowTest.getContentPane().add(usaInf7);
		
		ImagePanel usaInf8 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\usa_tokens\\usaInfluence8.png");
		usaInf8.setBounds(361, 151, 44, 44);
		frmWindowTest.getContentPane().add(usaInf8);
		
		ImagePanel imagePanel_16 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\board_tokens\\turn.png");
		imagePanel_16.setBounds(151, 151, 44, 44);
		frmWindowTest.getContentPane().add(imagePanel_16);
		
		ImagePanel imagePanel_17 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\board_tokens\\vp.png");
		imagePanel_17.setBounds(221, 151, 44, 44);
		frmWindowTest.getContentPane().add(imagePanel_17);
		
		ImagePanel imagePanel = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\gameMap.jpg");
		imagePanel.setBounds(0, 214, 2447, 1585);
		frmWindowTest.getContentPane().add(imagePanel);
		frmWindowTest.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{frmWindowTest.getContentPane(), usa1, usa2, usa3, usa4, usa5, usa6, usa7, usa8, usaInf1, usaInf2, usaInf3, usaInf4, usaInf5, usaInf6, usaInf7, usaInf8, imagePanel_16, imagePanel_17}));
	}
}
