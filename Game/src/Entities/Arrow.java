package Entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import Util.GameSessionFactory;
import Util.Resources;
import Util.Window;

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
			int width, int height, int ownerID, int direction) {
		super(damage, hitpoints, defense, speed, xCoord, yCoord, entityType, width, height, ownerID);
		// TODO Auto-generated constructor stub
		if(direction == 1)
			this.setCurrentAnimation(walking[UP]);
		else if(direction == 2)
			this.setCurrentAnimation(walking[DOWN]);
		else if(direction == 3)
			this.setCurrentAnimation(walking[LEFT]);
		else if(direction == 4)
			this.setCurrentAnimation(walking[RIGHT]);
					
	}

	@Override
	public void move() {
		xCoord += xVel;
		yCoord += yVel;
		
		//If this is off the screen according to the player, add to the remove list to remove on next update.
		if(Math.abs(xCoord - GameSessionFactory.getGameSession().getPlayer().getxCoord()) > Window.WIDTH
				|| Math.abs(yCoord - GameSessionFactory.getGameSession().getPlayer().getyCoord()) > Window.HEIGHT)
		{
			GameSessionFactory.getGameSession().getRemoveListEntities().add(this);
		}
	}

	@Override
	public Image getImage() {
		return Resources.getImage("arrow");
	}

	@Override
	public void initAnimations() {
		
		ss = new SpriteSheet(getImage(), 32,32);
		
		
		
		walking = new Animation[4];

		walking[LEFT]  = new Animation(ss, 0, 0,  0, 0,  true, ANIMSPEED, false);

		walking[UP]    = new Animation(ss, 1, 0,  1, 0,  true, ANIMSPEED, false);

		walking[RIGHT] = new Animation(ss, 2, 0, 2, 0, true, ANIMSPEED, false);

		walking[DOWN]  = new Animation(ss, 3, 0, 3, 0, true, ANIMSPEED, false);
		
		attacking = new Animation[4];
		attacking[UP]    = new Animation(ss, 0, 0, 0, 0,  true, ANIMSPEED*2, false);
		attacking[LEFT]  = new Animation(ss, 0, 0, 0, 0,  true, ANIMSPEED*2, false);
		attacking[DOWN]  = new Animation(ss, 0, 0, 0, 0,  true, ANIMSPEED*2, false);
		attacking[RIGHT] = new Animation(ss, 0, 0, 0, 0,  true, ANIMSPEED*2, false);
		
	}

}
