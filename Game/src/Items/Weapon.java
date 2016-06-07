package Items;

import java.util.List;
import java.util.Random;

public abstract class Weapon extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7386540504122739565L;

	protected Random random;
	
	private int minDamage;
	private int maxDamage;
	
	//Used for generating a min damage and max damage for a new weapon upon generation.
	protected int minDamageMin;
	protected int minDamageMax;
	protected int maxDamageMin;
	protected int maxDamageMax;
	
	//IF we want to include chances for crit's
	public double critPercent;
	public double critDamageMultiplier;
	
	//if we want to get modifiers for enchanting/rolling onto weapons. Probably want to create Modifier class.
	public List<Integer> modifiers;
	
	public Weapon()
	{
		super();
		random = new Random();
		generateMinAndMaxDamage();
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
	
	public void generateMinAndMaxDamage() {
		this.minDamage = minDamageMin + random.nextInt( ( minDamageMax - minDamageMin) + 1 );
		this.maxDamage = maxDamageMin + random.nextInt( ( maxDamageMax - maxDamageMin) + 1 );
	}
	
	public int getMinDamage()
	{
		return minDamage;
	}
	
	public int getMaxDamage()
	{
		return maxDamage;
	}
	
	public abstract void attack();
	
}
