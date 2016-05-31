package UI;

/*import java.awt.Color;*/
import java.util.Vector;
import org.newdawn.slick.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import Entities.Entity;
import Entities.Player;
import Entities.Enemy.EnemyType;
import Entities.Entity.AnimationType;
import Util.Camera;
import Util.EnemyFactory;
import Util.GameSessionFactory;
import Util.Window;

/*import Util.Resources;*/

public class Play extends BasicGameState {
	
	private final int inventoryWidth = 125;
	private final int inventoryHeight = 200;
	private final int itemSlotWidth = 200;
	private final int itemSlotHeight = 30;
	private final int guiPadding = 5;
	private final int up = 0;
	private final int left = 1;
	private final int down = 2;
	private final int right = 3;
	private final int PIXEL_OFFSET = 10;
	private final double diagonalOffset = .7071;
	boolean upKeyPressed    = false;
	boolean leftKeyPressed  = false;
	boolean downKeyPressed  = false;
	boolean rightKeyPressed = false;
	boolean isAttacking = false;
	boolean stopAttacking = false;
	boolean movingDiagonal = false;
	private boolean debug = false;
	private float mouseX;
	private float mouseY;
	private float hudMouseX;
	private float hudMouseY;

	
	private int lastKeyPressed = 2;
	private int lastKeyReleased = 2;	
	private Vector<Integer> keysPressed;

	private float[] topTrianglePoints;
	private float[] rightTrianglePoints;
	private float[] leftTrianglePoints;
	private float[] bottomTrianglePoints;
	private float[] playerHitBoxPoints;
	
	private Shape attackAreaTop;
	private Shape attackAreaLeft;
	private Shape attackAreaBottom;
	private Shape attackAreaRight;
	private Shape playerHitBox;
	
	String mouse = "No input yet!";
	private Player player;
	
	private Camera cam;
	private TiledMap map;
	
	private boolean hoveringOverHUD = false;
	
