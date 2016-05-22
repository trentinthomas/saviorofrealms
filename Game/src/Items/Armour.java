package Items;

public abstract class Armour extends Item {

	public int defence;
	
	public Armour( int itemId, int defence ) {
		super( itemId );
		this.defence = defence;
	}

	
}
