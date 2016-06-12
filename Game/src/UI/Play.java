package UI;

import java.util.List;
/*import java.awt.Color;*/
import java.util.Vector;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Entities.Enemy;
import Entities.Enemy.EnemyType;
import Entities.Entity;
import Entities.Entity.AnimationType;
import Entities.Entity.EntityType;
import Entities.Player;
import Entities.Player.PlayerType;
import Items.Item;
import Util.Camera;
import Util.EnemyFactory;
import Util.GameSessionFactory;
import Util.Resources;
import Util.Window;
import saviorOfRealms.Tiles.Tile;
import saviorOfRealms.WorldGeneration.HexMapGenerator;
import saviorOfRealms.errorHandling.EntityDeadException;


public class Play extends BasicGameState {
	
	private final int inventoryWidth = 195;
	private final int inventoryHeight = 240;
	private final int itemSlotWidth = 465;
	private final int itemSlotHeight = 60;
	private final int healthWidth = 190;
	private final int healthHeight = 25;
	private final int energyWidth = 190;
	private final int energyHeight = 25;
	private final int experienceWidth = 190;
	private final int experienceHeight = 25;
	private final int currentLevelHeight = 85;
	private final int currentLevelWidth = 85;
	private final int guiPadding = 5;
	private final int up = 0;
	private final int left = 1;
	private final int down = 2;
	private final int right = 3;
	public final static int PIXEL_OFFSET = 10;
	private final double diagonalOffset = .7071;
	boolean upKeyPressed;
	boolean leftKeyPressed;
	boolean downKeyPressed;
	boolean rightKeyPressed;
	boolean isAttacking;
	boolean stopAttacking;
	boolean movingDiagonal;
	boolean debug;
	private boolean showInventory;
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
	
	Image inventory_20;
	Image quickSlots;
	Image healthBar;
	Image energyBar;
	Image experienceBar;
	Image currentLevel;
	
	boolean paused = false;
	
	String mouse = "No input yet!";
	private Player player;
	
	private Camera cam;
	//private TiledMap map;
	private int[][] map;
	
	float playerX;
	float playerY;
	float maxMapX;
	float maxMapY;
	
	private boolean hoveringOverHUD = false;
	
