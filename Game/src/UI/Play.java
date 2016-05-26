package UI;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Entities.Player;
import Util.GameSessionFactory;

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
	boolean upKeyPressed    = false;
	boolean leftKeyPressed  = false;
	boolean downKeyPressed  = false;
	boolean rightKeyPressed = false;
	
	private int lastKeyPressed = 2;
	private int lastKeyReleased = 2;
	
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
		g.drawString("velx: " + player.getXVel() + " vely: " + player.getYVel(), 30, 70);
		
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

		/**
		 * Do animations
		 */
		//----------------------------------------------------------------------------------------------------------normals
			if(player.getXVel() == 0 && player.getYVel() >  0)      			//move down
				walking[down].draw(player.getxCoord(), player.getyCoord());
			
			else if(player.getXVel() == 0 && player.getYVel() <  0)       		//move up
				walking[up].draw(player.getxCoord(), player.getyCoord());
			
			else if(player.getXVel() > 0 && player.getYVel() ==  0)       		//move right
				walking[right].draw(player.getxCoord(), player.getyCoord());
			
			else if(player.getXVel() < 0 && player.getYVel() ==  0)       		//move left
				walking[left].draw(player.getxCoord(), player.getyCoord());

			//-----------------------------------------------------------------------------------------------------diagonals

			else if(player.getXVel() < 0 && player.getYVel() < 0) { 						//up to left
				   if(lastKeyPressed == up)
				      walking[up].draw(player.getxCoord(), player.getyCoord());
				   if(lastKeyPressed == left)
				      walking[left].draw(player.getxCoord(), player.getyCoord());
				}

			else if(player.getXVel() > 0 && player.getYVel() < 0)  {    					 //up to right
			   if(lastKeyPressed == up)
			      walking[up].draw(player.getxCoord(), player.getyCoord());
			   if(lastKeyPressed == right)
			      walking[right].draw(player.getxCoord(), player.getyCoord());
			}

			else if(player.getXVel() < 0 && player.getYVel() > 0)  {     					//down to left
			   if(lastKeyPressed == down)
			      walking[down].draw(player.getxCoord(), player.getyCoord());
			   if(lastKeyPressed == left)
			      walking[left].draw(player.getxCoord(), player.getyCoord());
			}

			else if(player.getXVel() > 0 && player.getYVel() > 0)  {     					//down to right
			   if(lastKeyPressed == down)
			      walking[down].draw(player.getxCoord(), player.getyCoord());
			   if(lastKeyPressed == right)
			      walking[right].draw(player.getxCoord(), player.getyCoord());
			}
			
			else 
			{
				walking[lastKeyReleased].setCurrentFrame(0);
				walking[lastKeyReleased].draw(player.getxCoord(), player.getyCoord());
			}
		//}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		
		Input input = gc.getInput();
		
		/**
		 * Used for debug purposes
		 */
		int mouseX = Mouse.getX();
		int mouseY = Mouse.getY();
		mouse = "Mouse Position: x(" + mouseX + ") y(" + mouseY + ")";
		
		/**
		 * Go to pause menu
		 */
		if(input.isKeyDown(Input.KEY_ESCAPE))
			sbg.enterState(Engine.paused);
		
		/**
		 * Make the player run
		 */
		//TODO: 1) Change this so that it move the camara rather than the player
		//		byproduct of this is that the character stays in the middle of the screen
		if(input.isKeyDown(Input.KEY_LCONTROL))  { player.setSpeed(10); }
		else { player.setSpeed(2); }
		
		
		/**
		 * Test keyboard input for the last key pressed
		 */
		if(input.isKeyPressed(Input.KEY_W) || input.isKeyPressed(Input.KEY_UP))
			lastKeyPressed = up;
		if(input.isKeyPressed(Input.KEY_A) || input.isKeyPressed(Input.KEY_LEFT))
			lastKeyPressed = left;
		if(input.isKeyPressed(Input.KEY_S) || input.isKeyPressed(Input.KEY_DOWN))
			lastKeyPressed = down;
		if(input.isKeyPressed(Input.KEY_D) || input.isKeyPressed(Input.KEY_RIGHT))
			lastKeyPressed = right;
		
		
		
		/**
		 * Test keyboard input and set player velocity accordingly
		 */
		//------------------------------------------------------------------------- w pressed, move up
		if((input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP)) && !downKeyPressed) {
			upKeyPressed = true;
			player.setYVel(-player.getSpeed());
			walking[up].update(delta);
			lastKeyReleased = up;
		}
		else { // key not down			
			if(!downKeyPressed)
				player.setYVel(0);
			
			upKeyPressed = false;
		}
		
		//------------------------------------------------------------------------- a pressed, move left
		if((input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)) && !rightKeyPressed) {
			leftKeyPressed = true;
			player.setXVel(-player.getSpeed());
			walking[left].update(delta);
			lastKeyReleased = left;
		}
		else {
			if(!rightKeyPressed)
				player.setXVel(0);

			leftKeyPressed = false;
		}
		
		//------------------------------------------------------------------------- s pressed, move down
		if((input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN)) && !upKeyPressed) {
			downKeyPressed = true;
			player.setYVel(player.getSpeed());
			walking[down].update(delta);
			lastKeyReleased = down;
		}
		else { // key not down
			
			if(!upKeyPressed)
				player.setYVel(0);
			
			downKeyPressed = false;
		}
		
		//------------------------------------------------------------------------- d pressed, move right
		if((input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)) && !leftKeyPressed) {
			rightKeyPressed = true;
			player.setXVel(player.getSpeed());
			walking[right].update(delta);
			lastKeyReleased = right;
		}
		else {
			if(!leftKeyPressed)
				player.setXVel(0);

			rightKeyPressed = false;
		}


		/**
		 * Test keyboard input for the last key pressed
		 */
		if(input.isKeyPressed(Input.KEY_W))
			lastKeyPressed = up;
		if(input.isKeyPressed(Input.KEY_A))
			lastKeyPressed = left;
		if(input.isKeyPressed(Input.KEY_S))
			lastKeyPressed = down;
		if(input.isKeyPressed(Input.KEY_D))
			lastKeyPressed = right;
		
			
		
		
		
		//----------------------------------------------------------------------------
		
		player.move();

	}

	@Override
	public int getID() 
	{
		return 3;
	}

	
}
