package Entities;

import java.awt.Color;
import java.awt.Graphics;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Goblin extends Enemy
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8094431321460940037L;

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
	
	public Image getImage()
	{
		//TODO
		return null;
	}

	@Override
	public void initAnimations() {
		// TODO Auto-generated method stub
		
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
}
