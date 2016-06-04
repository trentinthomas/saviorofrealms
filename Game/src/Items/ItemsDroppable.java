package Items;

public interface ItemsDroppable {

	DropTable dropTable = new DropTable();
	
	public void initializeDropTable();
	
	public Item getDropTableItem();
}
