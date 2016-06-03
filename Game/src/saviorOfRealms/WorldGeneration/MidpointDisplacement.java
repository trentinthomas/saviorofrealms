package saviorOfRealms.WorldGeneration;

import com.badlogic.gdx.math.MathUtils;

public class MidpointDisplacement {
 public float deepWaterThreshold, 
     shallowWaterThreshold,
     desertThreshold,
     plainsThreshold,
     grasslandThreshold,
     forestThreshold,
     hillsThreshold,
     mountainsThreshold;
 
 public int n;
 public int wmult, hmult;
  
 public float smoothness;
 
 public MidpointDisplacement() {
   
  // the thresholds which determine cutoffs for different terrain types
  deepWaterThreshold = 0.5f;
  shallowWaterThreshold = 0.55f;
  desertThreshold = 0.58f;
  plainsThreshold = 0.62f;
  grasslandThreshold = 0.7f;
  forestThreshold = 0.8f;
  hillsThreshold = 0.88f;
  mountainsThreshold = 0.95f;
   
  // n partly controls the size of the map, but mostly controls the level of detail available
  n = 7;
   
  // wmult and hmult are the width and height multipliers.  They set how separate regions there are
  wmult=6;
  hmult=4;
   
  // Smoothness controls how smooth the resultant terain is.  Higher = more smooth
  smoothness = 3f;
 }
  
 public int[][] getMap() {
   
  // get the dimensions of the map
  int power = (int) Math.pow(2,n);
  int width = wmult*power + 1;
  int height = hmult*power + 1;
   
  // initialize arrays to hold values 
  float[][] map = new float[width][height];
  int[][] returnMap = new int[width][height];
   
   
  int step = power/2;
  float sum;
  int count;
   
  // h determines the fineness of the scale it is working on.  After every step, h
  // is decreased by a factor of "smoothness"
  float h = 1;
   
  // Initialize the grid points
  for (int i=0; i<width; i+=2*step) {
   for (int j=0; j<height; j+=2*step) {
    map[i][j] = MathUtils.random(2*h);
   }
  }
 
  // Do the rest of the magic
  while (step > 0) {   
   // Diamond step
   for (int x = step; x < width; x+=2*step) {
    for (int y = step; y < height; y+=2*step) {
     sum = map[x-step][y-step] + //down-left
        map[x-step][y+step] + //up-left
        map[x+step][y-step] + //down-right
        map[x+step][y+step];  //up-right
     map[x][y] = sum/4 + MathUtils.random(-h,h);
    }
   }
    
   // Square step
   for (int x = 0; x < width; x+=step) {
    for (int y = step*(1-(x/step)%2); y<height; y+=2*step) {
     sum = 0;
     count = 0;
     if (x-step >= 0) {
      sum+=map[x-step][y];
      count++;
     }
     if (x+step < width) {
      sum+=map[x+step][y];
      count++;
     }
     if (y-step >= 0) {
      sum+=map[x][y-step];
      count++;
     }
     if (y+step < height) {
      sum+=map[x][y+step];
      count++;
     }
     if (count > 0) map[x][y] = sum/count + MathUtils.random(-h,h);
     else map[x][y] = 0;
    }
     
   }
   h /= smoothness;
   step /= 2;
  }
   
  // Normalize the map
  float max = Float.MIN_VALUE;
  float min = Float.MAX_VALUE;
  for (float[] row : map) {
   for (float d : row) {
    if (d > max) max = d;
    if (d < min) min = d;
   }
  }
   
  // Use the thresholds to fill in the return map
  for(int row = 0; row < map.length; row++){
   for(int col = 0; col < map[row].length; col++){
    map[row][col] = (map[row][col]-min)/(max-min);
    if (map[row][col] < deepWaterThreshold) returnMap[row][col] = 0;
    else if (map[row][col] < shallowWaterThreshold) returnMap[row][col] = 1;
    else if (map[row][col] < desertThreshold) returnMap[row][col] = 2;
    else if (map[row][col] < plainsThreshold) returnMap[row][col] = 3;
    else if (map[row][col] < grasslandThreshold) returnMap[row][col] = 4;
    else if (map[row][col] < forestThreshold) returnMap[row][col] = 5;
    else if (map[row][col] < hillsThreshold) returnMap[row][col] = 6;
    else if (map[row][col] < mountainsThreshold) returnMap[row][col] = 7;
    else returnMap[row][col] = 8;
   }
  }
 
  return returnMap;
 }
}
