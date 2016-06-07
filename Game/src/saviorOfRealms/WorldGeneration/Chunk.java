package saviorOfRealms.WorldGeneration;

public class Chunk {
	
	private int chunkMap[][];
	
	private float startingX;
	private float startingY;
	
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
