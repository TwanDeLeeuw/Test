import com.jogamp.opengl.GL4;

public class Renderer {
	
	private GL4 gl;
	
	public Renderer(GL4 gl) {
		this.gl = gl;
	}
	
	public void prepare() {
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL4.GL_COLOR_BUFFER_BIT);																																												
	}
	
	public void render(RawModel model) {
		gl.glBindVertexArray(model.getVaoID());
		gl.glEnableVertexAttribArray(0);
		gl.glDrawArrays(GL4.GL_TRIANGLES, 0, model.getVertexCount());
		gl.glDisableVertexAttribArray(0);
		gl.glBindVertexArray(0);
	}

}
