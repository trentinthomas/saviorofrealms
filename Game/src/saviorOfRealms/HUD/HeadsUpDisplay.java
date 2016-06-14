package saviorOfRealms.HUD;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.GUIContext;

import Util.Resources;

public class HeadsUpDisplay {
	
	private boolean visible;
	private List<HUDComponent> components;
	private GUIContext gc;
	
	public HeadsUpDisplay(GUIContext gc) {
		components = new ArrayList<HUDComponent>();
		this.gc = gc;
		initComponents();
	}
	
	public void initComponents() {
		HUDInventoryDisplay inventoryDisplay = new HUDInventoryDisplay(gc, 100, 100, Resources.getImage("inventory_20")); //TODO change to coordinates of the inventory.
		add(inventoryDisplay);
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public void draw(Graphics g) {
		if(visible) {
			for(HUDComponent component : components) {
				component.draw(g);
			}
		}
	}
	
	public void add(HUDComponent hudComponent) {
		components.add(hudComponent);
	}

}
