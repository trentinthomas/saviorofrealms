package Entities;

import java.io.Serializable;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import UI.Play;
import Util.Camera;
import Util.GameSession;
import Util.GameSessionFactory;
import Util.Window;
import saviorOfRealms.errorHandling.EntityDeadException;

/**
 * Entity is a superclass that all Entities will extend to keep track of all entities on the screen
 * @author Trentin
 *
 */
public abstract class Entity implements Serializable
{
	
	private static final long serialVersionUID = -1448174602088544515L;
	//TODO implement a way to serialize all the entities into a file and read them back into the game.
	
	
	public static final int UP = 0;
	public static final int LEFT = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;
	protected static final int ANIMSPEED = 55; //speed of the Animation
	
	
	protected int level;
	protected int experience;
	
	protected int damage; //how much damage the entity does
	protected int maxHitpoints; //how many hitpoints the entity has maximum
	protected int defense; //how much defence the entity has
	protected double speed; //how fast the entity is
	protected float xCoord; //where on the map the entity is.
	protected float yCoord; //where on the map the entity is.
	protected int currentHitPoints; //what the current hitponits of the entity is
	
	protected double xVel;
	protected double yVel;
	
	protected float width;
	protected float height;
	
	protected SpriteSheet ss;
	protected Animation[] walking;
	protected Animation[] attacking;
	protected int lastAttackingFrame;
	private Animation currentAnimation;
	
	private int entityID;
	private int ownerID;
	
	private int directionFacing;
	
	public enum EntityType {PLAYER, NPC, ENEMY, PROJECTILE};
	public enum AnimationType {MOVEMENT, ATTACKING};
	
	protected EntityType entityType;
	
	public Entity(int damage, int hitpoints, int defense, int speed, float xCoord, float yCoord, EntityType entityType, int width, int height)
	{
		this.setDamage(damage);
		this.maxHitpoints = hitpoints;
		this.defense = defense;
		this.speed = speed;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.entityType = entityType;
		this.width = width;
		this.height = height;
		initAnimations();
		level = 1;
		experience = 0;
		entityID = GameSession.entityId++;
		currentHitPoints = maxHitpoints;
	}
	
	/**
	 * Gets the number of hitpoints the entity has
	 * @return hitpoints
	 */
	public int getHitpoints() 
	{
		return maxHitpoints;
	}

	/**
	 * Sets the number of hitpoints the entity has
	 * @param hitpoints
	 */
	public void setHitpoints(int hitpoints) 
	{
		this.maxHitpoints = hitpoints;
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
	public double getSpeed() 
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
	


	public void setXVel(double d) {
		this.xVel = d;
	}
	
	public double getXVel() {
		return this.xVel;
	}
	
	public void setYVel(double yVel)
	{
		this.yVel = yVel;
	}
	
	public double getYVel() {
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
		return (e.getEndX() >= xCoord 
				&& e.getEndY() >= yCoord 
				&& getEndX() >= e.getxCoord() 
				&& getEndY() >= e.getyCoord() 
				&& e.getEntityId() != getEntityId() 
				&& e.getOwnerID() != getOwnerID());
	}
	
	public void subtractHitpoints(int hitpoints) throws EntityDeadException
	{
		currentHitPoints -= hitpoints;
		if(currentHitPoints <= 0)
			throw new EntityDeadException();
	}
	
	public boolean isMoving()
	{
		return (xVel > 0 || yVel > 0);
	}
	
	public void addHitpoints(int hitpoints)
	{
		if(currentHitPoints + hitpoints > this.maxHitpoints)
		{
			currentHitPoints = this.maxHitpoints;
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
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	@Override
	public String toString() {
		return "EntityType: " + entityType + "\n"
				+ "maxhitpoints: " + maxHitpoints + "\n"
				+ "xCoord: " + xCoord + "\n"
				+ "yCoord: " + yCoord + "\n"
				+ "speed: " + speed + "\n"
				+ "xVel: " + xVel + "\n"
				+ "width: " + width + "\n"
				+ "level: " + level + "\n"
				+ "damage: " + damage + "\n"
				+ "currentAnimation: " + currentAnimation + "\n";
	} 
	
	public void setCurrentAnimation(Animation animation)
	{
		this.currentAnimation = animation;
	}
	
	public void setCurrentAnimation(Animation animation, int frame)
	{
		this.currentAnimation = animation;
		currentAnimation.setCurrentFrame(frame);
	}
	
	public Animation getCurrentAnimation() {
		return currentAnimation;
	}
	
	public int getLastAttackingFrame() {
		return lastAttackingFrame;
	}
	
	public int getDirectionFacing() {
		return directionFacing;
	}
	
	public void setDirectionFacing(int directionFacing) {
		this.directionFacing = directionFacing;
	}
	
	public EntityType getEntityType() {
		return entityType;
	}
	
	public int getEntityId() {
		return entityID;
	}
	
	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}
	
	public int getOwnerID() {
		return ownerID;
	}
	
	public float[] getHitBox(Camera cam) {
		return new float[]{
				cam.getX() + getHalfWidth() + Window.WIDTH/2,
				cam.getY() - getHalfHeight() + Window.HEIGHT/2 + Play.PIXEL_OFFSET, 
				cam.getX() + getHalfWidth() + Window.WIDTH/2,
				cam.getY() + getHalfHeight() + Window.HEIGHT/2,
				cam.getX() - getHalfWidth() + Window.WIDTH/2,
				cam.getY() + getHalfHeight() + Window.HEIGHT/2,
				cam.getX() - getHalfWidth() + Window.WIDTH/2,
				cam.getY() - getHalfHeight() + Window.HEIGHT/2 + Play.PIXEL_OFFSET};
	}
	
	/**
	 * abstract move method to allow entities
	 * to move uniquely
	 */
	public abstract void move();
	public abstract Image getImage();
	public abstract void initAnimations();


	
	
}
