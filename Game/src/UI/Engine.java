package UI;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import Util.Window;
import saviorOfRealms.connections.ConnectionFactory;

public class Engine extends StateBasedGame{
	
	public static final String gameName = "Savior of Realms";
	public static final int menu = 0;
	public static final int gameSelect = 1;
	public static final int playerSelect = 2;
	public static final int play = 3;
	public static final int paused = 4;
	
	
	public Engine()
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
		gc.setMaximumLogicUpdateInterval(60);
		gc.setTargetFrameRate(60);
		gc.setAlwaysRender(true);
		gc.setShowFPS(true);
		gc.setVSync(true);
		
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
			appgc = new AppGameContainer(new Engine());
			appgc.setDisplayMode(Window.WIDTH, Window.HEIGHT, false);
			appgc.start();
		}
		catch (SlickException e) { e.printStackTrace(); }
	}

}
