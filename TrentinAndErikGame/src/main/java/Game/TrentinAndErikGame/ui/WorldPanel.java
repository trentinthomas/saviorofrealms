package Game.TrentinAndErikGame.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.Timer;

import javax.swing.JPanel;

import Game.TrentinAndErikGame.entities.Barbarian;
import Game.TrentinAndErikGame.entities.Entity;
import java.awt.Color;

public class WorldPanel extends JPanel implements GUICard, ActionListener
{

	ArrayList<Entity> entities;
	public static Barbarian player;
	private Timer gameTimer = new Timer(10, this);
	public static boolean startTimer;
	
	public WorldPanel() {
		startTimer = false;
		setBackground(Color.GREEN);
		entities = new ArrayList<Entity>();
		player = new Barbarian();
		entities.add(player);
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
		this.addKeyListener(new KeyAdapter() {
					
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getKeyCode());
				
			}

			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyCode());
				if(e.getKeyCode() == KeyEvent.VK_UP)
				{
					player.setYVel(-player.getSpeed());
					System.out.println("Up pressed");
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT)
				{
					player.setXVel(-player.getSpeed());
					System.out.println("Left pressed");
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN)
				{
					player.setYVel(player.getSpeed());
					System.out.println("down pressed");
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT)
				{
					player.setXVel(player.getSpeed());
					System.out.println("Right pressed");
				}
			}

			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		for(Entity entity : entities)
		{
			entity.move();
			entity.paint(this.getGraphics());
			repaint();
		}
	}


}
