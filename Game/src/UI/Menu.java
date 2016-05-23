package UI;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

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
	

	public String mouse = "No input yet!";
	
	public Menu(int state)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		up_singleplayer = new Image("/res/buttons/up_singleplayer.png");
		up_multiplayer = new Image("/res/buttons/up_multiplayer.png");
		up_options = new Image("/res/buttons/up_options.png");
		up_exit = new Image("/res/buttons/up_exit.png");
		
		down_singleplayer = new Image("/res/buttons/down_singleplayer.png");
		down_multiplayer = new Image("/res/buttons/down_multiplayer.png");
		down_options = new Image("/res/buttons/down_options.png");
		down_exit = new Image("/res/buttons/down_exit.png");
/*		gc.setAlwaysRender(true);
		gc.setTargetFrameRate(60);
		gc.setMaximumLogicUpdateInterval(60);*/
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.drawString(mouse, 10, 30);
		//g.drawLine(0, gc.getHeight()/2, gc.getWidth(), gc.getHeight()/2);
		
		
/*		g.drawLine(0, gc.getHeight()/2, gc.getWidth(), gc.getHeight()/2);
		g.drawLine(0, gc.getHeight()/2 - buttonHeight*2, gc.getWidth(), gc.getHeight()/2 - buttonHeight*2);
		g.drawLine(0, gc.getHeight()/2 - buttonHeight, gc.getWidth(), gc.getHeight()/2 - buttonHeight); // this is the middle 
		g.drawLine(0, gc.getHeight()/2 + buttonHeight, gc.getWidth(), gc.getHeight()/2 + buttonHeight);
		g.drawLine(0, gc.getHeight()/2 + buttonHeight*2, gc.getWidth(), gc.getHeight()/2 + buttonHeight*2);
		
		//vertical lines
		g.drawLine(gc.getWidth()/2 - buttonWidth/2, 0, gc.getWidth()/2 - buttonWidth/2, gc.getHeight());
		g.drawLine(gc.getWidth()/2 + buttonWidth/2, 0, gc.getWidth()/2 + buttonWidth/2, gc.getHeight());*/
		
		//g.drawString("Savior of Realms", 325, 100);
		
		if(!hoverSingleplayer)
			g.drawImage(up_singleplayer, gc.getWidth()/2 - buttonWidth/2, gc.getHeight()/2 - buttonHeight*2);
		else
			g.drawImage(down_singleplayer, gc.getWidth()/2 - buttonWidth/2, gc.getHeight()/2 - buttonHeight*2);
		
		if(!hoverMultiplayer)
			g.drawImage(up_multiplayer, gc.getWidth()/2 - buttonWidth/2, gc.getHeight()/2 - buttonHeight);
		else
			g.drawImage(down_multiplayer, gc.getWidth()/2 - buttonWidth/2, gc.getHeight()/2 - buttonHeight);
		
		if(!hoverOptions)
			g.drawImage(up_options, gc.getWidth()/2 - buttonWidth/2, gc.getHeight()/2);
		else
			g.drawImage(down_options, gc.getWidth()/2 - buttonWidth/2, gc.getHeight()/2);
		
		if(!hoverExit)
			g.drawImage(up_exit, gc.getWidth()/2 - buttonWidth/2, gc.getHeight()/2 + buttonHeight);
		else
			g.drawImage(down_exit, gc.getWidth()/2 - buttonWidth/2, gc.getHeight()/2 + buttonHeight);
		
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		Input input = gc.getInput();
		int mouseX = Mouse.getX();
		int mouseY = Mouse.getY();
		mouse = "Mouse Position: x(" + mouseX + ") y(" + mouseY + ")";
		
		if(input.isKeyPressed(Input.KEY_ENTER))
		{
			sbg.enterState(Engine.gameSelect);
		}
		
		if((mouseX > gc.getWidth()/2 - buttonWidth/2 && mouseX < gc.getWidth()/2 + buttonWidth/2) && 
				(mouseY > gc.getHeight()/2 + buttonHeight && mouseY < gc.getHeight()/2 + buttonHeight*2))
		{
			hoverSingleplayer = true;
			if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
			{
				sbg.enterState(Engine.gameSelect);
			}
		}
		else
			hoverSingleplayer = false;		
		
		if((mouseX > gc.getWidth()/2 - buttonWidth/2 && mouseX < gc.getWidth()/2 + buttonWidth/2) && 
				(mouseY > gc.getHeight()/2 && mouseY < gc.getHeight()/2 + buttonHeight))
		{
			hoverMultiplayer = true;
			if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
			{
				//sbg.enterState(Engine.multiplayerOptions);
			}
		}
		else
			hoverMultiplayer = false;	
		
		if((mouseX > gc.getWidth()/2 - buttonWidth/2 && mouseX < gc.getWidth()/2 + buttonWidth/2) && 
				(mouseY > gc.getHeight()/2 - buttonHeight && mouseY < gc.getHeight()/2))
		{
			hoverOptions = true;
			if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
			{
				//sbg.enterState(Engine.options);
			}
		}
		else
			hoverOptions = false;
		
		if((mouseX > gc.getWidth()/2 - buttonWidth/2 && mouseX < gc.getWidth()/2 + buttonWidth/2) && 
				(mouseY > gc.getHeight()/2 - buttonHeight*2 && mouseY < gc.getHeight()/2 - buttonHeight))
		{
			hoverExit = true;
			if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
			{
				System.exit(0);
			}
		}
		else
			hoverExit = false;
		
		
	}

	@Override
	public int getID() 
	{
		return 0;
	}

}
