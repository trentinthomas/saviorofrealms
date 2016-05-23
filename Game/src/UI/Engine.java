package UI;

import java.io.File;

/*import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;*/
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/*import Util.Resources;*/
import Util.Window;

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
		/*new Resources();*/
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
	
	public static void main(String[] args) throws SlickException 
	{
		AppGameContainer appgc;
		
/*		try {
			Display.setDisplayMode(new DisplayMode(Window.WIDTH, Window.HEIGHT));
			Display.create();
			
		} catch (LWJGLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		try	
		{
			appgc = new AppGameContainer(new Engine());
			//appgc.setDisplayMode(1280, 768, false);
			appgc.setDisplayMode(808, 500, false);
			appgc.setAlwaysRender(true);
			appgc.start();
		}
		catch (SlickException e) { e.printStackTrace(); }
	}

}