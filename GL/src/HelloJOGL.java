import java.io.File;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import com.jogamp.newt.Display;
import com.jogamp.newt.NewtFactory;
import com.jogamp.newt.Screen;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.GLBuffers;
import com.jogamp.opengl.util.glsl.ShaderCode;
import com.jogamp.opengl.util.glsl.ShaderProgram;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;
import com.jogamp.opengl.math.Matrix4f;
import com.jogamp.opengl.math.Vec3f;

public class HelloJOGL implements GLEventListener {
	
	private GLWindow window;
	private Animator anim;
	private ShaderProgram shaderProgram;
	private IntBuffer VBO, VAO, tex;
	private TextureData texture;
	private Player player = new Player(0, 0, 0);
	
	private Cube[] cubes = new Cube[9];
	
	private int meshCount = 9;
	
	private boolean[] keys = new boolean[255];
	
	Vec3f cameraPos = new Vec3f(0.0f, 0.0f, 3.0f);
	Vec3f cameraFront = new Vec3f(0.0f, 0.0f, -1.0f);
	Vec3f cameraUp = new Vec3f(0.0f, 1.0f, 0.0f);

	private Matrix4f model, view, projection;
	
	public static void main(String[] args) {
		HelloJOGL hello = new HelloJOGL();
	}
	
