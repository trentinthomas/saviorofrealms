package Game.TrentinAndErikGame.entities;

import Game.TrentinAndErikGame.entities.Entity.EntityType;

public class Wizard extends Player
{	
	
	private Wizard(int damage, int hitpoints, int defence, int speed, int xCoord, int yCoord, EntityType entityType, int width, int height) {
		super(damage, hitpoints, defence, speed, xCoord, yCoord, entityType, width, height);
	}
	
	public Wizard()
	{
		super(10, 100, 10, 10, 0, 0, EntityType.PLAYER, 40, 40);
	}
}
