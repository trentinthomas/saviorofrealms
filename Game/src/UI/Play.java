package UI;

import java.util.Vector;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import Entities.Player;
import Util.GameSessionFactory;
import Util.Window;

/*import Util.Resources;*/

public class Play extends BasicGameState {
	Animation [] walking;
	Animation [] attacking;
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
	boolean isAttacking = false;
	boolean stopAttacking = false;
	
	private boolean debug = false;

	private int lastKeyPressed = 2;
	private int lastKeyReleased = 2;	
	private Vector<Integer> keysPressed;

	private Shape attackAreaTop;
	private Shape attackAreaLeft;
	private Shape attackAreaBottom;
	private Shape attackAreaRight;
	
	String mouse = "No input yet!";
	private Player player;
	
	private TiledMap map;
	
	public Play(int state)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{	
		map = new TiledMap("res/testworldv4.tmx");
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
			
			attacking = new Animation[4];
			attacking[up]    = new Animation(ss, 1, 12, 5, 12,  true, animSpeed, false);
			attacking[left]  = new Animation(ss, 1, 13, 5, 13,  true, animSpeed, false);
			attacking[down]  = new Animation(ss, 1, 14, 5, 14,  true, animSpeed, false);
			attacking[right] = new Animation(ss, 1, 15, 5, 15,  true, animSpeed, false);
			
			keysPressed = new Vector<Integer>();
			for(int i = 0; i < 3; i++)
				keysPressed.add(i);
			
			float[] topTrianglePoints =  new float[]{Window.WIDTH, Window.HEIGHT, Window.WIDTH/2, Window.HEIGHT/2, 0, Window.HEIGHT};
			attackAreaTop = new Polygon(topTrianglePoints);
			
			float[] leftTrianglePoints = new float[]{0, 0, Window.WIDTH/2, Window.HEIGHT/2, 0, Window.HEIGHT};
			attackAreaLeft = new Polygon(leftTrianglePoints);
			
			float[] bottomTrianglePoints = new float[]{0, 0, Window.WIDTH/2, Window.HEIGHT/2, Window.WIDTH, 0};
			attackAreaBottom = new Polygon(bottomTrianglePoints);
			
			float[] rightTrianglePoints = new float[]{Window.WIDTH, Window.HEIGHT, Window.WIDTH/2, Window.HEIGHT/2, Window.WIDTH, 0};
			attackAreaRight = new Polygon(rightTrianglePoints);
			
		}
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		map.render(player.getxCoord(), player.getyCoord());
		if(lastKeyReleased == keysPressed.elementAt(1))
			g.drawLine(100, 100, 100, 200);

		ss = new SpriteSheet(player.getImage(), 64, 64);
		
		

		if( debug )
			drawDebug(g);
		drawHUD(gc, g);
		if(!isAttacking)
			drawWalkingAnimation();
		if(isAttacking)
			drawAttackingAnimation(Mouse.getX(), Mouse.getY());
			

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		
		Input input = gc.getInput();

		/**
		 * Used for debug purposes
		 */
		if( input.isKeyPressed(Input.KEY_F1))
		{
			debug = !debug;
		}

		int mouseX = Mouse.getX();
		int mouseY = Mouse.getY();

		
		/**
		 * Go to pause menu
		 */
		if(input.isKeyDown(Input.KEY_ESCAPE))
			sbg.enterState(Engine.paused);
		
		if( input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
		{
			isAttacking = true;
			player.setCurrentAnimation(attacking[lastKeyPressed]);
			
			if(attackAreaTop.contains(mouseX, mouseY)) {
				System.out.println("top area");
			}
			else if(attackAreaRight.contains(mouseX, mouseY)) {
				System.out.println("right area");
			}
			else if(attackAreaBottom.contains(mouseX, mouseY)) {
				System.out.println("bottom area");
			}
			else {
				System.out.println("left area");
			}
		}
		/**
		 * Update player movement based on key input
		 */
		if(!isAttacking)
			doPlayerMovement(input, delta);
		
/*		*//**
		 * Update player animation to attack
		 */
		if(isAttacking)
		{
			doPlayerAttack(input, delta);
		}

		
	}
	