	public HelloJOGL() {
		GLProfile glp = GLProfile.getMaxProgrammableCore(true);
		GLCapabilities caps = new GLCapabilities(glp);
		caps.setDoubleBuffered(true);
		caps.setHardwareAccelerated(true);
		
		Display disp = NewtFactory.createDisplay("Demo");
		Screen screen = NewtFactory.createScreen(disp, 0);
		window = GLWindow.create(screen, caps);
		window.setSize(800, 600);
		window.setTitle("Hello Java OpenGL!");
		window.setVisible(true);
		window.addKeyListener(player);
		
		window.addGLEventListener(this);
		
		anim = new Animator(window);
		anim.start();
		
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 3; y++) {
				cubes[x * 3 + y] = new Cube(x, y, 0);
			}
		}
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		GL4 gl = drawable.getGL().getGL4();
		
		gl.glEnable(GL4.GL_DEPTH_TEST);
		
		gl.glViewport(0, 0, 800, 600);
		
		ShaderCode vertexShader = ShaderCode.create(gl, GL4.GL_VERTEX_SHADER, this.getClass(), "shaders", "shaders/bin", "vertex", true);
		vertexShader.compile(gl, System.err);
		vertexShader.defaultShaderCustomization(gl, true, true);
		
		ShaderCode fragmentShader = ShaderCode.create(gl, GL4.GL_FRAGMENT_SHADER, this.getClass(), "shaders", "shaders/bin", "fragment", true);
		fragmentShader.compile(gl, System.err);
		fragmentShader.defaultShaderCustomization(gl, true, true);
		
		shaderProgram = new ShaderProgram();
		shaderProgram.init(gl);
		shaderProgram.add(vertexShader);
		shaderProgram.add(fragmentShader);
		shaderProgram.link(gl, System.err);
		
		projection = new Matrix4f();
		//view = new Matrix4f();
		model = new Matrix4f();
		
		projection.setToPerspective((float)Math.toRadians(45), 800.0f / 600.0f, 0.1f, 100.0f);
		//model.setToRotationAxis((float)Math.toRadians(45), 1.0f, 1.0f, 0.0f);
		
		gl.glDeleteShader(vertexShader.id());
		gl.glDeleteShader(fragmentShader.id());
		
		VAO = IntBuffer.allocate(meshCount);
		
		
		VBO = IntBuffer.allocate(meshCount);
		tex = IntBuffer.allocate(1);
		
		gl.glGenBuffers(meshCount, VBO);
		
		gl.glGenVertexArrays(meshCount, VAO);
		
		for(int i = 0; i < meshCount; i++) {
			gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, VBO.get(i));
			gl.glBindVertexArray(VAO.get(i));
			gl.glVertexAttribPointer(0, 1, GL4.GL_FLOAT, false, 3 * GLBuffers.SIZEOF_FLOAT, 0);
			gl.glVertexAttribPointer(1, 2, GL4.GL_FLOAT, false, 3 * GLBuffers.SIZEOF_FLOAT, 1 * GLBuffers.SIZEOF_FLOAT);
			gl.glEnableVertexAttribArray(0);
			gl.glEnableVertexAttribArray(1);
		}
		
		for(int i = 0; i < meshCount; i++) {
			gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, VBO.get(i));
			gl.glBindVertexArray(VAO.get(i));
			FloatBuffer vertices = GLBuffers.newDirectFloatBuffer(cubes[i].getMeshData());
			gl.glBufferData(GL4.GL_ARRAY_BUFFER, vertices.limit() * GLBuffers.SIZEOF_FLOAT, vertices, GL4.GL_STATIC_DRAW);
		}
		
		try {
			texture = TextureIO.newTextureData(gl.getGLProfile(), new File("res/container2.png"), GL4.GL_TEXTURE_2D, GL4.GL_RGBA, false, "png");
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		gl.glGenTextures(1, tex);
		gl.glBindTexture(GL4.GL_TEXTURE_2D, tex.get(0));
		
		gl.glTexParameteri(GL4.GL_TEXTURE_2D, GL4.GL_TEXTURE_WRAP_S, GL4.GL_REPEAT);
		gl.glTexParameteri(GL4.GL_TEXTURE_2D, GL4.GL_TEXTURE_WRAP_T, GL4.GL_REPEAT);
		gl.glTexParameteri(GL4.GL_TEXTURE_2D, GL4.GL_TEXTURE_MIN_FILTER, GL4.GL_LINEAR);
		gl.glTexParameteri(GL4.GL_TEXTURE_2D, GL4.GL_TEXTURE_MAG_FILTER, GL4.GL_LINEAR);
		gl.glTexImage2D(GL4.GL_TEXTURE_2D, 0, GL4.GL_RGBA, texture.getWidth(), texture.getHeight(), 0, GL4.GL_RGBA, GL4.GL_UNSIGNED_BYTE, texture.getBuffer());
		gl.glGenerateMipmap(GL4.GL_TEXTURE_2D);
	}
	
	@Override
	public void dispose(GLAutoDrawable drawable) {
		GL4 gl = drawable.getGL().getGL4();
		gl.glDeleteVertexArrays(1, VAO);
		gl.glDeleteBuffers(1, VBO);
		anim.stop();
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		GL4 gl = drawable.getGL().getGL4();
		gl.glUseProgram(shaderProgram.program());
		gl.glClear(GL4.GL_COLOR_BUFFER_BIT | GL4.GL_DEPTH_BUFFER_BIT);
		
		gl.glActiveTexture(GL4.GL_TEXTURE0);
		gl.glBindTexture(GL4.GL_TEXTURE_2D, tex.get(0));
		
		gl.glUniform1i(gl.glGetUniformLocation(shaderProgram.program(), "tex"), 0);
		
		player.tick();
		
		long currMillis = System.currentTimeMillis();
		model.loadIdentity();
		//model.setToRotationAxis((float) (2 * Math.PI * (currMillis % 2000) / 2000.0), 1.0f, 1.0f, 0.0f);
		
		view = player.getCamera().getMatrix();
		//view.setToRotationAxis((float)((currMillis % 2000) / 2000.0), 1.0f, 0.0f, 0.0f);
		
		int modelLocation = gl.glGetUniformLocation(shaderProgram.program(), "model");
		int projectionLocation = gl.glGetUniformLocation(shaderProgram.program(), "projection");
		int viewLocation = gl.glGetUniformLocation(shaderProgram.program(), "view");
		
		gl.glUniformMatrix4fv(modelLocation, 1, false, model.get(new float[16]), 0);
		gl.glUniformMatrix4fv(projectionLocation, 1, false, projection.get(new float[16]), 0);
		gl.glUniformMatrix4fv(viewLocation, 1, false, view.get(new float[16]), 0);
		
		for(int i = 0; i < meshCount; i++) {
			draw(gl, VBO.get(i), VAO.get(i));
		}

		//gl.glDrawElements(GL4.GL_TRIANGLES, VAO.limit(), GL4.GL_FLOAT, 0);

	}
	
	private void draw(GL4 gl, int vb, int ib) {
		//gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, vb);
		gl.glBindVertexArray(ib);
		gl.glDrawArrays(GL4.GL_TRIANGLES, 0, 36);

	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL4 gl = drawable.getGL().getGL4();
		
	}

}
