package Entities;

import java.awt.Color;
import java.awt.Graphics;

import Items.Inventory;

public abstract class Player extends Entity{

	public enum PlayerType { BARBARIAN, ARCHER, WIZARD };
	protected PlayerType playerType;
	private Inventory inventory;
	private int currency;
	
	public Player(int damage, int hitpoints, int defence, int speed, int xCoord, int yCoord, EntityType entityType, int width, int height, PlayerType playerType) {
		super(damage, hitpoints, defence, speed, xCoord, yCoord, EntityType.PLAYER, width, height);
		this.playerType = playerType;
		inventory = new Inventory();
	}
	
	@Override
	public void move() 
	{
		xCoord += xVel;
		yCoord += yVel;
	}
	
	public abstract void paint(Graphics g);
	
	public abstract void attack(int x, int y);
	
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
		 
	}

}
