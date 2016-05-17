package Game.TrentinAndErikGame.entities;

import java.awt.Color;
import java.awt.Graphics;

import Game.TrentinAndErikGame.items.Inventory;

public class Player extends Entity{

	public enum PlayerType { BARBARIAN, RANGER, WIZARD };
	protected PlayerType playerType;
	private Inventory inventory;
	
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
	
	public void paint(Graphics g)
	{
		g.setColor(Color.YELLOW);
		g.fillRect(xCoord, yCoord, width, height);
	}
	
	public PlayerType getPlayerType()
	{
		return playerType;
	}
	
	public Inventory getInventory()
	{
		return inventory;
	}

}
