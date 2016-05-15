package Game.TrentinAndErikGame.entities;

import java.awt.Color;
import java.awt.Graphics;

public class Barbarian extends Player
{
	
	private Barbarian(int damage, int hitpoints, int defence, int speed, int xCoord, int yCoord, EntityType entityType, int width, int height) {
		super(damage, hitpoints, defence, speed, xCoord, yCoord, entityType, width, height);
	}
	
	public Barbarian()
	{
		super(10, 100, 20, 10, 200, 200, EntityType.PLAYER, 40, 40);
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect(xCoord, yCoord, width, height);
	}
	

}
