package Entities;

import java.awt.Color;
import java.awt.Graphics;

import org.newdawn.slick.Image;

import Util.Resources;

/**
 * Barbarian is a class the Player can control. Barbarians have more hitpoints and defence than normal, and
 * will be melee based.
 * @author Trentin
 *
 */
public class Barbarian extends Player
{
	
	/**
	 * If the player already has a game and loads one, it calls this.
	 * @param damage
	 * @param hitpoints
	 * @param defence
	 * @param speed
	 * @param xCoord
	 * @param yCoord
	 * @param entityType
	 * @param width
	 * @param height
	 */
	private Barbarian(int damage, int hitpoints, int defence, int speed, int xCoord, int yCoord, EntityType entityType, int width, int height) {
		super(damage, hitpoints, defence, speed, xCoord, yCoord, entityType, width, height, PlayerType.BARBARIAN);
	}
	
	/**
	 * Creates a new Barbarian with default stats.
	 */
	public Barbarian()
	{
		super(10, 100, 20, 10, 200, 200, EntityType.PLAYER, 40, 40, PlayerType.BARBARIAN);
	}
	
	/**
	 * Draws the barbarian on the screen. Will eventually be an Image. Change image and animation based on 
	 * direction pushed.
	 */
	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect(xCoord, yCoord, width, height);
	}

	@Override
	public void attack(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	public Image getImage()
	{
		return Resources.getSpriteImage("barbarian", 1, 1);
	}
	

}
