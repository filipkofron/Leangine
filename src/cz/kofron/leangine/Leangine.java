package cz.kofron.leangine;
import java.util.*;
import android.opengl.*;
import android.content.*;

import cz.kofron.leangine.model.ModelGroup;
import cz.kofron.leangine.model.SimpleColorModel;
import cz.kofron.leangine.model.TexturedModel;
import cz.kofron.leangine.model.data.ColorCircleData;
import cz.kofron.leangine.model.data.TestTexturedModelData;
import cz.kofron.leangine.scene.Camera;
import cz.kofron.leangine.scene.SceneRoot;
import cz.kofron.leangine.scene.Screen;
import cz.kofron.leangine.shader.ShaderCollection;
import cz.kofron.leangine.texture.TextureHelper;
import cz.kofron.leangine.transform.RotatingTransform;
import cz.kofron.leangine.transform.SimpleTransform;
import cz.kofron.leangine.transform.Transformer;

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
		TextureHelper.context = context;
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
		
		TexturedModel sm = new TexturedModel(new TestTexturedModelData());
		sm.initializeGL();
		sm.uploadData();
		
		ModelGroup mg = new ModelGroup();
		mg.addNode(sm, new RotatingTransform(0,0,20));
		
		RotatingTransform t = new RotatingTransform(20,0,0);
		RotatingTransform t2 = new RotatingTransform(0,20,0);
		
		sceneRoot.addNode(mg, t);
		ColorCircleData ccd = new ColorCircleData(16);
		SimpleColorModel cm2 = new SimpleColorModel(ccd.makeColorData());
		cm2.initializeGL();
		cm2.uploadData();
		
		ModelGroup mg2 = new ModelGroup();
		mg2.addNode(cm2, t2);
		
		ModelGroup mg3 = new ModelGroup();
		SimpleTransform sm2 = new SimpleTransform();
		sm2.translY += 0.5f;
		mg3.addNode(mg2, sm2);
		
		sceneRoot.addNode(mg3, t);
	}
	
	public void drawFrame()
	{
		sceneRoot.updateTransforms();
		
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
		GLES20.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
		
		cameras.get(currentCam).provideView(transformer.view);
		screen.provideProj(transformer.projection);
		
		sceneRoot.drawScene(transformer);
	}
}
