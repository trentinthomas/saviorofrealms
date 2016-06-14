package saviorOfRealms.HUD;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;

public class HUDInventoryDisplay extends HUDComponent{

	public HUDInventoryDisplay(GUIContext gc, int x, int y, Image image) {
		super(gc, x, y, image, false);
	}

	@Override
	public void doAction() {
		// TODO If they click in the inventory, they want to view an item so show its description.
	}

	@Override
	public void drawOtherThings(Graphics g) {
		// TODO draw the player inventory here, and if hovering an item show its description.
	}

}
