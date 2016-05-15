package Game.TrentinAndErikGame.Util;

import java.util.ArrayList;

import Game.TrentinAndErikGame.entities.Entity;
import Game.TrentinAndErikGame.entities.Player;

/**
 * GameSession will hold all variables where we need access to them globally, such as an array of all enemies on the map, or other important variables.
 * @author Trentin
 *
 */
public class GameSession 
{
	
	private ArrayList<Entity> entities;
	
	private Player player;
	private String gameFile;
	private int ID;
	
	public GameSession(int IDNum)
	{
		entities = new ArrayList<Entity>();
		player = null;
		this.ID = IDNum;
	}
	
	public void setEntities(ArrayList<Entity> entities)
	{
		this.entities = entities;
	}
	
	public void addEntity(Entity entity)
	{
		entities.add(entity);
	}
	
	public ArrayList<Entity> getEntities()
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

}
