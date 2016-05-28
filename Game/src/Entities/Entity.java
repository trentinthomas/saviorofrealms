package Entities;

import java.io.Serializable;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import saviorOfRealms.errorHandling.EntityDeadException;

/**
 * Entity is a superclass that all Entities will extend to keep track of all entities on the screen
 * @author Trentin
 *
 */
public abstract class Entity implements Serializable
{
	
	//TODO implement a way to serialize all the entities into a file and read them back into the game.
	private static final long serialVersionUID = 1L;
	
	
	public static final int UP = 0;
	public static final int LEFT = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;
	protected static final int ANIMSPEED = 55; //walking speed
	
	
	protected int damage; //how much damage the entity does
	protected int hitpoints; //how many hitpoints the entity does
	protected int defense; //
	protected int speed;
	protected float xCoord; //TODO FLOAT
	protected float yCoord; //TODO FLOAT
	protected int centerOfEntity;
	protected int currentHitPoints;
	
	protected float xVel;
	protected float yVel;
	
	protected float width;
	protected float height;
	
	protected SpriteSheet ss;
	protected Animation[] walking;
	protected Animation[] attacking;
	
//	ss = new SpriteSheet(player.getImage(), 64, 64);
//	
//	walking = new Animation[4];
//	walking[up]    = new Animation(ss, 1, 8,  8, 8,  true, animSpeed, false);
//	walking[left]  = new Animation(ss, 0, 9,  8, 9,  true, animSpeed, false);
//	walking[down]  = new Animation(ss, 1, 10, 8, 10, true, animSpeed, false);
//	walking[right] = new Animation(ss, 0, 11, 8, 11, true, animSpeed, false);
//	
//	attacking = new Animation[4];
//	attacking[up]    = new Animation(ss, 1, 12, 5, 12,  true, animSpeed, false);
//	attacking[left]  = new Animation(ss, 1, 13, 5, 13,  true, animSpeed, false);
//	attacking[down]  = new Animation(ss, 1, 14, 5, 14,  true, animSpeed, false);
//	attacking[right] = new Animation(ss, 1, 15, 5, 15,  true, animSpeed, false);
	
	protected enum EntityType {PLAYER, NPC, ENEMY, ITEM};
	public enum AnimationType {MOVEMENT, ATTACKING};
	
	protected EntityType entityType;
	
	public Entity(int damage, int hitpoints, int defense, int speed, float xCoord, float yCoord, EntityType entityType, int width, int height)
	{
		this.setDamage(damage);
		this.hitpoints = hitpoints;
		this.defense = defense;
		this.speed = speed;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.entityType = entityType;
		this.width = width;
		this.height = height;
		initAnimations();
	}
	
	/**
	 * Gets the number of hitpoints the entity has
	 * @return hitpoints
	 */
	public int getHitpoints() 
	{
		return hitpoints;
	}

	/**
	 * Sets the number of hitpoints the entity has
	 * @param hitpoints
	 */
	public void setHitpoints(int hitpoints) 
	{
		this.hitpoints = hitpoints;
	}

	/**
	 * Gets the number of defense the entity has
	 * @return defense
	 */
	public int getDefense() 
	{
		return defense;
	}

	/**
	 * Sets the number of defense the entity has
	 * @param defense
	 */
	public void setDefense(int defense) 
	{
		this.defense = defense;
	}

	/**
	 * Gets the speed of the entity
	 * @return
	 */
	public int getSpeed() 
	{
		return speed;
	}

	/**
	 * Sets the speed of the entity
	 * @param speed
	 */
	public void setSpeed(int speed) 
	{
		this.speed = speed;
	}

	/**
	 * Gets the x coordinate of the entity
	 * @return xCoord
	 */
	public float getxCoord() 
	{ 
		return xCoord - this.getHalfWidth();
	}

	/**
	 * Sets the x coordinate of the entity
	 * @param xCoord
	 */
	public void setxCoord(int xCoord) 
	{
		this.xCoord = xCoord;
	}

	/**
	 * Gets the y coordinate of the entity
	 * @return yCoord
	 */
	public float getyCoord() 
	{
		return yCoord - this.getHalfHeight();
	}

	/**
	 * Sets the y coordinate of the entity
	 * @param yCoord
	 */
	public void setyCoord(int yCoord) 
	{
		this.yCoord = yCoord;
	}

	/**
	 * Gets the damage of the entity
	 * @return damage
	 */
	public int getDamage() 
	{
		return damage;
	}

	/**
	 * Sets the damage of the entity
	 * @param damage
	 */
	public void setDamage(int damage) 
	{
		this.damage = damage;
	}
	


	public void setXVel(float xVel) {
		this.xVel = xVel;
	}
	
	public float getXVel() {
		return this.xVel;
	}
	
	public void setYVel(float yVel)
	{
		this.yVel = yVel;
	}
	
	public float getYVel() {
		return this.yVel;
	}
	
	public void setWidth(float width)
	{
		this.width = width;
	}
	public float getWidth()
	{
		return width;
	}
	
	public void setHeight(float height)
	{
		this.height = height;
	}
	
	
	public float getHeight()
	{
		return height;
	}
	
	public float getEndX()
	{
		return xCoord + width;
	}
	
	public float getEndY()
	{
		return yCoord + height;
	}
	
	public float getHalfWidth() {
		return width/2;
	}
	
	public float getHalfHeight() {
		return height/2;
	}
	
	public float getCenterX()
	{
		return xCoord + getHalfWidth();
	}
	
	public float getCenterY()
	{
		return yCoord + getHalfHeight();
	}
	
	public boolean testCollision(Entity e)
	{
		return (e.getEndX() >= xCoord && e.getEndY() >= yCoord && getEndX() >= e.getxCoord() && getEndY() >= e.getyCoord());
	}
	
	public void subtractHitpoints(int hitpoints) throws EntityDeadException
	{
		currentHitPoints -= hitpoints;
		if(currentHitPoints <= 0)
			throw new EntityDeadException();
	}
	
	public boolean isMoving()
	{
		return (xVel > 0 && yVel > 0);
	}
	
	public void addHitpoints(int hitpoints)
	{
		if(currentHitPoints + hitpoints > this.hitpoints)
		{
			currentHitPoints = this.hitpoints;
		}
		else
		{
			currentHitPoints += hitpoints;
		}
	}
	
	public Animation getAnimation(AnimationType at, int direction)
	{
		if(at == AnimationType.MOVEMENT)
		{
			return walking[direction];
		}
		else if(at == AnimationType.ATTACKING)
			return attacking[direction];
		return null;
	}
	
	/**
	 * abstract move method to allow entities
	 * to move uniquely
	 */
	public abstract void move();
	public abstract Image getImage();
	public abstract void initAnimations();
}
