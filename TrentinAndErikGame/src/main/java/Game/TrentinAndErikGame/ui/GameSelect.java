package Game.TrentinAndErikGame.ui;

import javax.swing.JPanel;

import Game.TrentinAndErikGame.Util.GameSessionFactory;
import Game.TrentinAndErikGame.entities.Barbarian;
import Game.TrentinAndErikGame.entities.Player;
import Game.TrentinAndErikGame.entities.Ranger;
import Game.TrentinAndErikGame.entities.Wizard;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.Panel;
import java.awt.event.ActionEvent;

/**
 * Where the player selects what saved character they want to play.
 * @author Trentin
 *
 */
public class GameSelect extends JPanel implements GUICard{

	/**
	 * Create the panel.
	 */
	public GameSelect() {
		super();
		
		initUI();
	}

	public void refreshUI() {
		this.removeAll();
		initUI();
	}
	
	private void initUI()
	{
		setLayout(null);
		File file = new File("save1.data");
		File file2 = new File("save2.data");
		File file3 = new File("save3.data");
		JLabel lblGameOne = new JLabel("Game One:");
		lblGameOne.setBounds(101, 126, 125, 14);
		//lblGameOne.setLocation(this.getWidth()/5 - lblGameOne.getWidth()/2, 125);
		add(lblGameOne);
		
		JLabel lblGametwo = new JLabel("Game Two:");
		lblGametwo.setBounds(450, 126, 125, 14);
		//lblGametwo.setLocation(this.getWidth()/3 - lblGametwo.getWidth()/2, 125);
		add(lblGametwo);
		
		JLabel lblGameThree = new JLabel("Game Three:");
		lblGameThree.setBounds(750, 126, 125, 14);
		//lblGameThree.setLocation(this.getWidth() - lblGameThree.getWidth()/2, 125);
		add(lblGameThree);
		
		JButton btnLoadGameOne = new JButton("Load Game One");
		btnLoadGameOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load(1);
			}
		});
		btnLoadGameOne.setEnabled(file.exists());
		btnLoadGameOne.setBounds(146, 315, 125, 23);
		add(btnLoadGameOne);
		
		JButton btnLoadGameTwo = new JButton("Load Game Two");
		btnLoadGameTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load(2);
			}
		});
		btnLoadGameTwo.setEnabled(file2.exists());
		btnLoadGameTwo.setBounds(385, 315, 125, 23);
		add(btnLoadGameTwo);
		
		JButton btnLoadGameThree = new JButton("Load Game Three");
		btnLoadGameThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load(3);
			}
		});
		btnLoadGameThree.setEnabled(file3.exists());
		btnLoadGameThree.setBounds(681, 315, 125, 23);
		add(btnLoadGameThree);
		
		JButton btnNewButton = new JButton("Start New Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameSessionFactory.createGameSession(1);
				GameSessionFactory.getGameSession().setGameFile("save1.data");
				MainFrame.changeCard(MainFrame.CHARACTER_SELECT);
				WorldPanel.startTimer = true;
				
			}
		});
		btnNewButton.setBounds(146, 366, 125, 23);
		add(btnNewButton);
		
		JButton button = new JButton("Start New Game");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GameSessionFactory.createGameSession(2);
				GameSessionFactory.getGameSession().setGameFile("save2.data");
				MainFrame.changeCard(MainFrame.CHARACTER_SELECT);
				WorldPanel.startTimer = true;
			}
		});
		button.setBounds(385, 366, 125, 23);
		add(button);
		
		JButton button_1 = new JButton("Start New Game");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WorldPanel.startTimer = true;
				GameSessionFactory.createGameSession(3);
				GameSessionFactory.getGameSession().setGameFile("save3.data");
				MainFrame.changeCard(MainFrame.CHARACTER_SELECT);
			}
		});
		button_1.setBounds(681, 366, 125, 23);
		add(button_1);
		
		JLabel label = new JLabel("<Empty>");
		label.setBounds(175, 253, 67, 14);
		add(label);
		
		JLabel label_1 = new JLabel("<Empty>");
		label_1.setBounds(423, 253, 87, 14);
		add(label_1);
		
		JLabel label_2 = new JLabel("<Empty>");
		label_2.setBounds(717, 253, 60, 14);
		add(label_2);  
		setSize(1000, 700);
	}
	
	private void load(int saveFileNumber)
	{
		String saveFileName = "save" + Integer.toString(saveFileNumber) + ".data";		
		GameSessionFactory.createGameSession(saveFileNumber);
		WorldPanel.startTimer = true;
		GameSessionFactory.getGameSession().setGameFile(saveFileName);
		//TODO: create new method that is called loadWorld() which contains loadPlayer.
		WorldPanel.player = loadPlayer();
		MainFrame.changeCard(MainFrame.WORLD_PANEL);
		
	}
	
	private Player loadPlayer()
	{
		Player player = null;
		FileInputStream f_in = null;
		ObjectInputStream obj_in = null;
		File file = new File(GameSessionFactory.getGameSession().getGameFile());
		try{
			f_in = new FileInputStream("save1.data");
			obj_in = new ObjectInputStream(f_in);
			Object obj = obj_in.readObject();
			
			if( obj instanceof Barbarian)
			{
				player = (Barbarian)obj;
				
				WorldPanel.player = player;
				System.out.println(player.getxCoord());
			}
			if( obj instanceof Ranger)
			{
				player = (Ranger)obj;
				
				WorldPanel.player = player;
				System.out.println(player.getxCoord());
			}
			if( obj instanceof Wizard)
			{
				player = (Wizard)obj;
				WorldPanel.player = player;
				System.out.println(player.getxCoord());
			}
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		catch(ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
		}
		return player;
	}
	
	private static void loadPreviousObjects()
	{
		FileInputStream f_in = null;
		ObjectInputStream obj_in = null;
		File file = new File("save1.data");
		if(file.exists())
		{
			try{
				f_in = new FileInputStream("save1.data");
				obj_in = new ObjectInputStream(f_in);
				Object obj = obj_in.readObject();
				
				if( obj instanceof Player)
				{
					Player player = (Player)obj;
					
					WorldPanel.player = player;
					System.out.println(player.getxCoord());
				}
			}
			catch(IOException ioe)
			{
				ioe.printStackTrace();
			}
			catch(ClassNotFoundException cnfe)
			{
				cnfe.printStackTrace();
			}
		}
		
	}
}
