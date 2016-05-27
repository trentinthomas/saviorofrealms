package Entities;

import org.newdawn.slick.Image;

import Util.Resources;

public class Wizard extends Player
{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5572526824061693096L;
	
	public static final int DEFAULT_DAMAGE = 15;
	public static final int DEFAULT_HITPOINTS = 70;
	public static final int DEFAULT_DEFENCE = 10;
	public static final int DEFAULT_SPEED = 5;
	public static final int DEFAULT_XCOORD = 200;
	public static final int DEFAULT_YCOORD = 200;
	public static final int DEFAULT_WIDTH = 32;
	public static final int DEFAULT_HEIGHT = 64;
	
	
	private Wizard(int damage, int hitpoints, int defence, int speed, int xCoord, int yCoord, EntityType entityType, int width, int height) {
		super(damage, hitpoints, defence, speed, xCoord, yCoord, entityType, width, height, PlayerType.WIZARD);
	}
	
	public Wizard()
	{
		super(DEFAULT_DAMAGE, DEFAULT_HITPOINTS, DEFAULT_DEFENCE, DEFAULT_SPEED, DEFAULT_XCOORD, DEFAULT_YCOORD, EntityType.PLAYER, DEFAULT_WIDTH, DEFAULT_HEIGHT, PlayerType.WIZARD);
	}

	@Override
	public void attack(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	public Image getImage()
	{
		return Resources.getImage("wizard");
	}
}
