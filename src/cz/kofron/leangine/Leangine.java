package cz.kofron.leangine;
import java.util.*;
import android.opengl.*;
import android.content.*;

public class Leangine
{
	private ArrayList<Camera> cameras = new ArrayList<Camera>();
	private Screen screen = new Screen();
	private SceneRoot sceneRoot = new SceneRoot();
	private Transformer transformer = new Transformer();
	
	private int currentCam = 0;
	
	private Context context;
	
	public Leangine(Context context)
	{
		this.context = context;
		cameras.add(new Camera());
	}
	
	public void onScreenResize(int w, int h)
	{
		GLES20.glViewport(0, 0, w, h);
		screen.update(w, h);
	}
	
	public void onInitializeGLContext()
	{
		ShaderCollection.loadShaders(context);
		
		GLES20.glDisable(GLES20.GL_CULL_FACE);
		GLES20.glEnable(GLES20.GL_DEPTH_TEST);
		
		SimpleModel sm = new SimpleModel(new TestSimpleModelData());
		sm.initializeGL();
		sm.uploadData();
		
		ModelGroup mg = new ModelGroup();
		mg.addNode(sm, new RotatingTransform(0,0,90));
		
		RotatingTransform t = new RotatingTransform(80,0,0);
		RotatingTransform t2 = new RotatingTransform(0,70,0);
		
		sceneRoot.addNode(mg, t);
		
		SimpleColorModel cm2 = new SimpleColorModel(new TestSimpleColorModelData());
		cm2.initializeGL();
		cm2.uploadData();
		
		ModelGroup mg2 = new ModelGroup();
		mg2.addNode(cm2, t2);
		
		sceneRoot.addNode(mg2, t);
	}
	
	public void drawFrame()
	{
		sceneRoot.updateTransforms();
		
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
		GLES20.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
		
		cameras.get(currentCam).provideView(transformer.view);
		screen.provideProj(transformer.projection);
		
		sceneRoot.onDraw(transformer);
	}
}
