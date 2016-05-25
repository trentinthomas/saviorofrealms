package Entities;

import java.awt.Color;
import java.awt.Graphics;

import org.newdawn.slick.Image;

import Entities.Entity.EntityType;
import Util.Resources;

public class Wizard extends Player
{	
	
	private Wizard(int damage, int hitpoints, int defence, int speed, int xCoord, int yCoord, EntityType entityType, int width, int height) {
		super(damage, hitpoints, defence, speed, xCoord, yCoord, entityType, width, height, PlayerType.WIZARD);
	}
	
	public Wizard()
	{
		super(10, 100, 10, 2, 0, 0, EntityType.PLAYER, 40, 40, PlayerType.WIZARD);
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.fillRect(xCoord, yCoord, width, height);
	}

	@Override
	public void attack(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	public Image getImage()
	{
		return Resources.getSpriteImage("wizard", 1, 1);
	}
}
