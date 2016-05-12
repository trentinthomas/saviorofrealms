package Game.TrentinAndErikGame.entities;

public class Ranger extends Player
{
	
	private Ranger(int damage, int hitpoints, int defence, int speed, int xCoord, int yCoord, EntityType entityType, int width, int height) {
		super(damage, hitpoints, defence, speed, xCoord, yCoord, entityType, width, height);
	}
	
	public Ranger()
	{
		super(10, 100, 10, 10, 0, 0, EntityType.PLAYER, 40, 40);
	}
}
