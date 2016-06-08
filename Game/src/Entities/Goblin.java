package Entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import Items.Item;
import Util.Resources;
import saviorOfRealms.errorHandling.ItemNotFoundException;

public class Goblin extends Enemy
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8094431321460940037L;

	public Goblin(int damage, int hitpoints, int defense, int speed, float xCoord, float yCoord, EntityType entityType, int width, int height) {
		super(damage, hitpoints, defense, speed, xCoord, yCoord, EntityType.ENEMY, width, height, EnemyType.GOBLIN);
		this.setCurrentAnimation(walking[DOWN]);
	}
	
	public Goblin()
	{
		super(10, 100, 10, 1, 0, 0, EntityType.ENEMY, 40, 40, EnemyType.GOBLIN);
		this.setCurrentAnimation(walking[DOWN]);
	}
	
	public Goblin(float xCoord, float yCoord)
	{
		super(10, 100, 10, 1, xCoord, yCoord, EntityType.ENEMY, 40, 40, EnemyType.GOBLIN);
		this.setCurrentAnimation(walking[DOWN]);
	}
	
	public Image getImage()
	{
		return Resources.getImage("goblin");
	}

	@Override
	public void initAnimations() {
		
		ss = new SpriteSheet(getImage(), 64, 64);
		
		walking = new Animation[4];
		walking[UP]    = new Animation(ss, 1, 8,  8, 8,  true, ANIMSPEED, false);
		walking[LEFT]  = new Animation(ss, 0, 9,  8, 9,  true, ANIMSPEED, false);
		walking[DOWN]  = new Animation(ss, 1, 10, 8, 10, true, ANIMSPEED, false);
		walking[RIGHT] = new Animation(ss, 0, 11, 8, 11, true, ANIMSPEED, false);
		
		attacking = new Animation[4];
		attacking[UP]    = new Animation(ss, 1, 12, 5, 12,  true, ANIMSPEED, false);
		attacking[LEFT]  = new Animation(ss, 1, 13, 5, 13,  true, ANIMSPEED, false);
		attacking[DOWN]  = new Animation(ss, 1, 14, 5, 14,  true, ANIMSPEED, false);
		attacking[RIGHT] = new Animation(ss, 1, 15, 5, 15,  true, ANIMSPEED, false);
		
	}
	
	@Override
	public String toString() {
		return super.toString() 
				+ "EnemyType: " + EnemyType.GOBLIN;
	}

	@Override
	public void initializeDropTable() {
		dropTable.addItem(Item.BRONZE_SWORD_ID, 1.00);
		
	}

	@Override
	public Item getDropTableItem() {
		try{
			return dropTable.getNextItem();
		} catch(ItemNotFoundException infe) {
			infe.printStackTrace();
		}
		return null;
	}
}
