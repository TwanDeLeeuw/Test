import java.awt.MouseInfo;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.opengl.math.Vec3f;

public class Player implements KeyListener {
	
	private Vec3f position;
	private float angleX, angleZ;
	
	private boolean[] keys = new boolean[256];
	
	private Camera camera = new Camera(0, 0, 0);
	
	
	public Player(float x, float y, float z) {
		position = new Vec3f(x, y, z);
	}
	
	public void tick() {
		float speed = 0.05f;
		
		angleX = MouseInfo.getPointerInfo().getLocation().y * 0.2f;
		angleZ = MouseInfo.getPointerInfo().getLocation().x * 0.2f;
		
		float sf = (float) Math.sin(Math.toRadians(angleZ));
		float cf = (float) Math.cos(Math.toRadians(angleZ));
		
		if(keys[KeyEvent.VK_W])
			position.add(cf * -speed,  0,  sf * -speed);
		if(keys[KeyEvent.VK_S])
			position.add(cf * speed,  0,  sf * speed);
		if(keys[KeyEvent.VK_A])
			position.add(sf * -speed,  0,  cf * speed);
		if(keys[KeyEvent.VK_D])
			position.add(sf * speed,  0,  cf * -speed);
		if(keys[KeyEvent.VK_SPACE])
			position.add(0,  speed,  0);
		if(keys[KeyEvent.VK_SHIFT])
			position.add(0,  -speed, 0);
		
		camera.setPosition(position);
		camera.setLookDir(angleZ, angleX);
	}
	
	public Camera getCamera() {
		return camera;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() > 255)
			return;
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() > 255)
			return;
		if(e.isAutoRepeat())
			return;
		keys[e.getKeyCode()] = false;
	}

}
