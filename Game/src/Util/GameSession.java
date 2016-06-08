package Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.newdawn.slick.geom.Point;

import Entities.Entity;
import Entities.Player;
import Items.Item;

/**
 * GameSession will hold all variables where we need access to them globally, such as an array of all enemies on the map, or other important variables.
 * @author Trentin
 *
 */
public class GameSession 
{
	public static int entityId;
	
	private List<Entity> entities;
	private List<Entity> removeEntities; //We need this to remove entities we dont need anymore.
	private HashMap<Item, Point> itemsOnGround;
	
	private Player player;
	private String gameFile;
	private int ID;
	
	public GameSession(int IDNum)
	{
		entities = new ArrayList<Entity>();
		removeEntities = new ArrayList<Entity>();
		itemsOnGround = new HashMap<Item, Point>();
		player = null;
		this.ID = IDNum;
		entityId = 0;
	}
	
	public void setEntities(ArrayList<Entity> entities)
	{
		this.entities = entities;
	}
	
	public void addEntity(Entity entity)
	{
		entities.add(entity);
	}
	
	public List<Entity> getEntities()
	{
		return entities;
	}
	
	public Entity getEntity(Entity entity)
	{
		if(entities.contains(entity))
		{
			for(Entity e : entities)
				if(e.equals(entity))
					return e;
		}
		return null;
	}
	
	public List<Entity> getRemoveListEntities() {
		return removeEntities;
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public void setPlayer(Player player)
	{
		if(this.player == null)
		{
			this.player = player;
			entities.add(player);
		}
	}
	
	public String getGameFile()
	{
		return gameFile;
	}
	
	public void setGameFile(String gameFile)
	{
		this.gameFile = gameFile;
	}
	
	public HashMap<Item, Point> getItemsOnGround() {
		return itemsOnGround;
	}

	public void addItemToGround(Item drop, float xCoord, float yCoord) {
		itemsOnGround.put(drop, new Point(xCoord, yCoord));
	}

}
