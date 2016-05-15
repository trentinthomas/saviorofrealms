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
		setSize(1000, 700);
		setLayout(null);
		
		JButton btnBarbarian = new JButton("Barbarian");
		btnBarbarian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player player = new Barbarian();
				GameSessionFactory.getGameSession().setPlayer(player);
				MainFrame.changeCard(MainFrame.WORLD_PANEL);
			}
		});
		btnBarbarian.setBounds(92, 307, 89, 23);
		add(btnBarbarian);
		
		JButton btnRanger = new JButton("Ranger");
		btnRanger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player player = new Ranger();
				GameSessionFactory.getGameSession().setPlayer(player);
				MainFrame.changeCard(MainFrame.WORLD_PANEL);
			}
		});
		btnRanger.setBounds(401, 307, 89, 23);
		add(btnRanger);
		
		JButton btnWizard = new JButton("Wizard");
		btnWizard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player player = new Wizard();
				GameSessionFactory.getGameSession().setPlayer(player);
				MainFrame.changeCard(MainFrame.WORLD_PANEL);
			}
		});
		btnWizard.setBounds(688, 307, 89, 23);
		add(btnWizard);
		
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(92, 200, 89, 89);
		g.setColor(Color.GREEN);
		g.fillRect(401, 200, 89, 89);
		g.setColor(Color.BLUE);
		g.fillRect(688, 200, 89, 89);
	}

}
