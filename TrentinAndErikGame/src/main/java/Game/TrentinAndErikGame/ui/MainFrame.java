package Game.TrentinAndErikGame.ui;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements KeyListener{

	private static JPanel cards;
	
	public static String MAIN_MENU = "0";
	public static String WORLD_PANEL = "1";

	
	public MainFrame()
	{
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
		cards.add(card1, MAIN_MENU);
		cards.add(card2, WORLD_PANEL);
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
		System.out.println(e.getKeyCode());
		
	}

	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			WorldPanel.player.setYVel(-WorldPanel.player.getSpeed());
			System.out.println("Up pressed");
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			WorldPanel.player.setXVel(-WorldPanel.player.getSpeed());
			System.out.println("Left pressed");
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			WorldPanel.player.setYVel(WorldPanel.player.getSpeed());
			System.out.println("down pressed");
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			WorldPanel.player.setXVel(WorldPanel.player.getSpeed());
			System.out.println("Right pressed");
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
