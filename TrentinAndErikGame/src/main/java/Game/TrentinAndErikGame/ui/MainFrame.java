package Game.TrentinAndErikGame.ui;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Game.TrentinAndErikGame.Util.GameSessionFactory;

public class MainFrame extends JFrame implements KeyListener{

	private static JPanel cards;
	
	public static String MAIN_MENU = "0";
	public static String WORLD_PANEL = "1";
	public static String PLAYER_SELECT = "2";

	
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
		PlayerSelect card3 = new PlayerSelect();
		cards.add(card1, MAIN_MENU);
		cards.add(card2, WORLD_PANEL);
		cards.add(card3, PLAYER_SELECT);
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
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			GameSessionFactory.getGameSession().getPlayer().setYVel(-GameSessionFactory.getGameSession().getPlayer().getSpeed());
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			GameSessionFactory.getGameSession().getPlayer().setXVel(-GameSessionFactory.getGameSession().getPlayer().getSpeed());
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			GameSessionFactory.getGameSession().getPlayer().setYVel(GameSessionFactory.getGameSession().getPlayer().getSpeed());
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			GameSessionFactory.getGameSession().getPlayer().setXVel(GameSessionFactory.getGameSession().getPlayer().getSpeed());
		}
	}

	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			GameSessionFactory.getGameSession().getPlayer().setYVel(0);
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			GameSessionFactory.getGameSession().getPlayer().setXVel(0);
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			GameSessionFactory.getGameSession().getPlayer().setYVel(0);
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			GameSessionFactory.getGameSession().getPlayer().setXVel(0);
		}
	}

}