	private void doPlayerMovement(Input input, int delta)
	{
		/**
		 * Make the player run
		 */
		//TODO: 1) Change this so that it move the camara rather than the player
		//		byproduct of this is that the character stays in the middle of the screen
		if(input.isKeyDown(Input.KEY_LCONTROL))
			player.setSpeed(10); 
		else
			player.setSpeed(2); 
		
		
		/**
		 * Test keyboard input for the last key pressed
		 */
		if(input.isKeyPressed(Input.KEY_W) || input.isKeyPressed(Input.KEY_UP)){
			lastKeyPressed = up;
		}
		
		if(input.isKeyPressed(Input.KEY_A) || input.isKeyPressed(Input.KEY_LEFT)){
			lastKeyPressed = left;
		}
		
		if(input.isKeyPressed(Input.KEY_S) || input.isKeyPressed(Input.KEY_DOWN)){
			lastKeyPressed = down;
		}
		
		if(input.isKeyPressed(Input.KEY_D) || input.isKeyPressed(Input.KEY_RIGHT)){
			lastKeyPressed = right;
		}
		
		
		
		/**
		 * Test keyboard input and set player velocity accordingly
		 */
		//------------------------------------------------------------------------- w pressed, move up
		if((input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP)) && !downKeyPressed) {
			upKeyPressed = true;
			player.setYVel(-player.getSpeed());
			player.getCurrentAnimation().update(delta);
			lastKeyReleased = up;
			addToKeysPressed(up);
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
			player.getCurrentAnimation().update(delta);
			lastKeyReleased = left;
			addToKeysPressed(left);
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
			player.getCurrentAnimation().update(delta);
			lastKeyReleased = down;
			addToKeysPressed(down);
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
			player.getCurrentAnimation().update(delta);
			lastKeyReleased = right;
			addToKeysPressed(right);
		}
		else {
			if(!leftKeyPressed) {
				player.setXVel(0);
			}

			rightKeyPressed = false;
		}
			
		//----------------------------------------------------------------------------
		
