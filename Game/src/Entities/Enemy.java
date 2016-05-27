package Entities;

import java.awt.Graphics;

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
	

	public Enemy(int damage, int hitpoints, int defense, int speed, int xCoord, int yCoord, EntityType entityType, int width, int height, EnemyType enemyType) {
		super(damage, hitpoints, defense, speed, xCoord, yCoord, EntityType.ENEMY, width, height);
		this.enemyType = enemyType;
	}

	@Override
	public void move() 
	{
		if(GameSessionFactory.getGameSession().getPlayer().getxCoord() < this.xCoord)
		{
			this.setXVel(-this.speed);
		}
		if(GameSessionFactory.getGameSession().getPlayer().getxCoord() > this.xCoord)
		{
			this.setXVel(this.speed);
		}
		if(GameSessionFactory.getGameSession().getPlayer().getyCoord() < this.yCoord)
		{
			this.setYVel(-this.speed);
		}
		if(GameSessionFactory.getGameSession().getPlayer().getyCoord() > this.yCoord)
		{
			this.setYVel(this.speed);
		}
		xCoord += xVel;
		yCoord += yVel;
	}
	
	public abstract void paint(Graphics g);
	
	public EnemyType getEnemyType()
	{
		return enemyType;
	}

}