	public Play(int state)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{	
		//map = new TiledMap("res/testworldv4.tmx");
		map = new HexMapGenerator().getDiamondSquare();
		cam = new Camera();
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
		
		inventory_20 = new Image("/res/hud/inventory_20.png");
		quickSlots = new Image("/res/hud/quickSlots.png");
		healthBar = new Image("/res/hud/healthBar.png");
		energyBar = new Image("/res/hud/energyBar.png");
		/*experienceBar = new Image("/res/hud/experienceBar.png");*/
		currentLevel = new Image("/res/hud/currentLevel.png");
		
		isAttacking = false;
		stopAttacking = false;
		movingDiagonal = false;
		showInventory = false;
		
		upKeyPressed    = false;
		leftKeyPressed  = false;
		downKeyPressed  = false;
		rightKeyPressed = false;
		
		movingDiagonal = false;
		debug = false;
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{

		g.setBackground(Color.black);
		
		cam.checkPosition();
		g.translate(-cam.getX(), -cam.getY());
		//map.render(0, 0);
		
		drawMap(g);		
		
		
		updateAttackAreas();
		
		if( debug )
			drawDebug(g);
		
		if(!isAttacking)
			drawWalkingAnimation();
		
		for(Entity e : GameSessionFactory.getGameSession().getEntities()) {
			e.getCurrentAnimation().draw(e.getxCoord(), e.getyCoord() + e.getHalfHeight());
		}
		for(Item item : GameSessionFactory.getGameSession().getItemsOnGround()) {
			g.drawImage(item.getItemImage(), item.getX(), item.getY());
		}
		
		drawHUD(gc, g);
			

	}



	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		
		Input input = gc.getInput();

		/**
		 * Used for debug purposes
		 */
		if( input.isKeyPressed(Input.KEY_F1))
			debug = !debug;

		mouseX = Mouse.getX() + cam.getX();
		mouseY = Mouse.getY() + cam.getY();

		if( input.isKeyDown(Input.KEY_H))
			player.addHitpoints(3);
		if( input.isKeyDown(Input.KEY_J))
			try {
				if(player.getCurrentHitpoints() > 0)
				player.subtractHitpoints(3);
			} catch (EntityDeadException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		/**
		 * Go to pause menu
		 */
		if(input.isKeyDown(Input.KEY_ESCAPE))
		{
			sbg.enterState(Engine.paused);
		}
		
		if( input.isMousePressed(Input.MOUSE_LEFT_BUTTON) && isAttacking != true)
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
		if( input.isKeyPressed( Input.KEY_SPACE ) ) {
			List<Item> items = GameSessionFactory.getGameSession().getItemsOnGround();
			for( Item item : items ) {
				if( player.canPickUp(item) ) {
					player.getInventory().addItem(item);
					GameSessionFactory.getGameSession().removeItemFromGround(item);
				}
			}
			
		}
		if( input.isKeyPressed( Input.KEY_B ) ) {
			showInventory = !showInventory;
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
			if(e.getEntityType() != Entity.EntityType.PLAYER)
				e.move();
			else
			{
				if(!isAttacking)
				{
					e.move();
				}
			}
		}
		
		checkForCollisions();
		GameSessionFactory.getGameSession().removeItemsFromGround();
	}
	
	private void drawMap(Graphics g) {
		playerX = Math.abs(player.getxCoord() - Window.WIDTH) / (Tile.TILE_WIDTH);
		playerY = Math.abs(player.getyCoord() - Window.HEIGHT) / (Tile.TILE_HEIGHT); 
		
		//TODO cant have Absolute value here always. - chunk idea should solve this
		maxMapX = Math.abs(Window.WIDTH / (Tile.TILE_WIDTH/2));
		maxMapY = Math.abs(Window.HEIGHT / (Tile.TILE_HEIGHT/2));
		
		
//		images.put("tiles_grass", loadSubImage("/res/tileset.png", 0, 0));
//		images.put("tiles_deep_water", loadSubImage("/res/tileset.png", 32, 0));
//		images.put("tiles_water", loadSubImage("/res/tileset.png", 64, 0));
//		images.put("tiles_sand", loadSubImage("/res/tileset.png", 96, 0));
//		images.put("tiles_stone", loadSubImage("/res/tileset.png", 0, 32));
//		images.put("tiles_lava", loadSubImage("/res/tileset.png", 32, 32));
		
		for(int column = (int)playerX; column < (int)maxMapX + playerX; column++) {
			for(int row = (int) playerY; row < map[column].length; row++) {
				switch(map[column][row])
				{
				case 0: //Deep water
					g.drawImage(Resources.getImage("tiles_deep_water"), column*Tile.TILE_WIDTH, row*Tile.TILE_HEIGHT);
					break;
				case 1: //Regular water
					g.drawImage(Resources.getImage("tiles_water"), column*Tile.TILE_WIDTH, row*Tile.TILE_HEIGHT);
					break;
				case 2: //sand
					g.drawImage(Resources.getImage("tiles_sand"), column*Tile.TILE_WIDTH, row*Tile.TILE_HEIGHT);
					break;
				case 3: //grass
					g.drawImage(Resources.getImage("tiles_grass"), column*Tile.TILE_WIDTH, row*Tile.TILE_HEIGHT);
					break;
				case 7: //stone
					g.drawImage(Resources.getImage("tiles_stone"), column*Tile.TILE_WIDTH, row*Tile.TILE_HEIGHT);
					break;
				case 8: //lava
					g.drawImage(Resources.getImage("tiles_lava"), column*Tile.TILE_WIDTH, row*Tile.TILE_HEIGHT);
					break;
				default: //grass
					g.drawImage(Resources.getImage("tiles_grass"), column*Tile.TILE_WIDTH, row*Tile.TILE_HEIGHT);
					break;
				}
			}
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
				
		//this is a little janky? possibly
		attackAreaTop = new Polygon(topTrianglePoints);
		attackAreaLeft = new Polygon(leftTrianglePoints);
		attackAreaBottom = new Polygon(bottomTrianglePoints);
		attackAreaRight = new Polygon(rightTrianglePoints);
		playerHitBox = new Polygon(player.getHitBox(cam));
	}
	
	private void doPlayerMovement(Input input, int delta)
	{
		/**
		 * Make the player run
		 */
		//TODO: 1) Change this so that it move the camara rather than the player
		//		byproduct of this is that the character stays in the middle of the screen
		if(input.isKeyDown(Input.KEY_LCONTROL))
			player.setSpeed(20); 
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
		
		if((input.isKeyPressed(Input.KEY_Q))) {
			for(Entity e : GameSessionFactory.getGameSession().getEntities())
			{
				System.out.println(e.getClass().getName() + " entity id is: " + e.getEntityId());
			}
		}
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
		
		if( player.getCurrentAnimation().getFrame() == player.getLastAttackingFrame() || stopAttacking ) {
			
			stopAttacking = true;
			

			if(player.getCurrentAnimation().getFrame() == 0) {
				if(player.getPlayerType() == PlayerType.WIZARD)
					player.attack(mouseX, mouseY);
				else
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
		g.draw(player.pickupRadius);
		
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
		g.drawString("playerX: " + playerX, cam.getX() + 30, cam.getY() + 250);
		g.drawString("playerY: " + playerY, cam.getX() + 30, cam.getY() + 270);
		g.drawString("maxMapX: " + maxMapX, cam.getX() + 30, cam.getY() + 290);
		g.drawString("maxMapY: " + maxMapX, cam.getX() + 30, cam.getY() + 310);
		g.drawString("mapSizeX: " + map[0].length, cam.getX() + 30, cam.getY() + 330);
		g.drawString("mapSizeY: " + map.length, cam.getX() + 30, cam.getY() + 350);
		g.drawString("currentHP: " + player.getCurrentHitpoints(), cam.getX() + 30, cam.getY() + 370);
		g.drawString("maxHP: " + player.getMaxHitpoints(), cam.getX() + 30, cam.getY() + 390);
		g.drawString("hp percent: " + player.getHitpointsPerecentage(), cam.getX() + 30, cam.getY() + 410);
	}
	
	private void drawHUD(GameContainer gc, Graphics g)
	{
		//inventory outline or something? could probably make this a picture of sorts
		if(showInventory)
		{
			drawInventory(g);
		}
		
		//width = 808
		//height = 500
		
		//item slots or something? this too could possibly be some sort of image
		g.drawImage( quickSlots,
				cam.getX() + (gc.getWidth()/2 - itemSlotWidth/2), 
				cam.getY() + (gc.getHeight() - (itemSlotHeight + guiPadding)));
		
		g.drawImage( currentLevel,
				cam.getX() + (gc.getWidth()/2 - currentLevelWidth/2), 
				cam.getY() + (gc.getHeight() - (itemSlotHeight + currentLevelHeight)));

		
/*		g.drawImage( healthBar,
				cam.getX() + (gc.getWidth()/2 - itemSlotWidth/2), 
				cam.getY() + (gc.getHeight() - (itemSlotHeight + energyHeight + healthHeight - guiPadding)));*/
		
		g.drawImage( healthBar,
				cam.getX() + (gc.getWidth()/2 - itemSlotWidth/2), 
				cam.getY() + (gc.getHeight() - (itemSlotHeight + energyHeight)));
		g.setColor(Color.green);
		if(player.getHitpointsPerecentage() < .75)
			g.setColor(Color.yellow);
		if(player.getHitpointsPerecentage() < .5)
			g.setColor(Color.orange);
		if(player.getHitpointsPerecentage() < .25)
			g.setColor(Color.red);
		if(player.getCurrentHitpoints() > 0)
			g.fillRect(
				cam.getX() + guiPadding + (gc.getWidth()/2 - itemSlotWidth/2), 
				cam.getY() + guiPadding + (gc.getHeight() - (itemSlotHeight + energyHeight)),
				(int)(player.getHitpointsPerecentage() * (healthWidth - 5)),
				15);
		
		
		//TODO: See if there is a reason why this is 
		g.drawImage( energyBar,
				cam.getX() + (gc.getWidth()/2 + (-experienceWidth + itemSlotWidth/2 + -guiPadding + 1)), 
				cam.getY() + (gc.getHeight() - (itemSlotHeight + experienceHeight)));
		g.setColor(Color.white);
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
				   player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, keysPressed.elementAt(0)));
			}

			else if(player.getXVel() > 0 && player.getYVel() < 0)  {    					 //up to right
			   if(lastKeyPressed == up)
				   player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, Entity.UP));
			   else if(lastKeyPressed == right)
				   player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, Entity.RIGHT));
			   else
			   {
				   player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, keysPressed.elementAt(0)));
			   }
			}

			else if(player.getXVel() < 0 && player.getYVel() > 0)  {     					//down to left
			   if(lastKeyPressed == down)
				   player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, Entity.DOWN));
			   else if(lastKeyPressed == left)
				   player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, Entity.LEFT));
			   else
				   player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, keysPressed.elementAt(0)));
			}

			else if(player.getXVel() > 0 && player.getYVel() > 0)  {     					//down to right
			   if(lastKeyPressed == down)
				   				   player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, Entity.DOWN));
			   else if(lastKeyPressed == right)
				   				   player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, Entity.RIGHT));
			   else
				   player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, keysPressed.elementAt(0)));
			}
			
			else 
			{
				player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, keysPressed.elementAt(2)), 0);
				player.setCurrentAnimation(player.getAnimation(AnimationType.MOVEMENT, keysPressed.elementAt(2)));
			}
			

	}
	
	public void checkForCollisions() {
		List<Entity> removeList = GameSessionFactory.getGameSession().getRemoveListEntities();
		for(Entity e : GameSessionFactory.getGameSession().getEntities()) {
			for(Entity ee : GameSessionFactory.getGameSession().getEntities() ) {
				if(e.testCollision(ee)) {
					
					if(e.getEntityType() != ee.getEntityType()) System.out.println(e.getClass().getName() + " collided with " + ee.getClass().getName());
					
					if( e.getEntityType().equals(EntityType.PROJECTILE) && ee.getEntityType().equals(EntityType.ENEMY))
					{
						try {
							ee.subtractHitpoints(e.getDamage());
						} catch (EntityDeadException e1) {
							Enemy enemy = (Enemy)ee;
							enemy.calculateDropItem();
							removeList.add(ee);
						}
						removeList.add(e);
					}
					else if( ee.getEntityType().equals(EntityType.PROJECTILE) && e.getEntityType().equals(EntityType.ENEMY))
					{
						try {
							e.subtractHitpoints(ee.getDamage());
						} catch (EntityDeadException e1) {
							Enemy enemy = (Enemy)e;
							enemy.calculateDropItem();
							removeList.add(e);
						}
						removeList.add(ee);
					}
					else if( e.getEntityType().equals(EntityType.PLAYER) )
					{
						try {
							if(e.getCurrentHitpoints() > 0)
								e.subtractHitpoints(ee.getDamage());
						} catch (EntityDeadException e1) {
							System.out.println("GAMEOVER");
						}
					}
					else if( ee.getEntityType().equals(EntityType.PLAYER) )
					{
						try {
							e.subtractHitpoints(ee.getDamage());
						} catch (EntityDeadException e1) {
							System.out.println("GAMEOVER");
						}
					}
					else if( e.getEntityType().equals(EntityType.ENEMY) && !ee.getEntityType().equals(EntityType.ENEMY) ) {
						try {
							e.subtractHitpoints( ee.getDamage() );
						} catch (EntityDeadException e1) {
							Enemy enemy = (Enemy)e;
							enemy.calculateDropItem();
							removeList.add(e);
						}
					}
					else if( ee.getEntityType().equals(EntityType.ENEMY) && !e.getEntityType().equals(EntityType.ENEMY) ) {
						try {
							ee.subtractHitpoints( e.getDamage() );
						} catch (EntityDeadException e1) {
							Enemy enemy = (Enemy)ee;
							enemy.calculateDropItem();
							removeList.add(ee);
						}
					}
				}
			}
		}
		
		GameSessionFactory.getGameSession().getEntities().removeAll(removeList);
	}

	
	@Override
	public int getID() 
	{
		return 3;
	}
	
	private void drawInventory(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage( inventory_20,
				cam.getX() + (Window.WIDTH - (inventoryWidth + guiPadding)), 
				cam.getY() + (Window.HEIGHT -(inventoryHeight + guiPadding)));
		
		player.getInventory().drawInventoryItems(g, cam);
	}

	
}
