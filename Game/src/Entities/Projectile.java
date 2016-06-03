package Entities;

//Should this be an Entity? TODO review with Erik
public abstract class Projectile extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 193493686396900198L;

	public Projectile(int damage, int hitpoints, int defense, int speed, float xCoord, float yCoord, EntityType entityType,
			int width, int height, int ownerID) {
		super(damage, hitpoints, defense, speed, xCoord, yCoord, entityType, width, height);
		setOwnerID(ownerID);
		// TODO Auto-generated constructor stub
	}

}
