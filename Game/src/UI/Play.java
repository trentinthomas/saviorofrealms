package UI;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import Entities.Player.PlayerType;
import Util.GameSessionFactory;
import Util.Resources;
import Util.Window;

/*import Util.Resources;*/

public class Play extends BasicGameState {
	Animation [] walking;
/*	Animation walking_up;
	Animation walking_left;
	Animation walking_down;
	Animation walking_right;*/
	
	Image player;
	SpriteSheet ss;
	
	int playerx = Window.WIDTH/2 - 64/2;
	int playery = Window.HEIGHT/2 - 64/2;
	int playerHeight = 64;
	int playerWidth = 64;
	int inventoryWidth = 125;
	int inventoryHeight = 200;
	int itemSlotWidth = 200;
	int itemSlotHeight = 30;
	int guiPadding = 5;
	int animSpeed = 55; //walking speed
	int up = 0;
	int left = 1;
	int down = 2;
	int right = 3;
	boolean walkingUp = false;
	boolean walkingLeft = false;
	boolean walkingDown = false;
	boolean walkingRight = false;
	
	String mouse = "No input yet!";
	public Play(int state)
	{
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{	
		//TODO: Depending on the character selected or being used, use the proper sprite sheet
/*		if(GameSessionFactory.getGameSession().getPlayer().getPlayerType() == PlayerType.BARBARIAN)
			ss = new SpriteSheet(new Image("/res/spritesheets/barbarian_dagger.png"), 64, 64);
		if(GameSessionFactory.getGameSession().getPlayer().getPlayerType() == PlayerType.ARCHER)
			ss = new SpriteSheet(new Image("/res/spritesheets/archer.png"), 64, 64);
		if(GameSessionFactory.getGameSession().getPlayer().getPlayerType() == PlayerType.WIZARD)
			ss = new SpriteSheet(new Image("/res/spritesheets/wizard.png"), 64, 64);*/
			
		//this should fix the todo from above? maybe i hope? but it has an error.
		if(GameSessionFactory.hasGameSession())
		{
			ss = new SpriteSheet(GameSessionFactory.getGameSession().getPlayer().getImage(), 64, 64);
			
			walking = new Animation[4];
			walking[up]    = new Animation(ss, 1, 8,  8, 8,  true, animSpeed, false);
			walking[left]  = new Animation(ss, 0, 9,  8, 9,  true, animSpeed, false);
			walking[down]  = new Animation(ss, 1, 10, 8, 10, true, animSpeed, false);
			walking[right] = new Animation(ss, 0, 11, 8, 11, true, animSpeed, false);
		}

	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		ss = new SpriteSheet(GameSessionFactory.getGameSession().getPlayer().getImage(), 64, 64);
		//g.drawImage(player, playerx, playery);
		g.drawString(mouse, 10, 30);
		g.drawString("x: " + playerx + " y: " + playery, 30, 50);
		
		//TODO: This currently draws all of them if pushing all buttons,
		//		need to update this so that its only drawing the most recently
		//		pressed button + if no button pressed, use first animation of
		//		the spritesheet as a resting animation.
		if(walkingUp)
			walking[up].draw(playerx, playery);
		if(walkingLeft)
			walking[left].draw(playerx, playery);
		if(walkingDown)
			walking[down].draw(playerx, playery);
		if(walkingRight)
			walking[right].draw(playerx, playery);

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
		
		//TODO: 1) Change this so that it move the camara rather than the player
		//		byproduct of this is that the character stays in the middle of the screen
		if(input.isKeyDown(Input.KEY_LCONTROL)){
			GameSessionFactory.getGameSession().getPlayer().setSpeed(10);
/*			walking_up.setSpeed(animSpeed/5);
			walking_left.setSpeed(animSpeed/5);
			walking_down.setSpeed(animSpeed/5);
			walking_right.setSpeed(animSpeed/5);*/
		}
		else {
			GameSessionFactory.getGameSession().getPlayer().setSpeed(2);
/*			walking_up.setSpeed(animSpeed);
			walking_left.setSpeed(animSpeed);
			walking_down.setSpeed(animSpeed);
			walking_right.setSpeed(animSpeed);*/
		}
		
		if(input.isKeyDown(Input.KEY_W)) { //up
			if(playery > 0) {
				playery -= GameSessionFactory.getGameSession().getPlayer().getSpeed();
				walkingUp = true;
				walking[up].update(delta);
			}
		}
		else
			walkingUp = false;
				
		if(input.isKeyDown(Input.KEY_A)) {//left
			if(playerx > 0) {
				playerx -= GameSessionFactory.getGameSession().getPlayer().getSpeed();
				walkingLeft = true;
				walking[left].update(delta);
			}
		}
		else
			walkingLeft = false;
		
		if(input.isKeyDown(Input.KEY_S)) {//down
			if(playery < gc.getHeight() - playerHeight) {
				playery += GameSessionFactory.getGameSession().getPlayer().getSpeed();
				walkingDown = true;
				walking[down].update(delta);
			}
		}
		else
			walkingDown = false;
		
		if(input.isKeyDown(Input.KEY_D)) { //right
			if(playerx < gc.getWidth() - playerWidth) {
				playerx += GameSessionFactory.getGameSession().getPlayer().getSpeed();
				walkingRight = true;
				walking[right].update(delta);
			}
		}
		else
			walkingRight = false;
	}

	@Override
	public int getID() 
	{
		return 3;
	}

}
