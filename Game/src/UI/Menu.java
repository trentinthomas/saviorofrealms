package UI;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState {


	
	public String mouse = "No input yet!";
	
	public Menu(int state)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
/*		gc.setAlwaysRender(true);
		gc.setTargetFrameRate(60);
		gc.setMaximumLogicUpdateInterval(60);*/
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.drawString(mouse, 10, 30);
		g.drawString("Savior of Realms", 325, 100);
		g.drawRoundRect(325, 150, 100, 25, 10);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		Input input = gc.getInput();
		
		if(input.isKeyPressed(Input.KEY_ENTER))
		{
			sbg.enterState(Engine.play);
		}
		
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		
		if((xpos > 325 && xpos < 425) && (ypos > 325 && ypos < 350))
		{
			if(input.isMouseButtonDown(input.MOUSE_LEFT_BUTTON))
			{
				sbg.enterState(Engine.play);
			}
		}
		//debug
		mouse = "Mouse Position: x(" + xpos + ") y(" + ypos + ")";
		
		
	}

	@Override
	public int getID() 
	{
		return 0;
	}

}
