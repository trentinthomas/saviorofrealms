package Entities;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.Point;

import org.newdawn.slick.Image;

import Util.Resources;

public class Archer extends Player
{
	
	private Archer(int damage, int hitpoints, int defence, int speed, int xCoord, int yCoord, EntityType entityType, int width, int height) {
		super(damage, hitpoints, defence, speed, xCoord, yCoord, entityType, width, height, PlayerType.RANGER);
	}
	
	public Archer()
	{
		super(10, 100, 10, 10, 0, 0, EntityType.PLAYER, 40, 40, PlayerType.RANGER);
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.GREEN);
		g.fillRect(xCoord, yCoord, width, height);
	}

	@Override
	public void attack(int x, int y) {
		// TODO Auto-generated method stub
		fireArrow(getCenterOfEntity(), x, y);
	}
	
	private void fireArrow(Point center, int x, int y)
	{
		Point point = new Point(x, y);
		Arrow arrow = new Arrow(center);
		
		if(point.getX() < center.getY())
		{
			
		}
		if(point.getY() < center.getY())
		{
			
		}
		if(point.getX() > center.getX())
		{
			
		}
		if(point.getY() > center.getY())
		{
			
		}
		
	}
	
	private class Arrow
	{
		int x;
		int y;
		int velX;
		int velY;
		
		public Arrow(Point p)
		{
			this.x = (int) p.getX();
			this.y = (int) p.getY();
		}
		
		
	}
	
	public Image getImage()
	{
		return Resources.getSpriteImage("archer", 1, 1);
	}
	
}
