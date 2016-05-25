package UI;

import java.awt.event.MouseEvent;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import Entities.Archer;
import Entities.Barbarian;
import Entities.Player;
import Entities.Wizard;
import Util.GameSessionFactory;

public class PlayerSelect extends BasicGameState implements InputListener{

	Image up_barbarian;
	Image up_archer;
	Image up_wizard;
	
	Image down_barbarian;
	Image down_archer;
	Image down_wizard;
	
	int buttonWidth  = 117;
	int buttonHeight = 43;

	
	boolean hoverBarbarian = false;
	boolean hoverArcher    = false;
	boolean hoverWizard    = false;
	
	boolean allowedToClick = false;
	boolean test = false;
	
	public String mouse = "No input yet!";
	
	public PlayerSelect(int state)
	{
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		up_barbarian = new Image("/res/buttons/up_barbarian.png");
		up_archer    = new Image("/res/buttons/up_archer.png");
		up_wizard    = new Image("/res/buttons/up_wizard.png");
		
		down_barbarian = new Image("/res/buttons/down_barbarian.png");
		down_archer    = new Image("/res/buttons/down_archer.png");
		down_wizard    = new Image("/res/buttons/down_wizard.png");
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.drawString(mouse, 10, 30);
		
		if(test == true)
			g.setBackground(Color.red);
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
		
		if(!hoverBarbarian)
			g.drawImage(up_barbarian, gc.getWidth()/2 - buttonWidth*2, gc.getHeight()/2);
		else
			g.drawImage(down_barbarian, gc.getWidth()/2 - buttonWidth*2 , gc.getHeight()/2);
		
		if(!hoverArcher)
			g.drawImage(up_archer, gc.getWidth()/2 - buttonWidth/2, gc.getHeight()/2);
		else
			g.drawImage(down_archer, gc.getWidth()/2 - buttonWidth/2, gc.getHeight()/2);		
		
		if(!hoverWizard)
			g.drawImage(up_wizard, gc.getWidth()/2 + buttonWidth , gc.getHeight()/2);
		else
			g.drawImage(down_wizard, gc.getWidth()/2 + buttonWidth , gc.getHeight()/2);
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
		
		
		if((mouseX > gc.getWidth()/2 - buttonWidth*2 && mouseX < gc.getWidth()/2 - buttonWidth) && 
				(mouseY > gc.getHeight()/2 - buttonHeight && mouseY < gc.getHeight()/2))
		{
			hoverBarbarian = true;
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				//createworld
				allowedToClick = false;
				GameSessionFactory.createGameSession(1);
				GameSessionFactory.getGameSession().setPlayer(new Barbarian());
				sbg.enterState(Engine.play);
				sbg.getState(Engine.play).init(gc, sbg);
				sbg.enterState(Engine.play);
			}
		}
		else
			hoverBarbarian = false;		
		
		if((mouseX > gc.getWidth()/2 - buttonWidth/2 && mouseX < gc.getWidth()/2 + buttonWidth/2) && 
				(mouseY > gc.getHeight()/2 - buttonHeight && mouseY < gc.getHeight()/2))
		{
			hoverArcher = true;
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				//createworld
				allowedToClick = false;
				GameSessionFactory.createGameSession(1);
				GameSessionFactory.getGameSession().setPlayer(new Archer());
				//this.getState(play).init(gc, this);w
				sbg.getState(Engine.play).init(gc, sbg);
				sbg.enterState(Engine.play);
			}
		}
		else
			hoverArcher = false;
		
		if((mouseX > gc.getWidth()/2 + buttonWidth && mouseX < gc.getWidth()/2 + buttonWidth*2) && 
				(mouseY > gc.getHeight()/2 - buttonHeight && mouseY < gc.getHeight()/2))
		{
			hoverWizard = true;
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				//createworld
				allowedToClick = false;
				GameSessionFactory.createGameSession(1);
				GameSessionFactory.getGameSession().setPlayer(new Wizard());
				sbg.enterState(Engine.play);
				sbg.getState(Engine.play).init(gc, sbg);
				sbg.enterState(Engine.play);
			}
		}
		else
			hoverWizard = false;
		
	}

	@Override
	public int getID() 
	{
		return 2;
	}

}
