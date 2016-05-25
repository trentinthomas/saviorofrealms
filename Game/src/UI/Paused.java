package UI;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Paused extends BasicGameState {

	Image up_resume;
	Image up_save;
	Image up_exit;
	
	Image down_resume;
	Image down_save;
	Image down_exit;
	
	boolean hoverResume;
	boolean hoverSave;
	boolean hoverExit;
	
	int buttonWidth  = 117;
	int buttonHeight = 43;
	
	String mouse = "No input yet!";
	
	public Paused(int state)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		up_resume = new Image("/res/buttons/up_resume.png");
		up_save = new Image("/res/buttons/up_save.png");
		up_exit = new Image("/res/buttons/up_exit.png");
		
		down_resume = new Image("/res/buttons/down_resume.png");
		down_save = new Image("/res/buttons/down_save.png");
		down_exit = new Image("/res/buttons/down_exit.png");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		if(!hoverResume)
			g.drawImage(up_resume, gc.getWidth()/2 - buttonWidth/2, gc.getHeight()/2 - buttonHeight);
		else
			g.drawImage(down_resume, gc.getWidth()/2 - buttonWidth/2, gc.getHeight()/2 - buttonHeight);
		
		if(!hoverSave)
			g.drawImage(up_save, gc.getWidth()/2 - buttonWidth/2, gc.getHeight()/2);
		else
			g.drawImage(down_save, gc.getWidth()/2 - buttonWidth/2, gc.getHeight()/2);
		
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
		
		if((mouseX > gc.getWidth()/2 - buttonWidth/2 && mouseX < gc.getWidth()/2 + buttonWidth/2) && 
				(mouseY > gc.getHeight()/2 && mouseY < gc.getHeight()/2 + buttonHeight))
		{
			//
			hoverResume = true;
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				//sbg.enterState(Engine.multiplayerOptions);
			}
		}
		else
			hoverResume = false;	
		
		if((mouseX > gc.getWidth()/2 - buttonWidth/2 && mouseX < gc.getWidth()/2 + buttonWidth/2) && 
				(mouseY > gc.getHeight()/2 - buttonHeight && mouseY < gc.getHeight()/2))
		{
			hoverSave = true;
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				//save
				//sbg.enterState(Engine.options);
			}
		}
		else
			hoverSave = false;
		
		if((mouseX > gc.getWidth()/2 - buttonWidth/2 && mouseX < gc.getWidth()/2 + buttonWidth/2) && 
				(mouseY > gc.getHeight()/2 - buttonHeight*2 && mouseY < gc.getHeight()/2 - buttonHeight))
		{
			hoverExit = true;
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				//save and exit to menu
				//save
				sbg.enterState(Engine.menu);
			}
		}
		else
			hoverExit = false;
	}

	@Override
	public int getID() 
	{
		return 4;
	}

}
