package UI;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import Entities.Player;
import Entities.Player.PlayerType;
import Util.GameSessionFactory;
import Util.Resources;
import Util.Window;

/*import Util.Resources;*/

public class Play extends BasicGameState {
	Animation [] walking;	
	SpriteSheet ss;
	
	private final int inventoryWidth = 125;
	private final int inventoryHeight = 200;
	private final int itemSlotWidth = 200;
	private final int itemSlotHeight = 30;
	private final int guiPadding = 5;
	private final int animSpeed = 55; //walking speed
	private final int up = 0;
	private final int left = 1;
	private final int down = 2;
	private final int right = 3;
	boolean walkingUp = false;
	boolean walkingLeft = false;
	boolean walkingDown = false;
	boolean walkingRight = false;
	private int lastKeyPressed = 2;
	
	String mouse = "No input yet!";
	private Player player;
	
	public Play(int state)
	{
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{	
		if(GameSessionFactory.hasGameSession())
		{
			if(player == null) {
				player = GameSessionFactory.getGameSession().getPlayer();
			}
			ss = new SpriteSheet(player.getImage(), 64, 64);
			
			walking = new Animation[4];
			walking[up]    = new Animation(ss, 1, 8,  8, 8,  true, animSpeed, false);
			walking[left]  = new Animation(ss, 0, 9,  8, 9,  true, animSpeed, false);
			walking[down]  = new Animation(ss, 1, 10, 8, 10, true, animSpeed, false);
			walking[right] = new Animation(ss, 0, 11, 8, 11, true, animSpeed, false);
		}

	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		
		ss = new SpriteSheet(player.getImage(), 64, 64);
		//g.drawImage(player, playerx, playery);
		g.drawString(mouse, 10, 30);
		g.drawString("x: " + player.getxCoord() + " y: " + player.getyCoord(), 30, 50);
		
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
		
		if(!player.isMoving())
		{
			walking[lastKeyPressed].draw(player.getxCoord(), player.getyCoord());
			walking[lastKeyPressed].setCurrentFrame(0);
		}
		else
		{
			//TODO: This currently draws all of them if pushing all buttons,
			//		need to update this so that its only drawing the most recently
			//		pressed button + if no button pressed, use first animadtion of
			//		the spritesheet as a resting animation.
			if(walkingUp)
				walking[up].draw(player.getxCoord(), player.getyCoord());
			else if(walkingLeft)
				walking[left].draw(player.getxCoord(), player.getyCoord());
			else if(walkingDown)
				walking[down].draw(player.getxCoord(), player.getyCoord());
			else if(walkingRight)
				walking[right].draw(player.getxCoord(), player.getyCoord());
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_D) || 
		   input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_S))
		{
			if(input.isKeyDown(Input.KEY_W))
				lastKeyPressed = up;
			else if(input.isKeyDown(Input.KEY_A))
				lastKeyPressed = left;
			else if(input.isKeyDown(Input.KEY_S))
				lastKeyPressed = down;
			else if(input.isKeyDown(Input.KEY_D))
				lastKeyPressed = right;

		}
		int mouseX = Mouse.getX();
		int mouseY = Mouse.getY();
		mouse = "Mouse Position: x(" + mouseX + ") y(" + mouseY + ")";


		if(input.isKeyDown(Input.KEY_ESCAPE))
			sbg.enterState(Engine.paused);
		
		//TODO: 1) Change this so that it move the camara rather than the player
		//		byproduct of this is that the character stays in the middle of the screen
		if(input.isKeyPressed(Input.KEY_LCONTROL)){
			player.setSpeed(10);
		}
		else {
			player.setSpeed(2);

		}		
		
		//----------------------------------------------------------------------------
		
		if(input.isKeyDown(Input.KEY_W)) { //up
			if(player.getyCoord() > 0) {
				player.setYVel(-player.getSpeed());
				walkingUp = true;
				walking[up].update(delta);
			}
		}
		else
		{
			if(!walkingDown)//and didn't press down then
				player.setYVel(0); //Set Y to 0.
			walkingUp = false;//Reset varible??
		}				
		
		//----------------------------------------------------------------------------
		
		if(input.isKeyDown(Input.KEY_A)) {//left
			if(player.getxCoord() > 0) {
				player.setXVel(-player.getSpeed());
				walkingLeft = true;
				walking[left].update(delta);
			}
		}
		else
		{
			if(!walkingRight)
				player.setXVel(0);
			walkingLeft = false;
		}
		
		//----------------------------------------------------------------------------
		
		if(input.isKeyDown(Input.KEY_S)) {//down
			if(player.getyCoord() < gc.getHeight() - player.getHeight()) {
				player.setYVel(player.getSpeed());
				walkingDown = true;
				walking[down].update(delta);
			}
		}
		else
		{
			if(!walkingUp)
				player.setYVel(0);
			walkingDown = false;
		}
		
		//----------------------------------------------------------------------------
		
		if(input.isKeyDown(Input.KEY_D)) { //right
			if(player.getxCoord() < gc.getWidth() - player.getWidth()) {
				player.setXVel(player.getSpeed());
				walkingRight = true;
				walking[right].update(delta);
			}
		}
		else
		{
			if(!walkingLeft)
				player.setXVel(0);
			walkingRight = false;
		}
		
		//----------------------------------------------------------------------------
		
		player.move();
	}

	@Override
	public int getID() 
	{
		return 3;
	}

}
