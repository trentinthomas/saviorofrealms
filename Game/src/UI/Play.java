package UI;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/*import Util.Resources;*/

public class Play extends BasicGameState {

	
	Image player;
	int playerx = 200;
	int playery = 200;
	
	public Play(int state)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		player = new Image("/res/character.png");
		//player = Resources.getSpriteImage("wizard", 1, 1);

	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.drawImage(player, playerx, playery);
		g.drawString("x: " + playerx + " y: " + playery, 30, 30);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		
		Input input = gc.getInput();
		if(input.isMouseButtonDown(input.MOUSE_RIGHT_BUTTON))
			sbg.enterState(Engine.menu);
		
		if(input.isKeyDown(Input.KEY_W)) //up
			if(playery > 0)
				playery -= 10;
		if(input.isKeyDown(Input.KEY_A)) //left
			if(playerx > 0)
				playerx -= 10;
		if(input.isKeyDown(Input.KEY_S)) //down
			if(playery < gc.getHeight() - player.getHeight())
				playery += 10;
		if(input.isKeyDown(Input.KEY_D)) //right
			if(playerx < gc.getWidth() - player.getWidth())
				playerx += 10;
	}

	@Override
	public int getID() 
	{
		return 3;
	}

}
