package graphics;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import gameFiles.Board;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.Canvas;
import java.awt.Panel;
import javax.swing.JPanel;

public class Window {

	private JFrame frmWindowTest;
	private JLabel lblGameMap;
	private BufferedImage image = null;
	private ScrollImage scrollImage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					//Controller c = new Controller();
					//c.main(args);
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
		this.frmWindowTest.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWindowTest = new JFrame();
		frmWindowTest.setTitle("Window Test");
		frmWindowTest.setBounds(100, 100, 791, 580);
		frmWindowTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		scrollImage = new ScrollImage();
		frmWindowTest.getContentPane().add(scrollImage, BorderLayout.CENTER);
		
		String absPath = new File("").getAbsolutePath();
		
	}
	
	public void rePaintAll(Board b) {
		frmWindowTest.repaint();
		scrollImage.rePaintAll(b);
		
	}
}