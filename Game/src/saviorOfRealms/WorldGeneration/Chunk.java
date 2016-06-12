package saviorOfRealms.WorldGeneration;

import java.util.List;

import saviorOfRealms.Tiles.Tile;

public class Chunk {
	
	private int chunkMap[][];
	private List< List< Tile > > tileMap; //2 Dimensional list of tiles, takes the numbers from chunkmap and generates the tiles.
	
	private float startingX;
	private float startingY;
	private float width;
	private float height;
	
	public Chunk(float startingX, float startingY) {
		this.startingX = startingX;
		this.startingY = startingY;
		
		chunkMap = new HexMapGenerator().getDiamondSquare(); //Generate the Map of this chunk.
	}
	
	public float getStartingX() {
		return startingX;
	}
	
	public float getStartingY() {
		return startingY;
	}
	
	public int[][] getChunkMap() {
		return chunkMap;
	}

}
