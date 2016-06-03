package Entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import Util.Resources;

public class Arrow extends Projectile {

	/**
	 * 
	 * @param damage
	 * @param hitpoints
	 * @param defense
	 * @param speed
	 * @param xCoord
	 * @param yCoord
	 * @param entityType
	 * @param width
	 * @param height
	 */
	public Arrow(int damage, int hitpoints, int defense, int speed, float xCoord, float yCoord, EntityType entityType,
			int width, int height, int ownerID) {
		super(damage, hitpoints, defense, speed, xCoord, yCoord, entityType, width, height, ownerID);
		// TODO Auto-generated constructor stub
		this.setCurrentAnimation(walking[DOWN]);
	}

	@Override
	public void move() {
		xCoord += xVel;
		yCoord += yVel;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return Resources.getImage("arrow");
	}

	@Override
	public void initAnimations() {
		// TODO Auto-generated method stub
		
		ss = new SpriteSheet(getImage(), 32,32);
		
		
		
		walking = new Animation[4];
		walking[LEFT]  = new Animation(ss, 0, 0,  0, 0,  true, ANIMSPEED, false);
		ss.rotate(90);
		walking[UP]    = new Animation(ss, 0, 0,  0, 0,  true, ANIMSPEED, false);
		ss.rotate(90);
		walking[RIGHT] = new Animation(ss, 0, 0, 0, 0, true, ANIMSPEED, false);
		//ss.rotate(90);
		walking[DOWN]  = new Animation(ss, 0, 0, 0, 0, true, ANIMSPEED, false);
		
		attacking = new Animation[4];
		attacking[UP]    = new Animation(ss, 0, 0, 0, 0,  true, ANIMSPEED*2, false);
		attacking[LEFT]  = new Animation(ss, 0, 0, 0, 0,  true, ANIMSPEED*2, false);
		attacking[DOWN]  = new Animation(ss, 0, 0, 0, 0,  true, ANIMSPEED*2, false);
		attacking[RIGHT] = new Animation(ss, 0, 0, 0, 0,  true, ANIMSPEED*2, false);
		
	}

}
