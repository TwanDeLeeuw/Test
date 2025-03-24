import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.util.GLBuffers;

public class Loader {
	
	private GL4 gl;
	
	private ArrayList<IntBuffer> vaos = new ArrayList<IntBuffer>();
	private ArrayList<IntBuffer> vbos = new ArrayList<IntBuffer>();
	
	public Loader(GL4 gl) {
		this.gl = gl;
	}
	
	public RawModel loadToVAO(float[] positions) {
		int vaoID = createVAO();
		storeDataInAttributeList(0, positions);
		unbindVAO();
		return new RawModel(vaoID, positions.length/3);
	}
	
	public void cleanUp() {
		for(IntBuffer vao : vaos)
			gl.glDeleteVertexArrays(vao.capacity(), vao);
		for(IntBuffer vbo : vbos)
			gl.glDeleteBuffers(vbo.capacity(), vbo);
	}

	private int createVAO() {
		IntBuffer intBuffer = IntBuffer.allocate(1);
		vaos.add(intBuffer);
		gl.glGenVertexArrays(1, intBuffer);
		gl.glBindVertexArray(intBuffer.get(0));
		return intBuffer.get(0);
	}
	
	private void storeDataInAttributeList(int attributeNumber, float[] data) {
		IntBuffer intBuffer = IntBuffer.allocate(1);
		vbos.add(intBuffer);
		gl.glGenBuffers(1, intBuffer);
		gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, intBuffer.get(0));
		FloatBuffer floatBuffer = GLBuffers.newDirectFloatBuffer(data);
		gl.glBufferData(GL4.GL_ARRAY_BUFFER, floatBuffer.limit() * GLBuffers.SIZEOF_FLOAT, floatBuffer, GL4.GL_STATIC_DRAW);
		gl.glVertexAttribPointer(attributeNumber, 3, GL4.GL_FLOAT, false, 0, 0);
		gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, 0);
	}
	
	private void unbindVAO() {
		gl.glBindVertexArray(0);
	}
	
}
