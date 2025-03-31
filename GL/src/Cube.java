
public class Cube {
	
	private float x, y, z;
	
	public static final int VERTEX_COUNT = 36;
	public static final int VERTEX_SIZE = 3;
	
	public Cube(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float[] getMeshData() {
	    float[] meshData = new float[] {
	        // Front face
	        x, y, z, 0.0f, 0.0f,
	        x + 1, y, z, 1.0f, 0.0f,
	        x + 1, y + 1, z, 1.0f, 1.0f,
	        x + 1, y + 1, z, 1.0f, 1.0f,
	        x, y + 1, z, 0.0f, 1.0f,
	        x, y, z, 0.0f, 0.0f,

	        // Back face
	        x, y, z + 1, 0.0f, 0.0f,
	        x + 1, y, z + 1, 1.0f, 0.0f,
	        x + 1, y + 1, z + 1, 1.0f, 1.0f,
	        x + 1, y + 1, z + 1, 1.0f, 1.0f,
	        x, y + 1, z + 1, 0.0f, 1.0f,
	        x, y, z + 1, 0.0f, 0.0f,

	        // Left face
	        x, y + 1, z + 1, 1.0f, 0.0f,
	        x, y + 1, z, 1.0f, 1.0f,
	        x, y, z, 0.0f, 1.0f,
	        x, y, z, 0.0f, 1.0f,
	        x, y, z + 1, 0.0f, 0.0f,
	        x, y + 1, z + 1, 1.0f, 0.0f,

	        // Right face
	        x + 1, y + 1, z + 1, 1.0f, 0.0f,
	        x + 1, y + 1, z, 1.0f, 1.0f,
	        x + 1, y, z, 0.0f, 1.0f,
	        x + 1, y, z, 0.0f, 1.0f,
	        x + 1, y, z + 1, 0.0f, 0.0f,
	        x + 1, y + 1, z + 1, 1.0f, 0.0f,

	        // Bottom face
	        x, y, z, 0.0f, 1.0f,
	        x + 1, y, z, 1.0f, 1.0f,
	        x + 1, y, z + 1, 1.0f, 0.0f,
	        x + 1, y, z + 1, 1.0f, 0.0f,
	        x, y, z + 1, 0.0f, 0.0f,
	        x, y, z, 0.0f, 1.0f,

	        // Top face
	        x, y + 1, z, 0.0f, 1.0f,
	        x + 1, y + 1, z, 1.0f, 1.0f,
	        x + 1, y + 1, z + 1, 1.0f, 0.0f,
	        x + 1, y + 1, z + 1, 1.0f, 0.0f,
	        x, y + 1, z + 1, 0.0f, 0.0f,
	        x, y + 1, z, 0.0f, 1.0f
	    };
	    return meshData;
	}
	
	float getX() {
		return x;
	}
	
	float getY() {
		return y;
	}
	
	float getZ() {
		return z;
	}
}
