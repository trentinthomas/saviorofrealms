package Game.TrentinAndErikGame.ui;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Game.TrentinAndErikGame.Util.GameSessionFactory;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

/**
 * Main Menu of the game. The first menu the player sees when they enter the game.
 * @author Trentin
 *
 */
public class MainMenu extends JPanel implements GUICard{
	/**
	 * Create the panel.
	 */
	public MainMenu() {
		super();
		initUI();
	}

	/**
	 * Removes everything from the panel and re-initializes it.
	 */
	public void refreshUI() {
		this.removeAll();
		initUI();
	}
	
	/**
	 * Initializes the panel.
	 */
	public void initUI()
	{
		setLayout(null);
			
		JLabel lblNewLabel = new JLabel("Main Menu");		
		lblNewLabel.setBounds(100, 100, lblNewLabel.getText().length()*8, 10);
		lblNewLabel.setLocation(this.getWidth()/2 - lblNewLabel.getWidth()/2, this.getHeight()/4);
		add(lblNewLabel);
		
		JButton btnPlayGame = new JButton("Play Game");
		btnPlayGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.changeCard(MainFrame.PLAYER_SELECT);
			}
		});
		
		//removing this seems to be fine - assuming its because it just assumes the size of parent
		//setSize(1, 1);
		btnPlayGame.setBounds(384, 197, 118, 23);
		btnPlayGame.setLocation(this.getWidth()/2 - btnPlayGame.getWidth()/2, this.getHeight()/3);
		add(btnPlayGame);
		
		
	}
	
	/**
	 * Request an Image for the background. This is still TODO
	 * 
	 * @return The background image
	 */
    public Image requestImage() {
        Image image = null;
        File file = new File("./TrentinAndErikGame/Resources/bkg_main.jpg");
        file.getAbsolutePath();
//        try 
//        {
//            image = ImageIO.read(file);
//        } 
//        catch (IOException e) 
//        {
//            e.printStackTrace();
//        }
        return image;
    }
	
    /**
     * paints the panel using the Graphics object. Overrides the superclass
     */
	@Override
	public void paintComponent(Graphics g)
	{		
		super.paintComponent(g);
		g.drawImage(requestImage(), 0, 0, null);
		
	}
}
