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
	private List<Entity> removeEntities; //We need this to remove entities that are dead or that we do not need anymore.
	private List<Item> itemsOnGround;
	private List<Item> removeItemsOnGround; //We need this to remove items that have been picked up or we do not need anymore.
	
	private Player player;
	private String gameFile;
	private int ID;
	
	public GameSession(int IDNum)
	{
		entities = new ArrayList<Entity>();
		removeEntities = new ArrayList<Entity>();
		itemsOnGround = new ArrayList<Item>();
		removeItemsOnGround = new ArrayList<Item>();
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
	
	public List<Item> getItemsOnGround() {
		return itemsOnGround;
	}

	public void addItemToGround(Item drop, float xCoord, float yCoord) {
		drop.setX(xCoord);
		drop.setY(yCoord);
		itemsOnGround.add(drop);
	}
	
	public void removeItemFromGround(Item item)
	{
		removeItemsOnGround.add(item);
	}
	
	public void removeItemsFromGround() {
		itemsOnGround.removeAll(removeItemsOnGround);
	}

}
