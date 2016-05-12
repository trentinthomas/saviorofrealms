package Game.TrentinAndErikGame.entities;

public class Goblin extends Enemy
{
	
	public Goblin(int damage, int hitpoints, int defense, int speed, int xCoord, int yCoord, EntityType entityType, int width, int height) {
		super(damage, hitpoints, defense, speed, xCoord, yCoord, EntityType.ENEMY, width, height);
	}
	
	public Goblin()
	{
		super(10, 100, 10, 10, 0, 0, EntityType.ENEMY, 40, 40);
	}
}
