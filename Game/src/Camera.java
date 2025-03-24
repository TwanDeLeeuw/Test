import com.jogamp.opengl.math.Matrix4f;
import com.jogamp.opengl.math.Vec3f;

public class Camera {
	
	private Vec3f position;
	private Vec3f orientation;
	
	private float yaw = 0;
	private float pitch = 0;
	
	public Camera(float x, float y, float z) {
		position = new Vec3f(x, y, z);
		orientation = new Vec3f(0, 1, 0);
	}
	
	public void translate(float x, float y, float z) {
		position.add(x, y, z);
	}
	
	public void setPosition(float x, float y, float z) {
		position.set(x, y, z);
	}
	
	public void setPosition(Vec3f position) {
		this.position.set(position);
	}
	
	public void setLookDir(float x, float y) {
		yaw = x;
		pitch = y + 135;
	}

	public Matrix4f getMatrix() {
		Vec3f lookPoint = new Vec3f(0, 0, 0);
		
		lookPoint.setX((float)(Math.cos(Math.toRadians(pitch)) * Math.cos(Math.toRadians(yaw))));
		lookPoint.setY((float)(Math.sin(Math.toRadians(pitch))));
		lookPoint.setZ((float)(Math.cos(Math.toRadians(pitch)) * Math.sin(Math.toRadians(yaw))));
		
		lookPoint.add(position);
		
		Matrix4f matrix = new Matrix4f();
		matrix.setToLookAt(position, lookPoint, orientation, new Matrix4f());
		return matrix;
	}
}
