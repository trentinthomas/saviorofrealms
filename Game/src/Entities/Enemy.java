package Entities;

import java.awt.Graphics;
import java.util.Vector;

import org.newdawn.slick.geom.Vector2f;

import Items.Item;
import Items.ItemsDroppable;
import Util.GameSessionFactory;
import Util.ItemFactory;
import saviorOfRealms.errorHandling.ItemNotFoundException;;

/**
 * 
 * @author Trentin
 *
 */
public abstract class Enemy extends Entity implements ItemsDroppable
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
		
		//I found that vectors are a lot easier to coordinate with movement for enemies. It looks cleaner too.
		Vector2f vector = new Vector2f();
		vector.x = player.getxCoord() - getxCoord();
		vector.y = player.getyCoord() - getyCoord();
		double hyp = Math.sqrt(vector.x*vector.x + vector.y*vector.y);
		vector.x /= hyp;
		vector.y /= hyp;
		
		if(Math.abs(vector.x) > Math.abs(vector.y)) 
		{
			setCurrentAnimation(vector.x > 0 ? walking[RIGHT] : walking[LEFT]);
		}
		else
		{
			setCurrentAnimation(vector.y > 0 ? walking[DOWN] : walking[UP]);
		}
		
		xCoord += vector.x*speed;
		yCoord += vector.y*speed;
	}
	
	public EnemyType getEnemyType()
	{
		return enemyType;
	}
	
	@Override
	public int getOwnerID() {
		return getEntityId();
	}

	public void calculateDropItem() {
		Item drop = null;
		try {
			drop = ItemFactory.generateItem(Item.BRONZE_SWORD_ID);
		} catch (ItemNotFoundException e) {
			e.printStackTrace();
		}
		
		GameSessionFactory.getGameSession().addItemToGround(drop, this.xCoord, this.yCoord);
		
	}

}
