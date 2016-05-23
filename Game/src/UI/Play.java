package UI;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import Util.Resources;

public class Play extends BasicGameState {

	
	Image player;
	int playerx = 200;
	int playery = 200;
	
	public Play(int state)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		//player = new Image("/res/character.png");
		player = Resources.getSpriteImage("wizard", 1, 1);
/*		gc.setTargetFrameRate(120);
		gc.setMaximumLogicUpdateInterval(120);*/
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.drawString("In the play state", 200, 100);
		g.drawImage(player, playerx, playery);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		
		Input input = gc.getInput();
		if(input.isMouseButtonDown(input.MOUSE_RIGHT_BUTTON))
			sbg.enterState(Engine.menu);
		
		if(input.isKeyDown(Input.KEY_W)) //up
			playery -= 1;
		if(input.isKeyDown(Input.KEY_A)) //down
			playerx -= 1;
		if(input.isKeyDown(Input.KEY_S)) //left
			playery += 1;
		if(input.isKeyDown(Input.KEY_D)) //right
			playerx += 1;
	}

	@Override
	public int getID() 
	{
		return 3;
	}

}
