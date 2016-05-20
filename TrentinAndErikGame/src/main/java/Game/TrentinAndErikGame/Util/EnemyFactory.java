package Game.TrentinAndErikGame.Util;

import Game.TrentinAndErikGame.entities.Enemy;
import Game.TrentinAndErikGame.entities.Enemy.EnemyType;
import Game.TrentinAndErikGame.entities.Goblin;

public class EnemyFactory 
{
	
	public static void spawnEnemy( EnemyType enemyType, int xCoord, int yCoord)
	{
		Enemy enemy;
		if(enemyType == EnemyType.GOBLIN)
		{
			enemy = new Goblin(xCoord, yCoord);
			GameSessionFactory.getGameSession().addEntity(enemy);
		}
	}
}
