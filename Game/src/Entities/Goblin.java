package Entities;

import java.awt.Color;
import java.awt.Graphics;

public class Goblin extends Enemy
{
	
	public Goblin(int damage, int hitpoints, int defense, int speed, int xCoord, int yCoord, EntityType entityType, int width, int height) {
		super(damage, hitpoints, defense, speed, xCoord, yCoord, EntityType.ENEMY, width, height, EnemyType.GOBLIN);
	}
	
	public Goblin()
	{
		super(10, 100, 10, 1, 0, 0, EntityType.ENEMY, 40, 40, EnemyType.GOBLIN);
	}
	
	public Goblin(int xCoord, int yCoord)
	{
		super(10, 100, 10, 10, xCoord, yCoord, EntityType.ENEMY, 40, 40, EnemyType.GOBLIN);
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect(xCoord, yCoord, width, height);
	}
}
