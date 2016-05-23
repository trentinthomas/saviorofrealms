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
			sprites.put("wizard", loadSprite("res/mage.png", 64, 64));
		} catch(SlickException e) {
			e.printStackTrace();
		}
	}

	private SpriteSheet loadSprite(String path, int i, int j) throws SlickException {
		return new SpriteSheet(loadImage(path), i, j);
	}
	
	private Image loadImage(String path) throws SlickException {
		return new Image(path, false, Image.FILTER_NEAREST);
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
