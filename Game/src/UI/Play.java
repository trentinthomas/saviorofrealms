package UI;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import Util.GameSessionFactory;
import Util.Resources;
import Util.Window;

/*import Util.Resources;*/

public class Play extends BasicGameState {

	
	Image player;
	
	int playerx = Window.WIDTH/2 - 64/2;
	int playery = Window.HEIGHT/2 - 64/2;
	
	int inventoryWidth = 125;
	int inventoryHeight = 200;
	int itemSlotWidth = 200;
	int itemSlotHeight = 30;
	int guiPadding = 5;
	
	String mouse = "No input yet!";
	public Play(int state)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		

	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		player = GameSessionFactory.getGameSession().getPlayer().getImage();
		g.drawImage(player, playerx, playery);
		g.drawString(mouse, 10, 30);
		g.drawString("x: " + playerx + " y: " + playery, 30, 50);
		
		//inventory outline or something? could probably make this a picture of sorts
		g.drawRect(gc.getWidth() - (inventoryWidth + guiPadding), 
				   gc.getHeight() -(inventoryHeight + guiPadding), 
				   inventoryWidth, 
				   inventoryHeight);
		
		//item slots or something? this too could possibly be some sort of image
		g.drawRect(gc.getWidth()/2 - itemSlotWidth/2, 
				   gc.getHeight() - (itemSlotHeight + guiPadding), 
				   itemSlotWidth, 
				   itemSlotHeight);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		
		Input input = gc.getInput();
		int mouseX = Mouse.getX();
		int mouseY = Mouse.getY();
		mouse = "Mouse Position: x(" + mouseX + ") y(" + mouseY + ")";

		if(input.isKeyDown(Input.KEY_ESCAPE))
			sbg.enterState(Engine.paused);
		
		if(input.isKeyDown(Input.KEY_W)) //up
			if(playery > 0)
				playery -= GameSessionFactory.getGameSession().getPlayer().getSpeed();
		if(input.isKeyDown(Input.KEY_A)) //left
			if(playerx > 0)
				playerx -= GameSessionFactory.getGameSession().getPlayer().getSpeed();
		if(input.isKeyDown(Input.KEY_S)) //down
			if(playery < gc.getHeight() - player.getHeight())
				playery += GameSessionFactory.getGameSession().getPlayer().getSpeed();
		if(input.isKeyDown(Input.KEY_D)) //right
			if(playerx < gc.getWidth() - player.getWidth())
				playerx += GameSessionFactory.getGameSession().getPlayer().getSpeed();
	}

	@Override
	public int getID() 
	{
		return 3;
	}

}
