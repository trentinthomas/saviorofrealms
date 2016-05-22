package Items;

public abstract class Weapon extends Item {

	public int minDamage;
	public int maxDamage;
	public double critPercent;
	
	public Weapon(int itemId, int minDamage, int maxDamage) {
		super( itemId );
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
	}
	
	@Override
	public ItemType getItemType()
	{
		return ItemType.WEAPON;
	}
	
	public abstract void attack();

}
