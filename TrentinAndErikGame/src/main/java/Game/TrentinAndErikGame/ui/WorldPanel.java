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

public class WorldPanel extends JPanel implements GUICard, ActionListener
{
	private Timer gameTimer = new Timer(20, this);
	public static boolean startTimer;
	
	public WorldPanel() {
		super();
		startTimer = false;
		initUI();
	}

	public void refreshUI() {
		this.removeAll();
		initUI();
	}
	
	public void initUI()
	{
		setLayout(null);
		if(startTimer)
		{
			gameTimer.start();
		}
		this.setFocusable(true);
		setSize(1000, 700);
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
