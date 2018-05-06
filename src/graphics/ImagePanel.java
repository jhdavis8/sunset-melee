package graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Extends JPanel by creating a class for the holding and drawing of image files.
 * @author Brendan Cashman
 * {@link https://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel}
 *
 */
public class ImagePanel extends JPanel{

    /**
     * A buffer for holding the image to present
     */
    private BufferedImage image;

    /**
     * @param path The path to the image files.
     */
    public ImagePanel(String path) {
       try {                
          image = ImageIO.read(new File(path));
       } catch (IOException ex) {
            ex.getMessage();
            ex.printStackTrace();
            System.out.println("Not a Valid Path");
       }
    }

    /* (non-Javadoc)
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }

}