package UI;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class GameSelect extends BasicGameState {

	Image up_new;
	Image up_load;
	
	Image down_new;
	Image down_load;
	
	int buttonWidth  = 117;
	int buttonHeight = 43;

	
	boolean hoverNew1 = false;
	boolean hoverNew2 = false;
	boolean hoverNew3 = false;
	boolean hoverLoad1 = false;
	boolean hoverLoad2 = false;
	boolean hoverLoad3 = false;
	
	
	public String mouse = "No input yet!";
	
	public GameSelect(int state)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		up_new  = new Image("/res/buttons/up_new.png");
		up_load = new Image("/res/buttons/up_load.png");
		
		down_new  = new Image("/res/buttons/down_new.png");
		down_load = new Image("/res/buttons/down_load.png");

	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.drawString(mouse, 10, 30);
		
		//horizontal lines
/*		
		g.drawLine(0, gc.getHeight()/2 - buttonHeight*2, gc.getWidth(), gc.getHeight()/2 - buttonHeight*2);
		g.drawLine(0, gc.getHeight()/2 - buttonHeight, gc.getWidth(), gc.getHeight()/2 - buttonHeight); 
		g.drawLine(0, gc.getHeight()/2, gc.getWidth(), gc.getHeight()/2);
		g.drawLine(0, gc.getHeight()/2 + buttonHeight, gc.getWidth(), gc.getHeight()/2 + buttonHeight);
		g.drawLine(0, gc.getHeight()/2 + buttonHeight*2, gc.getWidth(), gc.getHeight()/2 + buttonHeight*2);
		
		//vertical lines
		g.setColor(Color.red);
		g.drawLine(gc.getWidth()/2, 0, gc.getWidth()/2, gc.getHeight()); //x
		g.drawLine(gc.getWidth()/2 + (buttonWidth + buttonWidth/2), 0, gc.getWidth()/2 + (buttonWidth + buttonWidth/2), gc.getHeight()); //y
		g.drawLine(gc.getWidth()/2 - (buttonWidth + buttonWidth/2), 0, gc.getWidth()/2 - (buttonWidth + buttonWidth/2), gc.getHeight()); //z		
		g.setColor(Color.green);
		
		g.drawLine(gc.getWidth()/2 - buttonWidth/2, 0, gc.getWidth()/2 - buttonWidth/2, gc.getHeight()); //x
		g.drawLine(gc.getWidth()/2 + buttonWidth/2, 0, gc.getWidth()/2 + buttonWidth/2, gc.getHeight()); //x
		
		g.setColor(Color.blue);
		g.drawLine(gc.getWidth()/2 + buttonWidth*2, 0, gc.getWidth()/2 + buttonWidth*2, gc.getHeight()); //y
		g.drawLine(gc.getWidth()/2 + (buttonWidth/2 + buttonWidth/2), 0, gc.getWidth()/2 + (buttonWidth/2 + buttonWidth/2), gc.getHeight()); //y good
		
		g.setColor(Color.yellow);
		g.drawLine(gc.getWidth()/2 - buttonWidth*2, 0, gc.getWidth()/2 - buttonWidth*2, gc.getHeight()); //y
		g.drawLine(gc.getWidth()/2 - (buttonWidth/2 + buttonWidth/2), 0, gc.getWidth()/2 - (buttonWidth/2 + buttonWidth/2), gc.getHeight()); //y good
		g.setColor(Color.white);*/
		
		if(!hoverNew1)
			g.drawImage(up_new, gc.getWidth()/2 - buttonWidth*2, gc.getHeight()/2);
		else
			g.drawImage(down_new, gc.getWidth()/2 - buttonWidth*2, gc.getHeight()/2);
		
		if(!hoverNew2)
			g.drawImage(up_new, gc.getWidth()/2 - buttonWidth/2, gc.getHeight()/2);
		else
			g.drawImage(down_new, gc.getWidth()/2 - buttonWidth/2, gc.getHeight()/2);
		
		if(!hoverNew3)
			g.drawImage(up_new, gc.getWidth()/2 + buttonWidth , gc.getHeight()/2);
		else
			g.drawImage(down_new, gc.getWidth()/2 + buttonWidth , gc.getHeight()/2);
		
		if(!hoverLoad1)
			g.drawImage(up_load, gc.getWidth()/2 - buttonWidth*2, gc.getHeight()/2 + buttonHeight);
		else
			g.drawImage(down_load, gc.getWidth()/2 - buttonWidth*2 , gc.getHeight()/2 + buttonHeight);
		
		if(!hoverLoad2)
			g.drawImage(up_load, gc.getWidth()/2 - buttonWidth/2, gc.getHeight()/2 + buttonHeight);
		else
			g.drawImage(down_load, gc.getWidth()/2 - buttonWidth/2 , gc.getHeight()/2 + buttonHeight);
		
		if(!hoverLoad3)
			g.drawImage(up_load, gc.getWidth()/2 + buttonWidth, gc.getHeight()/2 + buttonHeight);
		else
			g.drawImage(down_load, gc.getWidth()/2 + buttonWidth , gc.getHeight()/2 + buttonHeight);
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
			sbg.enterState(Engine.menu);
		}
		
		//new buttons
		
		if((mouseX > gc.getWidth()/2 - buttonWidth*2 && mouseX < gc.getWidth()/2 - buttonWidth) && 
				(mouseY > gc.getHeight()/2 - buttonHeight && mouseY < gc.getHeight()/2))
		{
			hoverNew1 = true;
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				sbg.enterState(Engine.playerSelect);
			}
		}
		else
			hoverNew1 = false;		
		
		if((mouseX > gc.getWidth()/2 - buttonWidth/2 && mouseX < gc.getWidth()/2 + buttonWidth/2) && 
				(mouseY > gc.getHeight()/2 - buttonHeight && mouseY < gc.getHeight()/2))
		{
			hoverNew2 = true;
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				sbg.enterState(Engine.playerSelect);
			}
		}
		else
			hoverNew2 = false;
		
		if((mouseX > gc.getWidth()/2 + buttonWidth && mouseX < gc.getWidth()/2 + buttonWidth*2) && 
				(mouseY > gc.getHeight()/2 - buttonHeight && mouseY < gc.getHeight()/2))
		{
			hoverNew3 = true;
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				sbg.enterState(Engine.playerSelect);
			}
		}
		else
			hoverNew3 = false;
		
		
		//load buttons
		
		if((mouseX > gc.getWidth()/2 - buttonWidth*2 && mouseX < gc.getWidth()/2 - buttonWidth) && 
				(mouseY > gc.getHeight()/2 - buttonHeight*2 && mouseY < gc.getHeight()/2 - buttonHeight))
		{
			hoverLoad1 = true;
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				//sbg.enterState(Engine.play);
			}
		}
		else
			hoverLoad1 = false;		
		
		if((mouseX > gc.getWidth()/2 - buttonWidth/2 && mouseX < gc.getWidth()/2 + buttonWidth/2) && 
				(mouseY > gc.getHeight()/2 - buttonHeight*2 && mouseY < gc.getHeight()/2 - buttonHeight))
		{
			hoverLoad2 = true;
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				//sbg.enterState(Engine.play);
			}
		}
		else
			hoverLoad2 = false;
		
		if((mouseX > gc.getWidth()/2 + buttonWidth && mouseX < gc.getWidth()/2 + buttonWidth*2) && 
				(mouseY > gc.getHeight()/2 - buttonHeight*2 && mouseY < gc.getHeight()/2 - buttonHeight))
		{
			hoverLoad3 = true;
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				//sbg.enterState(Engine.play);
			}
		}
		else
			hoverLoad3 = false;
		
	}

	@Override
	public int getID() 
	{
		return 1;
	}

}
