package UI;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Engine extends StateBasedGame{
	
	public static final String gameName = "Savior of Realms";
	public static final int menu = 0;
	public static final int gameSelect = 1;
	public static final int playerSelect = 2;
	public static final int play = 3;
	public static final int paused = 4;
	
	
	public Engine(String gameName)
	{
		super(gameName);
		this.addState(new Menu(menu));
		this.addState(new GameSelect(gameSelect));
		this.addState(new PlayerSelect(playerSelect));
		this.addState(new Play(play));
		this.addState(new Paused(paused));
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException
	{
		this.getState(menu).init(gc, this);
		this.getState(gameSelect).init(gc, this);
		this.getState(playerSelect).init(gc, this);
		this.getState(play).init(gc, this);
		this.getState(paused).init(gc, this);
		this.enterState(menu);
		
	}
	
	public static void main(String[] args) 
	{
		AppGameContainer appgc;
		try	
		{
			appgc = new AppGameContainer(new Engine(gameName));
			appgc.setDisplayMode(808, 500, false);
			appgc.start();
		}
		catch (SlickException e) { e.printStackTrace(); }
	}

}
