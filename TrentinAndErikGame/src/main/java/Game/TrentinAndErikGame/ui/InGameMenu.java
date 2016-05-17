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
public class InGameMenu extends JPanel implements GUICard{

	/**
	 * Create the panel.
	 */
	public InGameMenu() {
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
		
		JLabel lblPause = new JLabel("Paused");
		lblPause.setBounds(0, 0, lblPause.getText().length()*8, 14);
		lblPause.setLocation(this.getWidth()/2 - lblPause.getWidth()/2, 100);
		add(lblPause);

		//resume the game
		JButton btnResume = new JButton("Resume");
		btnResume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MainFrame.changeCard(MainFrame.WORLD_PANEL);
				WorldPanel.startTimer = true;
			}
		});
		btnResume.setBounds(0, 0, 125, 23);
		btnResume.setLocation(this.getWidth()/2 - btnResume.getWidth()/2, 150);
		add(btnResume);		
		
		//save and go to menu
		JButton btnStoM = new JButton("Save to Menu");
		btnStoM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.saveGame();
				WorldPanel.startTimer = false;
				MainFrame.changeCard(MainFrame.MAIN_MENU);
			}
		});
		btnStoM.setBounds(0, 0, 125, 23);
		btnStoM.setLocation(this.getWidth()/2 - btnStoM.getWidth()/2, 190);
		add(btnStoM);
		
		//save and exit the application
		JButton btnSandQ = new JButton("Save and Quit");
		btnSandQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.saveGame();
				System.exit(0);
			}
		});
		btnSandQ.setBounds(0, 0, 125, 23);
		btnSandQ.setLocation(this.getWidth()/2 - btnSandQ.getWidth()/2, 230);
		add(btnSandQ);
		

		
		
		

		
	}

	
}
