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

		File file1 = new File("save1.data");
		File file2 = new File("save2.data");
		File file3 = new File("save3.data");
		
		JLabel lblGameOne = new JLabel("Game One");
		lblGameOne.setBounds(101, 126, lblGameOne.getText().length()*8, 14);
		lblGameOne.setLocation(this.getWidth()/5 - lblGameOne.getWidth()/2, 125);
		add(lblGameOne);
		
		JLabel lblGameTwo = new JLabel("Game Two");
		lblGameTwo.setBounds(450, 126, lblGameTwo.getText().length()*8, 14);
		lblGameTwo.setLocation(this.getWidth()/2 - lblGameTwo.getWidth()/2, 125);
		add(lblGameTwo);
		
		JLabel lblGameThree = new JLabel("Game Three");
		lblGameThree.setBounds(750, 126, lblGameThree.getText().length()*8, 14);
		lblGameThree.setLocation(this.getWidth() * 5/6 - lblGameThree.getWidth()/2, 125);
		add(lblGameThree);
		
		JButton btnLoadGameOne = new JButton("Load");
		btnLoadGameOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load(1);
			}
		});
		btnLoadGameOne.setEnabled(file1.exists());
		btnLoadGameOne.setBounds(146, 315, 125, 23);
		btnLoadGameOne.setLocation(this.getWidth()/5 - btnLoadGameOne.getWidth()/2, 315); 
		add(btnLoadGameOne);
		
		JButton btnLoadGameTwo = new JButton("Load");
		btnLoadGameTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load(2);
			}
		});
		btnLoadGameTwo.setEnabled(file2.exists());
		btnLoadGameTwo.setBounds(385, 315, 125, 23);
		btnLoadGameTwo.setLocation(this.getWidth()/2 - btnLoadGameTwo.getWidth()/2, 315);
		add(btnLoadGameTwo);
		
		JButton btnLoadGameThree = new JButton("Load");
		btnLoadGameThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load(3);
			}
		});
		btnLoadGameThree.setEnabled(file3.exists());
		btnLoadGameThree.setBounds(681, 315, 125, 23);
		btnLoadGameThree.setLocation(this.getWidth() * 5/6 - btnLoadGameThree.getWidth()/2, 315);
		add(btnLoadGameThree);
		
		JButton btnNewButton = new JButton("New");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.gameSessionID = 1;
				MainFrame.changeCard(MainFrame.CHARACTER_SELECT);

				
			}
		});
		btnNewButton.setBounds(146, 366, 125, 23);
		btnNewButton.setLocation(this.getWidth()/5 - btnNewButton.getWidth()/2, 366);
		add(btnNewButton);
		
		JButton button = new JButton("New");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.gameSessionID = 2;
				MainFrame.changeCard(MainFrame.CHARACTER_SELECT);

			}
		});
		button.setBounds(385, 366, 125, 23);
		button.setLocation(this.getWidth()/2 - button.getWidth()/2, 366);
		add(button);
		
		JButton button_1 = new JButton("New");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.gameSessionID = 3;
				MainFrame.changeCard(MainFrame.CHARACTER_SELECT);
			}
		});
		button_1.setBounds(681, 366, 125, 23);
		button_1.setLocation(this.getWidth() * 5/6 - button_1.getWidth()/2, 366);
		add(button_1);
		
		JLabel label = new JLabel("<Empty>");
		label.setBounds(175, 253, label.getText().length()*8, 14);
		label.setLocation(this.getWidth()/5 - label.getWidth()/2, 253);
		add(label);
		
		JLabel label_1 = new JLabel("<Empty>");
		label_1.setBounds(423, 253, label_1.getText().length()*8, 14);
		label_1.setLocation(this.getWidth()/2 - label_1.getWidth()/2, 253);
		add(label_1);
		
		JLabel label_2 = new JLabel("<Empty>");
		label_2.setBounds(717, 253, label_2.getText().length()*8, 14);
		label_2.setLocation(this.getWidth() * 5/6 - label_2.getWidth()/2, 253);
		add(label_2);
		
		//removing this seems to be fine - assuming its because it just assumes the size of parent
		//setSize(1000, 700);
	}
	
	private void load(int saveFileNumber)
	{
		String saveFileName = "save" + Integer.toString(saveFileNumber) + ".data";		
		GameSessionFactory.createGameSession(saveFileNumber);
		WorldPanel.startTimer = true;
		GameSessionFactory.getGameSession().setGameFile(saveFileName);
		//TODO: create new method that is called loadWorld() which contains loadPlayer.
		GameSessionFactory.getGameSession().setPlayer(loadPlayer(saveFileName));
		MainFrame.changeCard(MainFrame.WORLD_PANEL);
		
	}
	
	private Player loadPlayer(String saveName)
	{
		Player player = null;
		FileInputStream f_in = null;
		ObjectInputStream obj_in = null;
		File file = new File(GameSessionFactory.getGameSession().getGameFile());
		if(file.exists())
		{
			try{
				f_in = new FileInputStream(saveName);
				obj_in = new ObjectInputStream(f_in);
				Object obj = obj_in.readObject();
				
				if( obj instanceof Barbarian)
				{
					player = (Barbarian)obj;
					GameSessionFactory.getGameSession().setPlayer(player);
				}
				else if( obj instanceof Ranger)
				{
					player = (Ranger)obj;
					GameSessionFactory.getGameSession().setPlayer(player);
				}
				else if( obj instanceof Wizard)
				{
					player = (Wizard)obj;
					GameSessionFactory.getGameSession().setPlayer(player);
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
		return player;
	}
	
}
