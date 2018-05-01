package gameFiles;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Window {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		ImagePanel imagePanel = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\usa_tokens\\usaControl1.png");
		imagePanel.setBounds(11, 11, 44, 44);
		frame.getContentPane().add(imagePanel);
		
		ImagePanel imagePanel_1 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\usa_tokens\\usaControl2.png");
		imagePanel_1.setBounds(81, 11, 44, 44);
		frame.getContentPane().add(imagePanel_1);
		
		ImagePanel imagePanel_2 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\usa_tokens\\usaControl3.png");
		imagePanel_2.setBounds(151, 11, 44, 44);
		frame.getContentPane().add(imagePanel_2);
		
		ImagePanel imagePanel_3 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\usa_tokens\\usaControl4.png");
		imagePanel_3.setBounds(151, 83, 44, 44);
		frame.getContentPane().add(imagePanel_3);
		
		ImagePanel imagePanel_4 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\usa_tokens\\usaControl5.png");
		imagePanel_4.setBounds(81, 83, 44, 44);
		frame.getContentPane().add(imagePanel_4);
		
		ImagePanel imagePanel_5 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\usa_tokens\\usaControl6.png");
		imagePanel_5.setBounds(11, 83, 44, 44);
		frame.getContentPane().add(imagePanel_5);
		
		ImagePanel imagePanel_6 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\usa_tokens\\usaControl7.png");
		imagePanel_6.setBounds(151, 151, 44, 44);
		frame.getContentPane().add(imagePanel_6);
		
		ImagePanel imagePanel_7 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\usa_tokens\\usaControl8.png");
		imagePanel_7.setBounds(81, 151, 44, 44);
		frame.getContentPane().add(imagePanel_7);
		
		ImagePanel imagePanel_8 = new ImagePanel("C:\\Users\\Mark\\git\\sunset-melee\\img\\usa_tokens\\usaInfluence1.png");
		imagePanel_8.setBounds(11, 151, 44, 44);
		frame.getContentPane().add(imagePanel_8);
	}
}
