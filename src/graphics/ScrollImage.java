package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import gameFiles.Board;
import gameFiles.Country;
import gameFiles.Location;
import gameFiles.Map;
import gameFiles.Side;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JMenu;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.Choice;

public class ScrollImage extends JPanel {
    private static final long serialVersionUID = 1L;
    private BufferedImage image;
    private JPanel canvas;
    private final JLabel lblActionRound = new JLabel("");
    private JLabel lblDefcon = new JLabel("");
    private Side actionRoundSide = Side.USSR;
    private int actionRoundNum = 1;
    private int defconNum = 5;
    boolean isTrue = false;
    
    private ArrayList<JLabel> influenceTokens;

    public ScrollImage() {
        try {
            this.image = ImageIO.read(new File("img\\gameMap.jpg"));
        } catch(IOException ex) {
            Logger.getLogger(ScrollImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        influenceTokens = new ArrayList<JLabel>();
        

        this.canvas = new JPanel() {
            private static final long serialVersionUID = 1L;
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };
        //canvas.add(new JButton("Currently I do nothing"));
        canvas.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        JScrollPane sp = new JScrollPane(canvas);
        canvas.setLayout(null);
        
        
        lblActionRound.setBounds(448, 103, 41, 42);
        lblActionRound.setVerticalAlignment(SwingConstants.TOP);
        canvas.add(lblActionRound);
        lblActionRound.setToolTipText("Current action round");
        lblActionRound.setIcon(new ImageIcon("img\\board_tokens\\ussr_action_round.png"));

        lblDefcon.setBounds(getXValueDefcon(), 1255, 42, 41);
        /*
        lblDefcon.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mousePressed(MouseEvent e) {
        		if (defconNum == 1) {
        			lblDefcon.setBounds(740, 1255, 42, 41);
        			defconNum = 5;
        		} else {
        			int newX = lblDefcon.getX() + 76;
        			lblDefcon.setBounds(newX, 1255, 42, 41);
        			defconNum--;
        		}
        	}
        });
        
        */
        lblDefcon.setIcon(new ImageIcon("img\\board_tokens\\defcon_status.png"));
        lblDefcon.setVerticalAlignment(SwingConstants.TOP);
        lblDefcon.setToolTipText("Current action round");
        lblDefcon.setBounds(740, 1255, 42, 41);
        canvas.add(lblDefcon);
        

        setLayout(new BorderLayout());
        add(sp, BorderLayout.CENTER);
    }
    
    private void actionRoundAdvance() {
		if (actionRoundNum == 8 && actionRoundSide == Side.USA) {
			lblActionRound.setBounds(448, 103, 41, 42);
			lblActionRound.setIcon(new ImageIcon("img\\board_tokens\\ussr_action_round.png"));
			actionRoundSide = Side.USSR;
		}
		else if (actionRoundSide == Side.USA) {
			lblActionRound.setIcon(new ImageIcon("img\\board_tokens\\ussr_action_round.png"));
			actionRoundSide = Side.USSR;
			int newX = lblActionRound.getX() + 56;
			lblActionRound.setBounds(newX, 103, 41, 42);
		} else {
			lblActionRound.setIcon(new ImageIcon("img\\board_tokens\\usa_action_round.png"));
			actionRoundSide = Side.USA;
		}
	}
    
    
    private int getXValueDefcon() {
    	return 740 + (5 - defconNum) *76;
	}

	private void repaintAllInfluence() {
		for (JLabel comp : influenceTokens) {
			canvas.remove(comp);
		}
		influenceTokens.removeAll(influenceTokens);
		influenceTokens = new ArrayList<JLabel>();
		
		for (int k = 0; k < Map.getWorld().size(); k ++) {
			Country c = Map.getWorld().get(k);
			if (c.userHasInfluence(Side.USA) && (c.userHasControl(Side.USA))) {
				JLabel jL = new JLabel("");
				String f = "img\\usa_tokens\\usaControl" + c.getUSInfluence() + ".png";
				jL.setIcon(new ImageIcon(f));
				
				Location l = Location.getImageLocation(c.getISO(), Side.USA);
				jL.setBounds(l.getX(), l.getY(), 45, 45);
				
				influenceTokens.add(jL);
			}
			else if (c.userHasInfluence(Side.USA) && !(c.userHasControl(Side.USA))) {
				JLabel jL = new JLabel("");
				String f = "img\\usa_tokens\\usaInfluence" + c.getUSInfluence() + ".png";
				jL.setIcon(new ImageIcon(f));
	
				Location l = Location.getImageLocation(c.getISO(), Side.USA);
				jL.setBounds(l.getX(), l.getY(), 45, 45);
				
				influenceTokens.add(jL);
			}
			else if (c.userHasInfluence(Side.USSR) && (c.userHasControl(Side.USSR))) {
				JLabel jL = new JLabel("");
				String f = "img\\ussr_tokens\\ussrControl" + c.getUSSRInfluence() + ".png";
				jL.setIcon(new ImageIcon(f));
				
				Location l = Location.getImageLocation(c.getISO(), Side.USSR);
				jL.setBounds(l.getX(), l.getY(), 45, 45);
				
				influenceTokens.add(jL);
			}
			else if (c.userHasInfluence(Side.USSR) && !(c.userHasControl(Side.USSR))) {
				JLabel jL = new JLabel("");
				String f = "img\\ussr_tokens\\ussrInfluence" + c.getUSSRInfluence() + ".png";
				jL.setIcon(new ImageIcon(f));
				
				Location l = Location.getImageLocation(c.getISO(), Side.USSR);
				jL.setBounds(l.getX(), l.getY(), 45, 45);
				
				influenceTokens.add(jL);
			}
		}
		
		for (JLabel jL : influenceTokens) {
			canvas.add(jL);
		}
		
		canvas.revalidate();
		canvas.repaint();
	}

	public void setDefcon(int d) {
    	defconNum = d;
    }
	
	public void rePaintAll(Board b) {
		defconNum = b.getDefcon();
		lblDefcon.setBounds(getXValueDefcon(), 1255, 42, 41);
		lblDefcon.repaint();
		actionRoundNum = b.getActionRound();
		actionRoundAdvance();
		repaintAllInfluence();
		canvas.revalidate();
		canvas.repaint();
	}
}