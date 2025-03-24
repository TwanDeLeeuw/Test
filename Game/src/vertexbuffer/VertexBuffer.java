package vertexbuffer;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.util.GLBuffers;

public class VertexBuffer {
	
	private GL4 gl;
	private int id;
	
	public VertexBuffer(GL4 gl, float[] data, int size) {
		this.gl = gl;
		IntBuffer ib = IntBuffer.allocate(1);
		gl.glGenBuffers(1, ib);
		id = ib.get(0);
		gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, id);
		FloatBuffer fb = GLBuffers.newDirectFloatBuffer(data);
		gl.glBufferData(GL4.GL_ARRAY_BUFFER, size, fb, GL4.GL_STATIC_DRAW);
	}

	public void bind() {
		
	}
	
}
