package saviorOfRealms.WorldGeneration;

public class HexMapGenerator {
	
	public HexMapGenerator() {
		
	}
	
	public int[][] getDiamondSquare() {
		MidpointDisplacement md = new MidpointDisplacement();
		return md.getMap();
	}

}
