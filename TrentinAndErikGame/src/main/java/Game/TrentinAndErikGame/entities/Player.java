package Game.TrentinAndErikGame.entities;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends Entity{

	
	public Player(int damage, int hitpoints, int defence, int speed, int xCoord, int yCoord, EntityType entityType, int width, int height, PlayerType playerType) {
		super(damage, hitpoints, defence, speed, xCoord, yCoord, EntityType.PLAYER, width, height);
		this.playerType = playerType;
	}
	
	public enum PlayerType { BARBARIAN, RANGER, WIZARD };
	protected PlayerType playerType;
	
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

}
