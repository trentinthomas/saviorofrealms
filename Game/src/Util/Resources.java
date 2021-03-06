package Util;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

public class Resources {
	
	private static Map<String, Image> images;
	private static Map<String, SpriteSheet> sprites;
	private static Map<String, Sound> sounds;

	public Resources() {
		images = new HashMap<String, Image>();
		sprites = new HashMap<String, SpriteSheet>();
		sounds = new HashMap<String, Sound>();
		
		try {
			loadSpriteSheets();
			loadButtons();
			loadIcons();
			loadTiles();
			loadHUD();
			
		} catch(SlickException e) {
			e.printStackTrace();
		}
	}

	private void loadHUD() throws SlickException {
		images.put("inventory_20", loadImage("/res/hud/inventory_20.png"));
		images.put("quickSlots", loadImage("/res/hud/quickSlots.png"));
		images.put("healthBar", loadImage("/res/hud/healthBar.png"));
		images.put("energyBar", loadImage("/res/hud/energyBar.png"));
		/*images.put("experienceBar", loadImage("/res/hud/experienceBar.png"));*/
		images.put("currentLevel", loadImage("/res/hud/currentLevel.png"));
	}
	
	private void loadIcons() throws SlickException {
		images.put("arrow", loadImage("/res/items/arrow.png"));
		images.put("bronzeSword", loadImage("/res/items/bronzeSword.png"));
		images.put("ironSword", loadImage("/res/items/ironSword.png"));
		images.put("steelSword", loadImage("/res/items/steelSword.png"));
	}
	
	private void loadTiles() throws SlickException {
		images.put("tiles", loadImage("/res/tileset.png"));
		
		//We were loading subimages from tileset when rendering, im loading and putting in images.
		images.put("tiles_grass", loadSubImage("/res/tileset.png", 0, 0));
		images.put("tiles_deep_water", loadSubImage("/res/tileset.png", 32, 0));
		images.put("tiles_water", loadSubImage("/res/tileset.png", 64, 0));
		images.put("tiles_sand", loadSubImage("/res/tileset.png", 96, 0));
		images.put("tiles_stone", loadSubImage("/res/tileset.png", 0, 32));
		images.put("tiles_lava", loadSubImage("/res/tileset.png", 32, 32));
	}

	private void loadSpriteSheets() throws SlickException {
		images.put("barbarian", loadImage("/res/spritesheets/barbarian_dagger.png"));
		images.put("archer", loadImage("/res/spritesheets/archerv3.png"));
		images.put("wizard", loadImage("/res/spritesheets/wizard.png"));
		images.put("goblin", loadImage("/res/spritesheets/goblin.png"));
		
	}

	private void loadButtons() throws SlickException {
		images.put("up_barbarian", loadImage("/res/buttons/up_barbarian.png"));
		images.put("up_archer", loadImage("/res/buttons/up_archer.png"));
		images.put("up_wizard", loadImage("/res/buttons/up_wizard.png"));
		images.put("down_barbarian", loadImage("/res/buttons/down_barbarian.png"));
		images.put("down_archer", loadImage("/res/buttons/down_archer.png"));
		images.put("down_wizard", loadImage("/res/buttons/down_wizard.png"));
	}
	
	private SpriteSheet loadSprite(String path, int i, int j) throws SlickException {
		return new SpriteSheet(loadImage(path), i, j);
	}
	
	private Image loadImage(String path) throws SlickException {
		return new Image(path, false, Image.FILTER_NEAREST);
	}
	
	private Image loadSubImage(String pathToGet, int x, int y) throws SlickException {
		return loadImage(pathToGet).getSubImage(x, y, 32, 32);
	}
	
	private Sound loadSound(String path) throws SlickException {
		return new Sound(path);
	}
	
	public static Image getSpriteImage(String getter, int x, int y) {
		return sprites.get(getter).getSubImage(x, y);
	}
	
	public static Image getImage(String getter) {
		return images.get(getter);
	}
	
	public static Sound getSound(String getter) {
		return sounds.get(getter);
	}
	


}