package UI;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Util.Resources;

public class Menu extends BasicGameState {

	Image up_singleplayer;
	Image up_multiplayer;
	Image up_options;
	Image up_exit;
	
	Image down_singleplayer;
	Image down_multiplayer;
	Image down_options;
	Image down_exit;
	
	int buttonWidth = 117;
	int buttonHeight = 43;
	
	boolean hoverSingleplayer = false;
	boolean hoverMultiplayer = false;
	boolean hoverOptions = false;
	boolean hoverExit = false;
	boolean hoveringOverAButton = false;
	
	private final int SINGLEPLAYER_BUTTON_ID = 1;
	private final int MULTIPLAYER_BUTTON_ID = 2;
	private final int OPTIONS_BUTTON_ID = 3;
	private final int EXIT_BUTTON_ID = 4;
	
	GraphicButton singlePlayerButton, multiPlayerButton, optionsButton, exitButton;
	private ArrayList<GraphicButton> buttons;
	

	public String mouse = "No input yet!";
	
	public Menu(int state)
	{
		
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		new Resources();
		up_singleplayer = new Image("/res/buttons/up_singleplayer.png");
		up_multiplayer = new Image("/res/buttons/up_multiplayer.png");
		up_options = new Image("/res/buttons/up_options.png");
		up_exit = new Image("/res/buttons/up_exit.png");
		
		down_singleplayer = new Image("/res/buttons/down_singleplayer.png");
		down_multiplayer = new Image("/res/buttons/down_multiplayer.png");
		down_options = new Image("/res/buttons/down_options.png");
		down_exit = new Image("/res/buttons/down_exit.png");
		
		
		singlePlayerButton = new GraphicButton(gc, 100, 100, up_singleplayer, down_singleplayer);
		multiPlayerButton = new GraphicButton(gc, 100, 100, up_multiplayer, down_multiplayer);
		optionsButton = new GraphicButton(gc, 100, 100, up_options, down_options);
		exitButton = new GraphicButton(gc, 100, 100, up_exit, down_exit);
		
		singlePlayerButton.setLocation(gc.getWidth()/2 - buttonWidth/2, gc.getHeight()/2 - buttonHeight*2);
		multiPlayerButton.setLocation(gc.getWidth()/2 - buttonWidth/2, gc.getHeight()/2 - buttonHeight);
		optionsButton.setLocation(gc.getWidth()/2 - buttonWidth/2, gc.getHeight()/2);
		exitButton.setLocation(gc.getWidth()/2 - buttonWidth/2, gc.getHeight()/2 + buttonHeight);
		
		singlePlayerButton.setButtonId(SINGLEPLAYER_BUTTON_ID);
		multiPlayerButton.setButtonId(MULTIPLAYER_BUTTON_ID);
		optionsButton.setButtonId(OPTIONS_BUTTON_ID);
		exitButton.setButtonId(EXIT_BUTTON_ID);
		
		buttons = new ArrayList<GraphicButton>();
		buttons.add(singlePlayerButton);
		buttons.add(multiPlayerButton);
		buttons.add(optionsButton);
		buttons.add(exitButton);
		
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.drawString(mouse, 10, 30);
		g.setBackground(Color.black);

		for( GraphicButton button : buttons)
		{
			button.render(gc, g);
		}
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		for( GraphicButton button : buttons)
		{
			button.update(gc);
			if( button.isPressed() )
			{
				handleEvent(button.getButtonId(), sbg);
			}
		}
		
	}

	@Override
	public int getID() 
	{
		return 0;
	}
	
	public void handleEvent(int buttonId, StateBasedGame sbg) {
		switch(buttonId)
		{
			case SINGLEPLAYER_BUTTON_ID:
				sbg.enterState(Engine.gameSelect);
				break;
			case MULTIPLAYER_BUTTON_ID:
				//sbg.enterState(Engine.multiplayer); TODO
				break;
			case OPTIONS_BUTTON_ID:
				//sbg.enterState(Engine.options); TODO
				break;
			case EXIT_BUTTON_ID:
				System.exit(0);
				break;
		}
	}

}
