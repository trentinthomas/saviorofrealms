package Entities;

import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

import org.newdawn.slick.Image;

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
	
	
	protected int damage; //how much damage the entity does
	protected int hitpoints; //how many hitpoints the entity does
	protected int defense; //
	protected int speed;
	protected int xCoord;
	protected int yCoord;
	protected int centerOfEntity;
	protected int currentHitPoints;
	
	protected int xVel;
	protected int yVel;
	
	protected int width;
	protected int height;
	
	protected enum EntityType {PLAYER, NPC, ENEMY, ITEM};
	
	protected EntityType entityType;
	
	public Entity(int damage, int hitpoints, int defense, int speed, int xCoord, int yCoord, EntityType entityType, int width, int height)
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
	public int getxCoord() 
	{
		return xCoord;
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
	public int getyCoord() 
	{
		return yCoord;
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
	


	public void setXVel(int xVel) {
		this.xVel = xVel;
	}
	
	public int getXVel() {
		return this.xVel;
	}
	
	public void setYVel(int yVel)
	{
		this.yVel = yVel;
	}
	
	public int getYVel() {
		return this.yVel;
	}
	
	public void setWidth(int width)
	{
		this.width = width;
	}
	public int getWidth()
	{
		return width;
	}
	
	public void setHeight(int height)
	{
		this.height = height;
	}
	
	
	public int getHeight()
	{
		return height;
	}
	
	public int getEndX()
	{
		return xCoord + width;
	}
	
	public int getEndY()
	{
		return yCoord + height;
	}
	
	public int getHalfWidth() {
		return width/2;
	}
	
	public int getHalfHeight() {
		return height/2;
	}
	
	public int getCenterX()
	{
		return xCoord + getHalfWidth();
	}
	
	public int getCenterY()
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
	
	/**
	 * abstract move method to allow entities
	 * to move uniquely
	 */
	public abstract void move();
	public abstract Image getImage();
}
