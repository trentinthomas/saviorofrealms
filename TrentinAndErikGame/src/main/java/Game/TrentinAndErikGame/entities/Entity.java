package Game.TrentinAndErikGame.entities;

import java.awt.Graphics;

public abstract class Entity 
{
	protected int damage;
	protected int hitpoints;
	protected int defense;
	protected int speed;
	protected int xCoord;
	protected int yCoord;
	
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
	
	public void setYVel(int yVel)
	{
		this.yVel = yVel;
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

	/**
	 * abstract move method to allow entities
	 * to move uniquely
	 */
	public abstract void move();
	public abstract void paint(Graphics g);
	
}
