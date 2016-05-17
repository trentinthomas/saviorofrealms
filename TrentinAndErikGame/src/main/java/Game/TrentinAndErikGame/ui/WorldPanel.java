package Game.TrentinAndErikGame.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.Timer;

import javax.swing.JPanel;

import Game.TrentinAndErikGame.Util.GameSessionFactory;
import Game.TrentinAndErikGame.entities.Barbarian;
import Game.TrentinAndErikGame.entities.Entity;
import Game.TrentinAndErikGame.entities.Player;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This is where all the action occurs in the game. After the player chooses which player, this will be
 * where everything occurs.
 * @author Trentin
 *
 */
public class WorldPanel extends JPanel implements GUICard, ActionListener
{
	private Timer gameTimer = new Timer(20, this);
	public static boolean startTimer;
	
	/**
	 * Default constructor
	 */
	public WorldPanel() {
		super();
		startTimer = false;
		initUI();
	}

	/**
	 * removes everything and re-initializes the GUI
	 */
	public void refreshUI() {
		this.removeAll();
		initUI();
	}
	
	/**
	 * Initializes the GUI
	 */
	public void initUI()
	{
		setLayout(null);
		if(startTimer)
		{
			gameTimer.start();
		}
		this.setFocusable(true);
		//removing this seems to be fine - assuming its because it just assumes the size of parent
		//setSize(1000, 700);
	}

	/**
	 * Anything within this method is performed with every tick of the timer.
	 */
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	/**
	 * paintComponent just paints everything on the screen.
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		ArrayList<Entity> entities = GameSessionFactory.getGameSession().getEntities();
		for( Entity entity : entities )
		{
			entity.move();
			entity.paint(g);
		}
	}


}
