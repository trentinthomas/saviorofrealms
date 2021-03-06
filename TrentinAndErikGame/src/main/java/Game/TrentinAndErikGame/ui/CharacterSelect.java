package Game.TrentinAndErikGame.ui;

import javax.swing.JPanel;

import Game.TrentinAndErikGame.Util.GameSessionFactory;
import Game.TrentinAndErikGame.entities.Barbarian;
import Game.TrentinAndErikGame.entities.Player;
import Game.TrentinAndErikGame.entities.Ranger;
import Game.TrentinAndErikGame.entities.Wizard;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class CharacterSelect extends JPanel implements GUICard {

	/**
	 * Create the panel.
	 */
	public CharacterSelect() {
		super();
		initUI();
	}

	public void refreshUI() {
		// TODO Auto-generated method stub
		this.removeAll();
		initUI();
	}
	
	private void initUI()
	{
		//removing this seems to be fine - assuming its because it just assumes the size of parent
		//setSize(1000, 700);
		//File file1 = new File("save1.data");
		//File file2 = new File("save2.data");
		//File file3 = new File("save3.data");
		setLayout(null);
		
		JButton btnBarbarian = new JButton("Warrior");
		btnBarbarian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameSessionFactory.createGameSession(MainFrame.gameSessionID);
				GameSessionFactory.getGameSession().setGameFile("save" + Integer.toString(MainFrame.gameSessionID) + ".data");
				WorldPanel.startTimer = true;
				Player player = new Barbarian();
				GameSessionFactory.getGameSession().setPlayer(player);
				MainFrame.changeCard(MainFrame.WORLD_PANEL);
			}
		});
		btnBarbarian.setBounds(92, 175, 89, 23);
		btnBarbarian.setLocation(this.getWidth()/5 - btnBarbarian.getWidth()/2, 250);
		add(btnBarbarian);
		
		JButton btnRanger = new JButton("Ranger");
		btnRanger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameSessionFactory.createGameSession(MainFrame.gameSessionID);
				GameSessionFactory.getGameSession().setGameFile("save" + Integer.toString(MainFrame.gameSessionID) + ".data");
				WorldPanel.startTimer = true;
				Player player = new Ranger();
				GameSessionFactory.getGameSession().setPlayer(player);
				MainFrame.changeCard(MainFrame.WORLD_PANEL);
			}
		});
		btnRanger.setBounds(401, 175, 89, 23);
		btnRanger.setLocation(this.getWidth()/2 - btnRanger.getWidth()/2, 250);
		add(btnRanger);
		
		JButton btnWizard = new JButton("Wizard");
		btnWizard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameSessionFactory.createGameSession(MainFrame.gameSessionID);
				GameSessionFactory.getGameSession().setGameFile("save" + Integer.toString(MainFrame.gameSessionID) + ".data");
				WorldPanel.startTimer = true;
				Player player = new Wizard();
				GameSessionFactory.getGameSession().setPlayer(player);
				MainFrame.changeCard(MainFrame.WORLD_PANEL);
			}
		});
		btnWizard.setBounds(688, 175, 89, 23);
		btnWizard.setLocation(this.getWidth() * 5/6 - btnWizard.getWidth()/2, 250);
		add(btnWizard);
		
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(this.getWidth()/5 - 89/2, 150, 89, 89);
		g.setColor(Color.GREEN);
		g.fillRect(this.getWidth()/2 - 89/2, 150, 89, 89);
		g.setColor(Color.BLUE);
		g.fillRect(this.getWidth() * 5/6 - 89/2, 150, 89, 89);
	}

}
