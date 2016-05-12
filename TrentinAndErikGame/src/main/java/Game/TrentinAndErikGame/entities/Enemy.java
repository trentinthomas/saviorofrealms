package Game.TrentinAndErikGame.entities;

import java.awt.Graphics;

public class Enemy extends Entity
{
	

	public Enemy(int damage, int hitpoints, int defense, int speed, int xCoord, int yCoord, EntityType entityType, int width, int height) {
		super(damage, hitpoints, defense, speed, xCoord, yCoord, EntityType.ENEMY, width, height);
	}

	@Override
	public void move() 
	{
		xCoord += xVel;
		yCoord += yVel;
	}
	
	public void paint(Graphics g)
	{
		
	}

}