	public Play(int state)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{	
		map = new TiledMap("res/testworldv4.tmx");
		cam = new Camera(map.getWidth() - Window.WIDTH, map.getHeight() - Window.HEIGHT);
		if(GameSessionFactory.hasGameSession())
		{
			player = GameSessionFactory.getGameSession().getPlayer();
			
			keysPressed = new Vector<Integer>();
			for(int i = 0; i < 3; i++)
				keysPressed.add(i);
			
			topTrianglePoints =  new float[]{Window.WIDTH, Window.HEIGHT, Window.WIDTH/2, Window.HEIGHT/2, 0, Window.HEIGHT};
			attackAreaTop = new Polygon(topTrianglePoints);
			
			leftTrianglePoints = new float[]{0, 0, Window.WIDTH/2, Window.HEIGHT/2, 0, Window.HEIGHT};
			attackAreaLeft = new Polygon(leftTrianglePoints);
			
			bottomTrianglePoints = new float[]{0, 0, Window.WIDTH/2, Window.HEIGHT/2, Window.WIDTH, 0};
			attackAreaBottom = new Polygon(bottomTrianglePoints);
			
			rightTrianglePoints = new float[]{Window.WIDTH, Window.HEIGHT, Window.WIDTH/2, Window.HEIGHT/2, Window.WIDTH, 0};
			attackAreaRight = new Polygon(rightTrianglePoints);
			

			playerHitBoxPoints = new float[]{player.getxCoord(), player.getyCoord(), (player.getxCoord()+player.getWidth()), 
					player.getyCoord(),player.getxCoord()+player.getWidth(), player.getyCoord()+player.getHeight(),
					player.getxCoord(), player.getyCoord() + player.getHeight() };
			playerHitBox = new Polygon(playerHitBoxPoints);
			
		}
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.setBackground(Color.black);
		
		cam.checkPosition();
		g.translate(-cam.getX(), -cam.getY());
		map.render(0, 0);
		
		updateAttackAreas();
		
		if( debug )
			drawDebug(g);
		drawHUD(gc, g);
		if(!isAttacking)
			drawWalkingAnimation();
		for(Entity e : GameSessionFactory.getGameSession().getEntities()) {
			e.getCurrentAnimation().draw(e.getxCoord(), e.getyCoord() + e.getHalfHeight());
		}
			

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

		mouseX = Mouse.getX() + cam.getX();
		mouseY = Mouse.getY() + cam.getY();

		
		/**
		 * Go to pause menu
		 */
		if(input.isKeyDown(Input.KEY_ESCAPE))
			sbg.enterState(Engine.paused);
		
		if( input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
		{
			isAttacking = true;			
			
			if(attackAreaTop.contains(mouseX, mouseY)) {
				player.setCurrentAnimation(player.getAnimation(AnimationType.ATTACKING, Entity.UP));
				player.setDirectionFacing(Entity.UP);
				System.out.println("top area");
			}
			else if(attackAreaRight.contains(mouseX, mouseY)) {
				player.setCurrentAnimation(player.getAnimation(AnimationType.ATTACKING, Entity.RIGHT));
				player.setDirectionFacing(Entity.RIGHT);
				System.out.println("right area");
			}
			else if(attackAreaBottom.contains(mouseX, mouseY)) {
				player.setCurrentAnimation(player.getAnimation(AnimationType.ATTACKING, Entity.DOWN));
				player.setDirectionFacing(Entity.DOWN);
				System.out.println("bottom area");
			}
			else {
				System.out.println("left area");
				player.setCurrentAnimation(player.getAnimation(AnimationType.ATTACKING, Entity.LEFT));
				player.setDirectionFacing(Entity.LEFT);
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
			doPlayerAttack(input, delta);

		for(Entity e : GameSessionFactory.getGameSession().getEntities())
		{
			if(e.getCurrentAnimation() != null)
				e.getCurrentAnimation().update(delta);
			e.move();
		}
		
	}
	
	private void updateAttackAreas()
	{
		//this is a little janky? possibly
		topTrianglePoints = new float[]{cam.getX() + Window.WIDTH, cam.getY() + Window.HEIGHT, 
										cam.getX() + Window.WIDTH/2, cam.getY() + Window.HEIGHT/2, 
										cam.getX() + 0, cam.getY() + Window.HEIGHT};
		leftTrianglePoints = new float[]{cam.getX() + 0, cam.getY() + 0, 
										cam.getX() + Window.WIDTH/2, cam.getY() + Window.HEIGHT/2, 
										cam.getX() + 0, cam.getY() + Window.HEIGHT};
		bottomTrianglePoints = new float[]{cam.getX() + 0, cam.getY() + 0, 
										cam.getX() + Window.WIDTH/2, cam.getY() + Window.HEIGHT/2, 
										cam.getX() + Window.WIDTH, cam.getY() + 0};
		rightTrianglePoints = new float[]{cam.getX() + Window.WIDTH, cam.getY() + Window.HEIGHT, 
										cam.getX() + Window.WIDTH/2, cam.getY() + Window.HEIGHT/2, 
										cam.getX() + Window.WIDTH, cam.getY() + 0};
		
		playerHitBoxPoints = new float[]{
				cam.getX() + player.getHalfWidth() + Window.WIDTH/2,
				cam.getY() - player.getHalfHeight() + Window.HEIGHT/2 + PIXEL_OFFSET, 
				cam.getX() + player.getHalfWidth() + Window.WIDTH/2,
				cam.getY() + player.getHalfHeight() + Window.HEIGHT/2,
				cam.getX() - player.getHalfWidth() + Window.WIDTH/2,
				cam.getY() + player.getHalfHeight() + Window.HEIGHT/2,
				cam.getX() - player.getHalfWidth() + Window.WIDTH/2,
				cam.getY() - player.getHalfHeight() + Window.HEIGHT/2 + PIXEL_OFFSET};
				
		//this is a little janky? possibly
		attackAreaTop = new Polygon(topTrianglePoints);
		attackAreaLeft = new Polygon(leftTrianglePoints);
		attackAreaBottom = new Polygon(bottomTrianglePoints);
		attackAreaRight = new Polygon(rightTrianglePoints);
		playerHitBox = new Polygon(playerHitBoxPoints);
	}
	
	private void doPlayerMovement(Input input, int delta)
	{
		boolean updateDelta = true;
		/**
		 * Make the player run
		 */
		//TODO: 1) Change this so that it move the camara rather than the player
		//		byproduct of this is that the character stays in the middle of the screen
		if(input.isKeyDown(Input.KEY_LCONTROL))
			player.setSpeed(10); 
		else
			player.setSpeed(1); 
		
		
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
		
		if((input.isKeyDown(Input.KEY_W) && input.isKeyDown(Input.KEY_D)) ||
				(input.isKeyDown(Input.KEY_W) && input.isKeyDown(Input.KEY_A)) ||
				(input.isKeyDown(Input.KEY_S) && input.isKeyDown(Input.KEY_D)) ||
				(input.isKeyDown(Input.KEY_S) && input.isKeyDown(Input.KEY_A)))
			movingDiagonal = true;
		else
			movingDiagonal = false;
		
		/**
		 * Test keyboard input and set player velocity accordingly
		 */
		//------------------------------------------------------------------------- w pressed, move up
		if((input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP)) && !downKeyPressed) {
			upKeyPressed = true;
			player.setYVel(movingDiagonal ? -player.getSpeed() * diagonalOffset :  -player.getSpeed());
			player.getCurrentAnimation().update(updateDelta ? delta : 0);
			updateDelta = false;
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
			player.setXVel(movingDiagonal ? -player.getSpeed() * diagonalOffset :  -player.getSpeed());
			player.getCurrentAnimation().update(updateDelta ? delta : 0);
			updateDelta = false;
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
			player.setYVel(movingDiagonal ? player.getSpeed() * diagonalOffset :  player.getSpeed());
			player.getCurrentAnimation().update(updateDelta ? delta : 0);
			updateDelta = false;
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
			player.setXVel(movingDiagonal ? player.getSpeed() * diagonalOffset :  player.getSpeed());
			player.getCurrentAnimation().update(updateDelta ? delta : 0);
			updateDelta = false;
			lastKeyReleased = right;
			addToKeysPressed(right);
		}
		else {
			if(!leftKeyPressed) {
				player.setXVel(0);
			}

			rightKeyPressed = false;
		}
		
		if((input.isKeyPressed(Input.KEY_N))) {
			EnemyFactory.spawnEnemy(EnemyType.GOBLIN, player.getxCoord() + Window.WIDTH, player.getyCoord() + Window.HEIGHT);
		}
			
		//----------------------------------------------------------------------------
		for(Entity e : GameSessionFactory.getGameSession().getEntities())
			e.move();
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
		for(Entity e : GameSessionFactory.getGameSession().getEntities())
			e.getCurrentAnimation().update(delta);
		
		if( player.getCurrentAnimation().getFrame() == player.getLastAttackingFrame() || stopAttacking ) {
			
			stopAttacking = true;
			
			if(player.getCurrentAnimation().getFrame() == 0) {
				player.attack(0,0); //TODO if we want to make it go towards where we clicked the mouse, we need to enter the mouse coords here.
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
		g.draw(playerHitBox);
		
		if(lastKeyReleased == keysPressed.elementAt(1))
			g.drawLine(cam.getX() + 100, cam.getY() + 100, cam.getX() + 100, cam.getY() + 200);
		
		g.drawString(mouse, cam.getX() + 10, cam.getY() + 30);
		mouse = "Mouse Position: x(" +  mouseX + ") y(" + mouseY + ")";
		g.drawString("x: " + player.getCenterX() + " y: " + player.getCenterY(), cam.getX() + 30, cam.getY() + 50);
		//g.drawString("player x: " + player.getxCoord() + " player y: " + player.getyCoord(), cam.getX() + 30, cam.getY() + 230);
		g.drawString("velx: " + player.getXVel() + " vely: " + player.getYVel(), cam.getX() + 30, cam.getY() + 70);
		g.drawString("key1: " + keysPressed.elementAt(0) + "\nkey2: " + keysPressed.get(1) + 
					 "\nkey3: " + keysPressed.get(2), cam.getX() + 30, cam.getY() + 90);
		g.drawString("camX: " + cam.getX() + " camY: " + cam.getY(), cam.getX() + 30, cam.getY() + 210);
		g.drawString("entities: " + GameSessionFactory.getGameSession().getEntities().size(), cam.getX() + 30, cam.getY() + 230);
	}
	
	private void drawHUD(GameContainer gc, Graphics g)
	{
		//inventory outline or something? could probably make this a picture of sorts
		g.drawRect(cam.getX() + (gc.getWidth() - (inventoryWidth + guiPadding)), 
				   cam.getY() + (gc.getHeight() -(inventoryHeight + guiPadding)), 
				   inventoryWidth, 
				   inventoryHeight);
		
		//width = 808
		//height = 500
		
		//item slots or something? this too could possibly be some sort of image
		g.drawRect(cam.getX() + (gc.getWidth()/2 - itemSlotWidth/2), 
				   cam.getY() + (gc.getHeight() - (itemSlotHeight + guiPadding)), 
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
				player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, Entity.DOWN));
			}
			else if(player.getXVel() == 0 && player.getYVel() <  0) {       		//move up
				player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, Entity.UP));
			}
			else if(player.getXVel() > 0 && player.getYVel() ==  0) {       		//move right
				player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, Entity.RIGHT));
			}
			else if(player.getXVel() < 0 && player.getYVel() ==  0) {       		//move left
				player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, Entity.LEFT));
			}
		
			//-----------------------------------------------------------------------------------------------------diagonals

			else if(player.getXVel() < 0 && player.getYVel() < 0) { 						//up to left
			   if(lastKeyPressed == up)
				   player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, Entity.UP));
			   else if(lastKeyPressed == left)
				   player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, Entity.LEFT));
			   else
				   player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, keysPressed.elementAt(1)));
			}

			else if(player.getXVel() > 0 && player.getYVel() < 0)  {    					 //up to right
			   if(lastKeyPressed == up)
				   player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, Entity.UP));
			   else if(lastKeyPressed == right)
				   player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, Entity.RIGHT));
			   else
			   {
				   player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, keysPressed.elementAt(1)));
			   }
			}

			else if(player.getXVel() < 0 && player.getYVel() > 0)  {     					//down to left
			   if(lastKeyPressed == down)
				   player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, Entity.DOWN));
			   else if(lastKeyPressed == left)
				   player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, Entity.LEFT));
			   else
				   player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, keysPressed.elementAt(1)));
			}

			else if(player.getXVel() > 0 && player.getYVel() > 0)  {     					//down to right
			   if(lastKeyPressed == down)
				   				   player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, Entity.DOWN));
			   else if(lastKeyPressed == right)
				   				   player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, Entity.RIGHT));
			   else
				   player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, keysPressed.elementAt(1)));
			}
			
			else 
			{

				player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, keysPressed.elementAt(2)), 0);
				player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, keysPressed.elementAt(2)));
			}
			

	}

	
	@Override
	public int getID() 
	{
		return 3;
	}

	
}
