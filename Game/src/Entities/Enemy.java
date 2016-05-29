package Entities;

import java.awt.Graphics;
import java.util.Vector;

import org.newdawn.slick.geom.Vector2f;

import Util.GameSessionFactory;;

/**
 * 
 * @author Trentin
 *
 */
public abstract class Enemy extends Entity
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -276268313229956899L;

	public enum EnemyType { GOBLIN, IMP};
	
	private EnemyType enemyType;
	

	public Enemy(int damage, int hitpoints, int defense, int speed, float xCoord, float yCoord, EntityType entityType, int width, int height, EnemyType enemyType) {
		super(damage, hitpoints, defense, speed, xCoord, yCoord, EntityType.ENEMY, width, height);
		this.enemyType = enemyType;
	}

	@Override
	public void move() 
	{
		Player player = GameSessionFactory.getGameSession().getPlayer();
//		if(GameSessionFactory.getGameSession().getPlayer().getxCoord() < this.xCoord + player.getWidth() )
//		{
//			this.setXVel(-this.speed);
//		}
//		if(GameSessionFactory.getGameSession().getPlayer().getxCoord() > this.xCoord)
//		{
//			this.setXVel(this.speed);
//		}
//		if(GameSessionFactory.getGameSession().getPlayer().getyCoord() < this.yCoord)
//		{
//			this.setYVel(-this.speed);
//		}
//		if(GameSessionFactory.getGameSession().getPlayer().getyCoord() > this.yCoord)
//		{
//			this.setYVel(this.speed);
//		}
		
		//I found that vectors are a lot easier to coordinate with movement for enemies. It looks cleaner too.
		Vector2f vector = new Vector2f();
		vector.x = player.getxCoord() - getxCoord();
		vector.y = player.getyCoord() - getyCoord();
		double hyp = Math.sqrt(vector.x*vector.x + vector.y*vector.y);
		vector.x /= hyp;
		vector.y /= hyp;
		
		xCoord += vector.x*speed;
		yCoord += vector.y*speed;
	}
	
	public EnemyType getEnemyType()
	{
		return enemyType;
	}

}
