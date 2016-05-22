package Util;

/**
 * GameSessionFactory creates a GameSession, which will hold all the variables for the Session.
 * @author Trentin
 *
 */
public class GameSessionFactory 
{
	private static GameSession gameSession;
	private static boolean loaded = false;
	
	public static void createGameSession(int ID)
	{
		gameSession = new GameSession(ID);
	}
	
	public static GameSession getGameSession()
	{
		return gameSession;
	}

	public static boolean hasGameSession() {
		return gameSession != null;
	}

}
