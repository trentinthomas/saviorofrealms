package Game.TrentinAndErikGame.ui;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Game.TrentinAndErikGame.Util.GameSessionFactory;
import Game.TrentinAndErikGame.entities.Player;

public class MainFrame extends JFrame implements KeyListener{

	private static JPanel cards;
	
	public static String MAIN_MENU = "0";
	public static String WORLD_PANEL = "1";
	public static String PLAYER_SELECT = "2";
	public static String CHARACTER_SELECT = "3";
	
	private boolean upKeyPressed, downKeyPressed, rightKeyPressed, leftKeyPressed; //Why?

	
	public MainFrame()
	{
		super();
		initUI();
	}
	
	public void initUI()
	{
		JFrame frame;
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		createCards(frame.getContentPane());
		changeCard(MAIN_MENU);
		frame.setSize(1000, 700);
		frame.setVisible(true);
	}
	
	
	public void createCards(Container pane)
	{
		cards = new JPanel(new CardLayout());
		
		MainMenu card1 = new MainMenu();
		WorldPanel card2 = new WorldPanel();
		GameSelect card3 = new GameSelect();
		CharacterSelect card4 = new CharacterSelect();
		cards.add(card1, MAIN_MENU);
		cards.add(card2, WORLD_PANEL);
		cards.add(card3, PLAYER_SELECT);
		cards.add(card4, CHARACTER_SELECT);
		pane.add(cards);
		cards.setFocusable(true);
		cards.addKeyListener(this);
	}
	
	public static void changeCard(String event)
	{
		CardLayout cardlay = (CardLayout)(cards.getLayout());
		
		int card = Integer.parseInt(event);
		((GUICard) cards.getComponent(card)).refreshUI();
		
		cardlay.show(cards, event);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		MainFrame.changeCard(MainFrame.MAIN_MENU);
	}
	
	

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		// Don't think we need to have anything in the KeyTyped event.
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
		{
			GameSessionFactory.getGameSession().getPlayer().setYVel(-GameSessionFactory.getGameSession().getPlayer().getSpeed());
			upKeyPressed = true;//Why? What's purpose?
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
		{
			GameSessionFactory.getGameSession().getPlayer().setXVel(-GameSessionFactory.getGameSession().getPlayer().getSpeed());
			leftKeyPressed = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
		{
			GameSessionFactory.getGameSession().getPlayer().setYVel(GameSessionFactory.getGameSession().getPlayer().getSpeed());
			downKeyPressed = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
		{
			GameSessionFactory.getGameSession().getPlayer().setXVel(GameSessionFactory.getGameSession().getPlayer().getSpeed());
			rightKeyPressed = true;
		}
	}

	public void keyReleased(KeyEvent e) {//I don't get the purpose of the else statements. Why would they push up and down at the same time?
		
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)//if they release up
		{
			if(!downKeyPressed)//and didn't press down then
				GameSessionFactory.getGameSession().getPlayer().setYVel(0); //Set Y to 0.
			else//If they pressed up and down at the same time and still are pressing down...
				GameSessionFactory.getGameSession().getPlayer().setYVel(GameSessionFactory.getGameSession().getPlayer().getSpeed());//Keep going?
			upKeyPressed = false;//Reset varible??
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
		{
			if(!rightKeyPressed)
				GameSessionFactory.getGameSession().getPlayer().setXVel(0);
			else
				GameSessionFactory.getGameSession().getPlayer().setXVel(GameSessionFactory.getGameSession().getPlayer().getSpeed());
			leftKeyPressed = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
		{
			if(!upKeyPressed)
				GameSessionFactory.getGameSession().getPlayer().setYVel(0);
			else
				GameSessionFactory.getGameSession().getPlayer().setYVel(-GameSessionFactory.getGameSession().getPlayer().getSpeed());
			downKeyPressed = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
		{
			if(!leftKeyPressed)
				GameSessionFactory.getGameSession().getPlayer().setXVel(0);
			else
				GameSessionFactory.getGameSession().getPlayer().setXVel(-GameSessionFactory.getGameSession().getPlayer().getSpeed());
			rightKeyPressed = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			saveGame();
			System.exit(0);
		}

	}
	
	private void saveGame()
	{
		Player player = GameSessionFactory.getGameSession().getPlayer();
		FileOutputStream f_out = null;
		ObjectOutputStream obj_out = null;
		try {
			f_out = new FileOutputStream(GameSessionFactory.getGameSession().getGameFile());
			obj_out = new ObjectOutputStream(f_out);
			
			obj_out.writeObject(player);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		finally{
			try {
				f_out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				obj_out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}

}
