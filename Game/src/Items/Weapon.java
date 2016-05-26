package Items;

import java.util.Random;

public abstract class Weapon extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7386540504122739565L;

	protected Random random;
	
	public int minDamage;
	public int maxDamage;
	
	//Used for generating a min damage and max damage for a new weapon upon generation.
	protected int minDamageMin;
	protected int minDamageMax;
	protected int maxDamageMin;
	protected int maxDamageMax;
	
	//IF we want to include chances for crit's
	public double critPercent;
	public double critDamageMultiplier;
	
	public Weapon()
	{
		super();
		generateMinAndMax();
	}
	
	public Weapon(int minDamage, int maxDamage) {
		super();
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
	}
	
	@Override
	public ItemType getItemType()
	{
		return ItemType.WEAPON;
	}
	
	public void generateMinAndMax() {
		this.minDamage = minDamageMin + random.nextInt( ( minDamageMax - minDamageMin) + 1 );
		this.maxDamage = maxDamageMin + random.nextInt( ( maxDamageMax - maxDamageMin) + 1 );
	}
	
	public abstract void attack();
	
}
