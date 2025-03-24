package display;

import java.util.ArrayList;
import java.nio.IntBuffer;

import com.jogamp.newt.NewtFactory;
import com.jogamp.newt.Screen;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.Animator;

import input.KeyManager;
import mesh.Mesh;

public class Display implements GLEventListener {
	
	GL4 gl;
	
	private GLWindow window;
	private Animator anim;
	
	private KeyManager keyManager;
	
	private ArrayList<Mesh> meshes = new ArrayList<Mesh>();

	public Display() {
		
		GLProfile glp = GLProfile.getMaxProgrammableCore(true);
		GLCapabilities caps = new GLCapabilities(glp);
		caps.setDoubleBuffered(true);
		caps.setHardwareAccelerated(true);
		
		com.jogamp.newt.Display disp = NewtFactory.createDisplay("Demo");
		Screen screen = NewtFactory.createScreen(disp, 0);
		window = GLWindow.create(screen, caps);
		window.setSize(800, 600);
		window.setTitle("Hello Java OpenGL!");
		window.setVisible(true);
		window.addKeyListener(keyManager);
		
		window.addGLEventListener(this);
		
		anim = new Animator(window);
		anim.start();
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		gl = drawable.getGL().getGL4();
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		anim.stop();
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		GL4 gl = drawable.getGL().getGL4();
		for(Mesh m : meshes) draw(gl, o.getVbIndex(), o.getIbIndex());
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		
	}
	
	private void draw(GL4 gl, int vb, int ib) {
		//gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, vb);
		gl.glBindVertexArray(ib);
		gl.glDrawArrays(GL4.GL_TRIANGLES, 0, 36);

	}
	
	public void addMesh(Mesh m) {
		IntBuffer buffer = IntBuffer.allocate(1);
		gl.glGenBuffers(1, buffer);
		m.setVbIndex(buffer.get(0));
		meshes.add(m);
	}
	
	public void removeDrawObject(Mesh m) {
		IntBuffer buffer = IntBuffer.allocate(1);
		buffer.put(m.getVbIndex());
		gl.glDeleteBuffers(1, buffer);
	}
}
