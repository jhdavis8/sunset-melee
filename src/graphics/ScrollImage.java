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
    private float SCALE;
    

    /**
     * Creates a new ScrollImage, Uses the gameMap.jpg
     */
    public ScrollImage() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		BufferedImage tImage = null;
		String path = new File("").getAbsolutePath();
		try {
			tImage = ImageIO.read(new File(path + "\\img\\gameMap.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(screenSize.width); 
		System.out.println(tImage.getWidth());
		SCALE =  (float) (screenSize.width - 300 ) / (float) tImage.getWidth();
		System.out.println(SCALE); 
        this.image = resize(new File(path + "\\img\\gameMap.jpg"));
        
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
        System.out.println(path + "\\img\\gameMap.jpg");
        canvas.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        JScrollPane sp = new JScrollPane(canvas);
        canvas.setLayout(null);


		
        // TODO get rid of magic numbers
        lblActionRound.setBounds(this.scaleCord(448), this.scaleCord(103), this.scaleCord(41), this.scaleCord(42));
        lblActionRound.setVerticalAlignment(SwingConstants.TOP);
        lblActionRound.setToolTipText("Current action round");
        lblActionRound.setIcon(new ImageIcon("img\\board_tokens\\ussr_action_round.png"));
        canvas.add(lblActionRound);
        
        lblDefcon.setBounds(this.scaleCord(740), this.scaleCord(1255), this.scaleCord(42), this.scaleCord(41));
        lblDefcon.setVerticalAlignment(SwingConstants.TOP);
        lblDefcon.setToolTipText("Current defcon");
        lblDefcon.setIcon(new ImageIcon("img\\board_tokens\\defcon_status.png"));
        canvas.add(lblDefcon);
        
        lblTurn.setBounds(this.scaleCord(1690), this.scaleCord(96), this.scaleCord(41), this.scaleCord(42));
        lblTurn.setVerticalAlignment(SwingConstants.TOP);
        lblTurn.setToolTipText("Current turn");
        lblTurn.setIcon(new ImageIcon("img\\board_tokens\\turn.png"));
        canvas.add(lblTurn);
        

        setLayout(new BorderLayout());
        add(sp, BorderLayout.CENTER);
    }
    
    private int scaleCord(int i) {
		return (int)(SCALE*i);
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
    	lblActionRound.setBounds(this.scaleCord((448 + (56 * actionRoundNum))), this.scaleCord(103), this.scaleCord(41), this.scaleCord(42));
	}
    
    /**
     * Advances the current turn token
     * @param b Current Board
     */
    private void turnAdvance(Board b) {
    	int turnNum = b.getTurn();
    	System.out.println(turnNum);
    	lblTurn.setBounds(this.scaleCord((1690 + (72 * turnNum))), this.scaleCord(96), this.scaleCord(41), this.scaleCord(42));
	}
    
    /**
     * Advances the new defcon token location
     * @param b the current board
     */
    private void defconAdvance(Board b) {
    	int defconNum = b.getDefcon();
    	System.out.println(defconNum);
    	lblDefcon.setBounds(this.scaleCord(740 + ((5 - defconNum) * 76)), this.scaleCord(1255), this.scaleCord(42), this.scaleCord(41));
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
				jL.setBounds(this.scaleCord(l.getX()), this.scaleCord(l.getY()), this.scaleCord(45), this.scaleCord(45));
				
				influenceTokens.add(jL);
			}
			else if (c.userHasInfluence(Side.USA) && !(c.userHasControl(Side.USA))) {
				JLabel jL = new JLabel("");
				String f = "img\\usa_tokens\\usaInfluence" + c.getUSInfluence() + ".png";
				jL.setIcon(new ImageIcon(resize(new File(f))));
	
				Location l = Location.getImageLocation(c.getISO(), Side.USA);
				jL.setBounds(this.scaleCord(l.getX()), this.scaleCord(l.getY()), this.scaleCord(45), this.scaleCord(45));
				
				influenceTokens.add(jL);
			}
			if (c.userHasInfluence(Side.USSR) && (c.userHasControl(Side.USSR))) {
				JLabel jL = new JLabel("");
				String f = "img\\ussr_tokens\\ussrControl" + c.getUSSRInfluence() + ".png";
				jL.setIcon(new ImageIcon(resize(new File(f))));
				
				Location l = Location.getImageLocation(c.getISO(), Side.USSR);
				jL.setBounds(this.scaleCord(l.getX()), this.scaleCord(l.getY()), this.scaleCord(45), this.scaleCord(45));
				
				influenceTokens.add(jL);
			}
			else if (c.userHasInfluence(Side.USSR) && !(c.userHasControl(Side.USSR))) {
				JLabel jL = new JLabel("");
				String f = "img\\ussr_tokens\\ussrInfluence" + c.getUSSRInfluence() + ".png";
				jL.setIcon(new ImageIcon(resize(new File(f))));
				
				Location l = Location.getImageLocation(c.getISO(), Side.USSR);
				jL.setBounds(this.scaleCord(l.getX()), this.scaleCord(l.getY()), this.scaleCord(45), this.scaleCord(45));
				
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
	 * Resizes images
	 */
	private BufferedImage resize(File f) {
		try {
			BufferedImage oldImage = ImageIO.read(f);
			BufferedImage newImage = new BufferedImage((int)(SCALE * oldImage.getWidth()), (int)(SCALE * oldImage.getHeight()), oldImage.getType());
			Graphics2D g2d = newImage.createGraphics();
			g2d.drawImage(oldImage, 0, 0, (int)(SCALE * oldImage.getWidth()), (int)(SCALE * oldImage.getHeight()), null);
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
		defconAdvance(b);
		lblDefcon.repaint();
		actionRoundAdvance(b);
		turnAdvance(b);
		defconAdvance(b);
		repaintAllInfluence();
		canvas.revalidate();
		canvas.repaint();
	}
}