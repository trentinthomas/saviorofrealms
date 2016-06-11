package Entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Circle;

import Items.Inventory;
import Items.Item;
import saviorOfRealms.errorHandling.InsufficientFundsException;

public abstract class Player extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2387359505606625000L;
	
	private static final float PLAYER_PICKUP_RADIUS = 5;

	public enum PlayerType { BARBARIAN, ARCHER, WIZARD };
	protected PlayerType playerType;
	private Inventory inventory;
	private int currency;
	
	private Circle pickupRadius;

	
	public Player(int damage, int hitpoints, int defence, int speed, int xCoord, int yCoord, EntityType entityType, int width, int height, PlayerType playerType) {
		super(damage, hitpoints, defence, speed, xCoord, yCoord, EntityType.PLAYER, width, height);
		this.playerType = playerType;
		inventory = new Inventory();
		
		pickupRadius = new Circle(xCoord, yCoord, PLAYER_PICKUP_RADIUS);
	}
	
	@Override
	public void move() 
	{
		xCoord += xVel;
		yCoord += yVel;
	}
	
	public abstract void attack(float mouseX, float mouseY);
	
	public PlayerType getPlayerType()
	{
		return playerType;
	}
	
	public Inventory getInventory()
	{
		return inventory;
	}
	
	public void addCurrency(int amountToAdd)
	{
		currency += amountToAdd;
	}
	
	public void subtractCurrency(int amountToSubtract) throws InsufficientFundsException
	{
		if(currency - amountToSubtract < 0) {
			throw new InsufficientFundsException();
		}
		currency -= amountToSubtract;
	}
	
	protected void levelUp() {
		//TODO implement a way to levelup, increasing stats and adding talent points etc.
	}
	
	@Override
	public int getOwnerID() {
		return getEntityId();
	}

	public boolean canPickUp(Item item) {
		return pickupRadius.contains(item.getX(), item.getY());
	}

}
