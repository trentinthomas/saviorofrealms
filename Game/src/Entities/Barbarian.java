package Entities;

import org.newdawn.slick.Image;

import Util.Resources;

/**
 * Barbarian is a class the Player can control. Barbarians have more hitpoints and defence than normal, and
 * will be melee based.
 * @author Trentin
 *
 */
public class Barbarian extends Player
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6129474299759272536L;
	
	public static int DEFAULT_DAMAGE = 10;
	public static int DEFAULT_HITPOINTS = 100;
	public static int DEFAULT_DEFENCE = 10;
	public static int DEFAULT_SPEED = 3;
	public static int DEFAULT_XCOORD = 200;
	public static int DEFAULT_YCOORD = 200;
	public static int DEFAULT_WIDTH = 32;
	public static int DEFAULT_HEIGHT = 64;
	
	/**
	 * If the player already has a game and loads one, it calls this.
	 * @param damage
	 * @param hitpoints
	 * @param defence
	 * @param speed
	 * @param xCoord
	 * @param yCoord
	 * @param entityType
	 * @param width
	 * @param height
	 */
	private Barbarian(int damage, int hitpoints, int defence, int speed, int xCoord, int yCoord, EntityType entityType, int width, int height) {
		super(damage, hitpoints, defence, speed, xCoord, yCoord, entityType, width, height, PlayerType.BARBARIAN);
	}
	
	/**
	 * Creates a new Barbarian with default stats.
	 */
	public Barbarian()
	{
		super(DEFAULT_DAMAGE, DEFAULT_HITPOINTS, DEFAULT_DEFENCE, DEFAULT_SPEED, DEFAULT_XCOORD, DEFAULT_YCOORD, EntityType.PLAYER, DEFAULT_WIDTH, DEFAULT_HEIGHT, PlayerType.BARBARIAN);
	}
	

	@Override
	public void attack(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	public Image getImage()
	{
		return Resources.getImage("barbarian");
	}
	

}
