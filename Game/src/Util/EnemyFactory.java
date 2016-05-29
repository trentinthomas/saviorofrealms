package Util;

import Entities.Enemy;
import Entities.Enemy.EnemyType;
import Entities.Goblin;

public class EnemyFactory 
{
	
	public static void spawnEnemy( EnemyType enemyType, float xCoord, float yCoord)
	{
		Enemy enemy;
		if(enemyType == EnemyType.GOBLIN)
		{
			enemy = new Goblin(xCoord, yCoord);
			GameSessionFactory.getGameSession().addEntity(enemy);
		}
	}
}
