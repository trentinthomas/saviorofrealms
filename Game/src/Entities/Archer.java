package Entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import Util.GameSessionFactory;
import Util.Resources;

public class Archer extends Player
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6896859090080642632L;
	
	public static final int DEFAULT_DAMAGE = 15;
	public static final int DEFAULT_HITPOINTS = 70;
	public static final int DEFAULT_DEFENCE = 10;
	public static final int DEFAULT_SPEED = 5;
	public static final int DEFAULT_XCOORD = 10000;
	public static final int DEFAULT_YCOORD = 10000;
	public static final int DEFAULT_WIDTH = 32;
	public static final int DEFAULT_HEIGHT = 64;
	
	private Archer(int damage, int hitpoints, int defence, int speed, int xCoord, int yCoord, EntityType entityType, int width, int height) {
		super(damage, hitpoints, defence, speed, xCoord, yCoord, entityType, width, height, PlayerType.ARCHER);
	}
	
	public Archer()
	{
		super(DEFAULT_DAMAGE, DEFAULT_HITPOINTS, DEFAULT_DEFENCE, DEFAULT_SPEED, DEFAULT_XCOORD, DEFAULT_YCOORD, EntityType.PLAYER, DEFAULT_WIDTH, DEFAULT_HEIGHT, PlayerType.ARCHER);
	}
	

	
	/**
	 * 
	 * @param damage
	 * @param hitpoints
	 * @param defense
	 * @param speed
	 * @param xCoord
	 * @param yCoord
	 * @param entityType
	 * @param width
	 * @param height
	 */
	@Override
	public void attack(float x, float y) {
		Player player = GameSessionFactory.getGameSession().getPlayer();
		Projectile arrow = null;
		if(player.getDirectionFacing() == LEFT)
		{
			arrow = new Arrow(getDamage(),0,0,12,player.getCenterX(),player.getCenterY() - 14, EntityType.PROJECTILE, 0, 0, getEntityId(), 3);
			arrow.setXVel(-arrow.getSpeed());
		}
		else if(player.getDirectionFacing() == UP)
		{
			arrow = new Arrow(getDamage(),0,0,12,player.getCenterX() - 14, player.getCenterY() - 14, EntityType.PROJECTILE, 0, 0, getEntityId(), 1);
			arrow.setYVel(-arrow.getSpeed());
		}
		else if(player.getDirectionFacing() == RIGHT)
		{
			arrow = new Arrow(getDamage(),0,0,12,player.getCenterX(),player.getCenterY() - 14, EntityType.PROJECTILE, 0, 0, getEntityId(), 4);
			arrow.setXVel(arrow.getSpeed());
		}
		else if(player.getDirectionFacing() == DOWN)
		{
			arrow = new Arrow(getDamage(),0,0,12,player.getCenterX() - 14, player.getCenterY() - 14, EntityType.PROJECTILE, 0, 0, getEntityId(), 2);
			arrow.setYVel(arrow.getSpeed());
		}
		
		GameSessionFactory.getGameSession().addEntity(arrow);
	}
	
	public Image getImage()
	{
		return Resources.getImage("archer");
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
		attacking[UP]    = new Animation(ss, 0, 16, 10, 16,  true, ANIMSPEED, false);
		attacking[LEFT]  = new Animation(ss, 0, 17, 10, 17,  true, ANIMSPEED, false);
		attacking[DOWN]  = new Animation(ss, 0, 18, 10, 18,  true, ANIMSPEED, false);
		attacking[RIGHT] = new Animation(ss, 0, 19, 10, 19,  true, ANIMSPEED, false);
		
		lastAttackingFrame = 9;
		
		setCurrentAnimation(walking[DOWN]);
	}
	
}
