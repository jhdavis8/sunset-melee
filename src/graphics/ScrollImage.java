package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
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

/**
 * Holds all the informaion and annimantions for the visual board
 * 
 * @author Mark Wolgin
 * @author Josh Davis
 *
 */
public class ScrollImage extends JPanel {
    private static final long serialVersionUID = 1L;
    /**
     * The Beffered Image of Board
     */
    private BufferedImage image;
    /**
     * The canvas all components are added to
     */
    private JPanel canvas;
    /**
     * Action Round token
     */
    private final JLabel lblActionRound = new JLabel("");
    /**
     * Defcon token
     */
    private JLabel lblDefcon = new JLabel("");
    /**
     * Defcon status
     */
    private int defconNum = 5;
    /**
     * isTrue???
     */
    boolean isTrue = false;
    
    /**
     * The list of all country tokens
     */
    private ArrayList<JLabel> influenceTokens;
    /**
     * The Turn Tolken
     */
    private final JLabel lblTurn = new JLabel("");
    
    /**
     * Constant for dimension scaling 
     */
    private final float SCALE;
    

    /**
     * Creates a new ScrollImage, Uses the gameMap.jpg
     */
    public ScrollImage() {
        this.image = resize(new File("img\\gameMap.jpg"));
        
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
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		SCALE = screenSize.width / image.getWidth();
		
        // TODO get rid of magic numbers
        lblActionRound.setBounds((int)SCALE*448, (int)SCALE*103, (int)SCALE*41, (int)SCALE*42);
        lblActionRound.setVerticalAlignment(SwingConstants.TOP);

        lblActionRound.setToolTipText("Current action round");
        lblActionRound.setIcon(new ImageIcon("img\\board_tokens\\ussr_action_round.png"));
        canvas.add(lblActionRound);
        lblDefcon.setBounds((int)SCALE*getXValueDefcon(), (int)SCALE*1255, (int)SCALE*42, (int)SCALE*41);

        lblDefcon.setIcon(new ImageIcon("img\\board_tokens\\defcon_status.png"));
        lblDefcon.setVerticalAlignment(SwingConstants.TOP);
        lblDefcon.setToolTipText("Current defcon");
        lblDefcon.setBounds((int)SCALE*740, (int)SCALE*1255, (int)SCALE*42, (int)SCALE*41);
        canvas.add(lblDefcon);
        lblTurn.setVerticalAlignment(SwingConstants.TOP);
        lblTurn.setToolTipText("Current turn");
        lblTurn.setBounds((int)SCALE*1690, (int)SCALE*96, (int)SCALE*41, (int)SCALE*42);
        lblTurn.setIcon(new ImageIcon("img\\board_tokens\\turn.png"));
        canvas.add(lblTurn);
        

        setLayout(new BorderLayout());
        add(sp, BorderLayout.CENTER);
    }
    
    /**
     * Advances the Action Round Token
     * @param b The Current Board
     */
    private void actionRoundAdvance(Board b) {
    	int actionRoundNum = b.getActionRound();
    	Side actionRoundSide = b.getCurrentPlayer();
    	System.out.println(actionRoundNum + " " + actionRoundSide);
    	if (actionRoundSide == Side.USA) {
    		lblActionRound.setIcon(new ImageIcon("img\\board_tokens\\usa_action_round.png"));
    	} else {
    		lblActionRound.setIcon(new ImageIcon("img\\board_tokens\\ussr_action_round.png"));
    	}
    	lblActionRound.setBounds((int)SCALE*(448 + (56 * actionRoundNum)), (int)SCALE*103, (int)SCALE*41, (int)SCALE*42);
	}
    
    /**
     * Advances the current turn token
     * @param b Current Board
     */
    private void turnAdvance(Board b) {
    	int turnNum = b.getTurn();
    	System.out.println(turnNum);
    	lblTurn.setBounds((int)SCALE*(1690 + (72 * turnNum)), (int)SCALE*96, (int)SCALE*41, (int)SCALE*42);
	}
    
    /**
     * Calculates the new defcon token location
     * @return int
     */
    private int getXValueDefcon() {
    	return 740 + (5 - defconNum) *76;
	}

	/**
	 * Repaints all elements on the board
	 * 
	 * NOTE: Regens all tokens each run.
	 */
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
				jL.setIcon(new ImageIcon(resize(new File(f))));
				
				Location l = Location.getImageLocation(c.getISO(), Side.USA);
				jL.setBounds((int)SCALE*l.getX(), (int)SCALE*l.getY(), (int)SCALE*45, (int)SCALE*45);
				
				influenceTokens.add(jL);
			}
			else if (c.userHasInfluence(Side.USA) && !(c.userHasControl(Side.USA))) {
				JLabel jL = new JLabel("");
				String f = "img\\usa_tokens\\usaInfluence" + c.getUSInfluence() + ".png";
				jL.setIcon(new ImageIcon(resize(new File(f))));
	
				Location l = Location.getImageLocation(c.getISO(), Side.USA);
				jL.setBounds((int)SCALE*l.getX(), (int)SCALE*l.getY(), (int)SCALE*45, (int)SCALE*45);
				
				influenceTokens.add(jL);
			}
			if (c.userHasInfluence(Side.USSR) && (c.userHasControl(Side.USSR))) {
				JLabel jL = new JLabel("");
				String f = "img\\ussr_tokens\\ussrControl" + c.getUSSRInfluence() + ".png";
				jL.setIcon(new ImageIcon(resize(new File(f))));
				
				Location l = Location.getImageLocation(c.getISO(), Side.USSR);
				jL.setBounds((int)SCALE*l.getX(), (int)SCALE*l.getY(), (int)SCALE*45, (int)SCALE*45);
				
				influenceTokens.add(jL);
			}
			else if (c.userHasInfluence(Side.USSR) && !(c.userHasControl(Side.USSR))) {
				JLabel jL = new JLabel("");
				String f = "img\\ussr_tokens\\ussrInfluence" + c.getUSSRInfluence() + ".png";
				jL.setIcon(new ImageIcon(resize(new File(f))));
				
				Location l = Location.getImageLocation(c.getISO(), Side.USSR);
				jL.setBounds((int)SCALE*l.getX(), (int)SCALE*l.getY(), (int)SCALE*45, (int)SCALE*45);
				
				influenceTokens.add(jL);
			}
		}
		
		for (JLabel jL : influenceTokens) {
			canvas.add(jL);
		}
		
		canvas.revalidate();
		canvas.repaint();
	}

	/**
	 * Sets the current defcon
	 * @param d int Current defcon
	 */
	public void setDefcon(int d) {
    	defconNum = d;
    }
	
	/**
	 * Resizes images
	 */
	private BufferedImage resize(File f) {
		try {
			BufferedImage oldImage = ImageIO.read(f);
			BufferedImage newImage = new BufferedImage((int)SCALE * oldImage.getWidth(), (int)SCALE * oldImage.getHeight(), oldImage.getType());
			Graphics2D g2d = newImage.createGraphics();
			g2d.drawImage(oldImage, 0, 0, (int)SCALE * oldImage.getWidth(), (int)SCALE * oldImage.getHeight(), null);
			g2d.dispose();
			return newImage;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Calls repaintAllInfluence()
	 * Handles more of the math
	 * @param b Current Board
	 */
	public void rePaintAll(Board b) {
		defconNum = b.getDefcon();
		lblDefcon.setBounds(getXValueDefcon(), 1255, 42, 41);
		lblDefcon.repaint();
		actionRoundAdvance(b);
		turnAdvance(b);
		repaintAllInfluence();
		canvas.revalidate();
		canvas.repaint();
	}
}