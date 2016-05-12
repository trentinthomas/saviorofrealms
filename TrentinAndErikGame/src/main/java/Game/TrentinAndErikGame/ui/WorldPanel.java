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

	public ArrayList<Entity> entities;
	public static Player player;
	private Timer gameTimer = new Timer(20, this);
	public static boolean startTimer;
	
	public WorldPanel() {
		super();
		startTimer = false;
		setBackground(Color.GREEN);
		entities = new ArrayList<Entity>();
		player = new Barbarian(); //TODO make into whatever player chooses.
		entities.add(player);
		
		initUI();
	}

	public void refreshUI() {
		this.removeAll();
		initUI();
	}
	
	public void initUI()
	{
		if(GameSessionFactory.hasGameSession())
		{
			GameSessionFactory.getGameSession().setPlayer(player);
		}
		setLayout(null);
		if(startTimer)
		{
			gameTimer.start();
		}
		this.setFocusable(true);
		this.addKeyListener(new KeyAdapter() {
					
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getKeyCode());
				
			}

			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_UP)
				{
					player.setYVel(-player.getSpeed());
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT)
				{
					player.setXVel(-player.getSpeed());
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN)
				{
					player.setYVel(player.getSpeed());
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT)
				{
					player.setXVel(player.getSpeed());
				}
			}

			public void keyReleased(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_UP)
				{
					player.setYVel(0);
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT)
				{
					player.setXVel(0);
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN)
				{
					player.setYVel(0);
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT)
				{
					player.setXVel(0);
				}
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		for(Entity entity : entities)
		{
			repaint();
		}
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(Entity entity : entities)
		{
			entity.move();
			entity.paint(g);
		}
	}


}
