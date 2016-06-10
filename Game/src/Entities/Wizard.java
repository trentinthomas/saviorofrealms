package Entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import Entities.Entity.EntityType;
import Util.GameSessionFactory;
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
	public static final int DEFAULT_XCOORD = 10000;
	public static final int DEFAULT_YCOORD = 10000;
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
	public void attack(float x, float y) {
		// TODO make attack with wizards

	}
	
	public Image getImage()
	{
		return Resources.getImage("wizard");
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
		attacking[UP]    = new Animation(ss, 0, 0, 6, 0,  true, ANIMSPEED*2, false);
		attacking[LEFT]  = new Animation(ss, 0, 1, 6, 1,  true, ANIMSPEED*2, false);
		attacking[DOWN]  = new Animation(ss, 0, 2, 6, 2,  true, ANIMSPEED*2, false);
		attacking[RIGHT] = new Animation(ss, 0, 3, 6, 3,  true, ANIMSPEED*2, false);
		
		lastAttackingFrame = 5;
		
		setCurrentAnimation(walking[DOWN]);
	}
}
