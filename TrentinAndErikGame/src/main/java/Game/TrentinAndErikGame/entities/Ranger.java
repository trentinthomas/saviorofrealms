package Game.TrentinAndErikGame.entities;

import java.awt.Color;
import java.awt.Graphics;

public class Ranger extends Player
{
	
	private Ranger(int damage, int hitpoints, int defence, int speed, int xCoord, int yCoord, EntityType entityType, int width, int height) {
		super(damage, hitpoints, defence, speed, xCoord, yCoord, entityType, width, height, PlayerType.RANGER);
	}
	
	public Ranger()
	{
		super(10, 100, 10, 10, 0, 0, EntityType.PLAYER, 40, 40, PlayerType.RANGER);
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.GREEN);
		g.fillRect(xCoord, yCoord, width, height);
	}
}
