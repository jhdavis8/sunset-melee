package gameFiles;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScrollImage extends JPanel {
    private static final long serialVersionUID = 1L;
    private BufferedImage image;
    private JPanel canvas;
    private final JLabel lblActionRound = new JLabel("");
    private Side actionRoundSide = Side.USSR;
    private int actionRoundNum = 1;

    public ScrollImage() {
        try {
            this.image = ImageIO.read(new File("img\\gameMap.jpg"));
        }catch(IOException ex) {
            Logger.getLogger(ScrollImage.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        lblActionRound.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mousePressed(MouseEvent arg0) {
        		if (actionRoundNum == 8 && actionRoundSide == Side.USA) {
        			lblActionRound.setBounds(448, 103, 41, 42);
        			lblActionRound.setIcon(new ImageIcon("img\\board_tokens\\ussr_action_round.png"));
        			actionRoundSide = Side.USSR;
        			actionRoundNum = 1;
        		}
        		else if (actionRoundSide == Side.USA) {
        			lblActionRound.setIcon(new ImageIcon("img\\board_tokens\\ussr_action_round.png"));
        			actionRoundSide = Side.USSR;
        			int newX = lblActionRound.getX() + 56;
        			lblActionRound.setBounds(newX, 103, 41, 42);
        			actionRoundNum++;
        		} else {
        			lblActionRound.setIcon(new ImageIcon("img\\board_tokens\\usa_action_round.png"));
        			actionRoundSide = Side.USA;
        		}
        	}
        });
        
        
        lblActionRound.setBounds(448, 103, 41, 42);
        lblActionRound.setVerticalAlignment(SwingConstants.TOP);
        canvas.add(lblActionRound);
        lblActionRound.setToolTipText("Current action round");
        lblActionRound.setIcon(new ImageIcon("img\\board_tokens\\ussr_action_round.png"));
        Side actionRoundSide = Side.USSR;
        setLayout(new BorderLayout());
        add(sp, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JPanel p = new ScrollImage();
                JFrame f = new JFrame();
                f.setContentPane(p);
                f.setSize(400, 300);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setVisible(true);
            }
        });
    }
}