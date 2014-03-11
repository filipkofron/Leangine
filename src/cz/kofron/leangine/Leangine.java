package cz.kofron.leangine;
import java.util.*;
import android.opengl.*;

public class Leangine
{
	private ArrayList<Camera> cameras = new ArrayList<Camera>();
	private Screen screen = new Screen();
	private SceneRoot sceneRoot = new SceneRoot();
	private Transformer transformer = new Transformer();
	
	private int currentCam = 0;
	
	public Leangine()
	{
		cameras.add(new Camera());
	}
	
	public void onScreenResize(int w, int h)
	{
		GLES20.glViewport(0, 0, w, h);
		screen.update(w, h);
	}
	
	public void onInitializeGLContext()
	{
		GLES20.glDisable(GLES20.GL_CULL_FACE);
	}
	
	public void drawFrame()
	{
		sceneRoot.updateTransforms();
		
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
		GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		
		sceneRoot.onDraw(transformer);
	}
}
