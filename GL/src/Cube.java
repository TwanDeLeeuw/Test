
public class Cube {
	
	private int x, y, z;
	
	public static final int VERTEX_COUNT = 36;
	public static final int VERTEX_SIZE = 3;
	
	public Cube(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float[] getMeshData() {
		
		float[] meshData = new float[] {
				Float.intBitsToFloat((x + 0) << 8 | (y + 0) << 4 | (z + 0)), 0.0f, 0.0f,
				Float.intBitsToFloat((x + 1) << 8 | (y + 0) << 4 | (z + 0)), 1.0f, 0.0f,
				Float.intBitsToFloat((x + 1) << 8 | (y + 1) << 4 | (z + 0)), 1.0f, 1.0f,
				Float.intBitsToFloat((x + 1) << 8 | (y + 1) << 4 | (z + 0)), 1.0f, 1.0f,
				Float.intBitsToFloat((x + 0) << 8 | (y + 1) << 4 | (z + 0)), 0.0f, 1.0f,
				Float.intBitsToFloat((x + 0) << 8 | (y + 0) << 4 | (z + 0)), 0.0f, 0.0f,
				
				Float.intBitsToFloat((x + 0) << 8 | (y + 0) << 4 | (z + 1)), 0.0f, 0.0f,
				Float.intBitsToFloat((x + 1) << 8 | (y + 0) << 4 | (z + 1)), 1.0f, 0.0f,
				Float.intBitsToFloat((x + 1) << 8 | (y + 1) << 4 | (z + 1)), 1.0f, 1.0f,
				Float.intBitsToFloat((x + 1) << 8 | (y + 1) << 4 | (z + 1)), 1.0f, 1.0f,
				Float.intBitsToFloat((x + 0) << 8 | (y + 1) << 4 | (z + 1)), 0.0f, 1.0f,
				Float.intBitsToFloat((x + 0) << 8 | (y + 0) << 4 | (z + 1)), 0.0f, 0.0f,
				
				Float.intBitsToFloat((x + 0) << 8 | (y + 1) << 4 | (z + 1)), 1.0f, 0.0f,
				Float.intBitsToFloat((x + 0) << 8 | (y + 1) << 4 | (z + 0)), 1.0f, 1.0f,
				Float.intBitsToFloat((x + 0) << 8 | (y + 0) << 4 | (z + 0)), 0.0f, 1.0f,
				Float.intBitsToFloat((x + 0) << 8 | (y + 0) << 4 | (z + 0)), 0.0f, 1.0f,
				Float.intBitsToFloat((x + 0) << 8 | (y + 0) << 4 | (z + 1)), 0.0f, 0.0f,
				Float.intBitsToFloat((x + 0) << 8 | (y + 1) << 4 | (z + 1)), 1.0f, 0.0f,
				
				Float.intBitsToFloat((x + 1) << 8 | (y + 1) << 4 | (z + 1)), 1.0f, 0.0f,
				Float.intBitsToFloat((x + 1) << 8 | (y + 1) << 4 | (z + 0)), 1.0f, 1.0f,
				Float.intBitsToFloat((x + 1) << 8 | (y + 0) << 4 | (z + 0)), 0.0f, 1.0f,
				Float.intBitsToFloat((x + 1) << 8 | (y + 0) << 4 | (z + 0)), 0.0f, 1.0f,
				Float.intBitsToFloat((x + 1) << 8 | (y + 0) << 4 | (z + 1)), 0.0f, 0.0f,
				Float.intBitsToFloat((x + 1) << 8 | (y + 1) << 4 | (z + 1)), 1.0f, 0.0f,
				
				Float.intBitsToFloat((x + 0) << 8 | (y + 0) << 4 | (z + 0)), 0.0f, 1.0f,
				Float.intBitsToFloat((x + 1) << 8 | (y + 0) << 4 | (z + 0)), 1.0f, 1.0f,
				Float.intBitsToFloat((x + 1) << 8 | (y + 0) << 4 | (z + 1)), 1.0f, 0.0f,
				Float.intBitsToFloat((x + 1) << 8 | (y + 0) << 4 | (z + 1)), 1.0f, 0.0f,
				Float.intBitsToFloat((x + 0) << 8 | (y + 0) << 4 | (z + 1)), 0.0f, 0.0f,
				Float.intBitsToFloat((x + 0) << 8 | (y + 0) << 4 | (z + 0)), 0.0f, 1.0f,
				
				Float.intBitsToFloat((x + 0) << 8 | (y + 1) << 4 | (z + 0)), 0.0f, 1.0f,
				Float.intBitsToFloat((x + 1) << 8 | (y + 1) << 4 | (z + 0)), 1.0f, 1.0f,
				Float.intBitsToFloat((x + 1) << 8 | (y + 1) << 4 | (z + 1)), 1.0f, 0.0f,
				Float.intBitsToFloat((x + 1) << 8 | (y + 1) << 4 | (z + 1)), 1.0f, 0.0f,
				Float.intBitsToFloat((x + 0) << 8 | (y + 1) << 4 | (z + 1)), 0.0f, 0.0f,
				Float.intBitsToFloat((x + 0) << 8 | (y + 1) << 4 | (z + 0)), 0.0f, 1.0f
		};
		
		return meshData;
	}

}
