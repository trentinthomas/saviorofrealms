package Entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

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
	public static int DEFAULT_XCOORD = 10000;
	public static int DEFAULT_YCOORD = 10000;
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
	public void attack(float x, float y) {
		// TODO attack with barbarian
		
	}
	
	public Image getImage()
	{
		return Resources.getImage("barbarian");
	}

	@Override
	public void initAnimations() {
		
		ss = new SpriteSheet(getImage(), 64, 64);
		
		walking = new Animation[4];
		walking[UP]    = new Animation(ss, 1, 8,  8, 8,  true, ANIMSPEED, false);
		walking[LEFT]  = new Animation(ss, 0, 9,  8, 9,  true, ANIMSPEED, false);
		walking[DOWN]  = new Animation(ss, 1, 10, 8, 10, true, ANIMSPEED, false);
		walking[RIGHT] = new Animation(ss, 0, 11, 8, 11, true, ANIMSPEED, false);
		
		attacking = new Animation[4];
		attacking[UP]    = new Animation(ss, 1, 12, 5, 12,  true, ANIMSPEED, false);
		attacking[LEFT]  = new Animation(ss, 1, 13, 5, 13,  true, ANIMSPEED, false);
		attacking[DOWN]  = new Animation(ss, 1, 14, 5, 14,  true, ANIMSPEED, false);
		attacking[RIGHT] = new Animation(ss, 1, 15, 5, 15,  true, ANIMSPEED, false);
		
		lastAttackingFrame = 4;
		
		setCurrentAnimation(walking[DOWN]);
	}
	

}
