package Items;

public interface ItemsDroppable {

	public final DropTable dropTable = new DropTable();
	
	public void initializeDropTable();
	
	public Item getDropTableItem();
}