		player.move();
	}
	private void addToKeysPressed(int num)
	{
		try {
			
			if(keysPressed.isEmpty())
				keysPressed.add(num);
			
			else if(keysPressed.size() <= 3)
			{
				
				keysPressed.remove(0);
				keysPressed.add(num);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}
	private void doPlayerAttack(Input input, int delta)
	{
		player.getCurrentAnimation().update(delta);
		
		if( player.getCurrentAnimation().getFrame() == 4 || stopAttacking ) {
			
			stopAttacking = true;
			
			if(player.getCurrentAnimation().getFrame() == 0) {
				isAttacking = false;
				stopAttacking = false;
			}
		}
	}
	private void drawDebug(Graphics g)
	{
		g.draw(attackAreaTop);
		g.draw(attackAreaLeft);
		g.draw(attackAreaBottom);
		g.draw(attackAreaRight);
		
		g.drawString(mouse, 10, 30);
/*		mouse = "Mouse Position: x(" + Input + ") y(" + mouseY + ")";
*/		g.drawString("x: " + player.getCenterX() + " y: " + player.getCenterY(), 30, 50);
		g.drawString("velx: " + player.getXVel() + " vely: " + player.getYVel(), 30, 70);
		g.drawString("key1: " + keysPressed.elementAt(0) + "\nkey2: " + keysPressed.get(1) + 
					 "\nkey3: " + keysPressed.get(2), 30, 90);
	}
	
	private void drawHUD(GameContainer gc, Graphics g)
	{
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
	private void drawWalkingAnimation()
	{
		/**
		 * Do animations
		 */
		//----------------------------------------------------------------------------------------------------------normals

			if(player.getXVel() == 0 && player.getYVel() >  0) {      			//move down
				player.setCurrentAnimation(walking[down]);
			}
			
			else if(player.getXVel() == 0 && player.getYVel() <  0) {       		//move up
				player.setCurrentAnimation(walking[up]);
			}
			else if(player.getXVel() > 0 && player.getYVel() ==  0) {       		//move right
				player.setCurrentAnimation(walking[right]);
			}
			else if(player.getXVel() < 0 && player.getYVel() ==  0) {       		//move left
				player.setCurrentAnimation(walking[left]);
			}
		
			//-----------------------------------------------------------------------------------------------------diagonals

			else if(player.getXVel() < 0 && player.getYVel() < 0) { 						//up to left
			   if(lastKeyPressed == up)
			      player.setCurrentAnimation(walking[up]);
			   else if(lastKeyPressed == left)
			      player.setCurrentAnimation(walking[left]);
			   else
				   player.setCurrentAnimation(walking[keysPressed.elementAt(1)]);
			}

			else if(player.getXVel() > 0 && player.getYVel() < 0)  {    					 //up to right
			   if(lastKeyPressed == up)
				   player.setCurrentAnimation(walking[up]);
			   else if(lastKeyPressed == right)
				   player.setCurrentAnimation(walking[right]);
			   else
				   player.setCurrentAnimation(walking[keysPressed.elementAt(1)]);
			}

			else if(player.getXVel() < 0 && player.getYVel() > 0)  {     					//down to left
			   if(lastKeyPressed == down)
				   player.setCurrentAnimation(walking[down]);
			   else if(lastKeyPressed == left)
				   player.setCurrentAnimation(walking[left]);
			   else
				   player.setCurrentAnimation(walking[keysPressed.elementAt(1)]);
			}

			else if(player.getXVel() > 0 && player.getYVel() > 0)  {     					//down to right
			   if(lastKeyPressed == down)
				   player.setCurrentAnimation(walking[down]);
			   else if(lastKeyPressed == right)
				   player.setCurrentAnimation(walking[right]);
			   else
				   player.setCurrentAnimation(walking[keysPressed.elementAt(1)]);
			}
			
			else 
			{
				player.setCurrentAnimation(walking[keysPressed.elementAt(2)], 0);
				player.setCurrentAnimation(walking[keysPressed.elementAt(2)]);
			}
			player.getCurrentAnimation().draw(player.getxCoord(), player.getyCoord());
	}
	
	private void drawAttackingAnimation(int mouseX, int mouseY)
	{
		/*pauseAllAnimations();*/
					//mouse location implementation
		if(mouseY > player.getCenterY())       //attack up
			player.setCurrentAnimation(attacking[up]);
/*			lastKeyPressed = up;*/
		else if(mouseX < player.getCenterX()) //attack left
			player.setCurrentAnimation(attacking[left]);
/*			lastKeyPressed = left;
*/		else if(mouseY < player.getCenterY()) //attack down
/*			lastKeyPressed = down;
*/			player.setCurrentAnimation(attacking[down]);
		else if(mouseX > player.getCenterX()) //attack right
/*			lastKeyPressed = right;
*/			player.setCurrentAnimation(attacking[right]);

		
		
		
		
/*		if(keysPressed.elementAt(2) == up)
		{
			player.setCurrentAnimation(attacking[up]);
		}
		else if(keysPressed.elementAt(2) == left)
		{
			player.setCurrentAnimation(attacking[left]);
		}
		else if(keysPressed.elementAt(2) == down)
		{
			player.setCurrentAnimation(attacking[down]);
		}
		else if(keysPressed.elementAt(2) == right)
		{
			player.setCurrentAnimation(attacking[right]);
		}*/
		player.getCurrentAnimation().draw(player.getxCoord(), player.getyCoord());
	}

	
	@Override
	public int getID() 
	{
		return 3;
	}

	
}
