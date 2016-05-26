package Items;


public abstract class Consumable extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5603686597998427386L;

	public Consumable() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ItemType getItemType()
	{
		return Item.ItemType.CONSUMABLE;
	}

}
