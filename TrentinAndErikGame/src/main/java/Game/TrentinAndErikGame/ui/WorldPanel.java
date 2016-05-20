package Game.TrentinAndErikGame.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.Timer;

import javax.swing.JPanel;

import Game.TrentinAndErikGame.Util.GameSessionFactory;
import Game.TrentinAndErikGame.entities.Entity;

import java.awt.Graphics;

/**
 * This is where all the action occurs in the game. After the player chooses which player, this will be
 * where everything occurs.
 * @author Trentin
 *
 */
public class WorldPanel extends JPanel implements GUICard, ActionListener, MouseListener
{
	private Timer gameTimer = new Timer(33, this);
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
		this.addMouseListener(this);
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
		List<Entity> entities = GameSessionFactory.getGameSession().getEntities();
		for( Entity entity : entities )
		{
			entity.move();
			entity.paint(g);
		}
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		GameSessionFactory.getGameSession().getPlayer().attack(e.getX(),e.getY());
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
