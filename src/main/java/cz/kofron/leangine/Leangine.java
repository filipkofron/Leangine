package cz.kofron.leangine;
import android.content.Context;
import android.opengl.GLES20;

import java.util.ArrayList;

import cz.kofron.leangine.scene.CameraTransform;
import cz.kofron.leangine.scene.SceneRoot;
import cz.kofron.leangine.scene.ScreenTransform;
import cz.kofron.leangine.shader.ShaderCollection;
import cz.kofron.leangine.texture.TextureHelper;
import cz.kofron.leangine.transform.Transformer;

public class Leangine
{
	private ArrayList<CameraTransform> cameras = new ArrayList<CameraTransform>();
	private ScreenTransform screen = new ScreenTransform();
	private SceneRoot sceneRoot = new SceneRoot();
	private Transformer transformer = new Transformer();
	private GLView glView;

	private int currentCam = 0;
	
	private Context context;
	
	public Leangine(Context context)
	{
		this.context = context;
		TextureHelper.context = context;
		cameras.add(new CameraTransform());
		glView = new GLView(context, this);
	}

	public GLView getGlView()
	{
		return glView;
	}

	public void onScreenResize(int w, int h)
	{
		GLES20.glViewport(0, 0, w, h);
		screen.update(w, h);
	}

	public ScreenTransform getScreen()
	{
		return screen;
	}

	public SceneRoot getSceneRoot()
	{
		return sceneRoot;
	}

	public void onInitializeGLContext()
	{
		ShaderCollection.loadShaders(context);
		
		GLES20.glDisable(GLES20.GL_CULL_FACE);
		GLES20.glEnable(GLES20.GL_DEPTH_TEST);
		/*
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
		
		sceneRoot.addNode(mg3, t);*/
	}
	
	public void drawFrame()
	{
		sceneRoot.updateTransforms();
		
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
		GLES20.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);

		cameras.get(currentCam).onUpdate();
		cameras.get(currentCam).provide(transformer.view);

		screen.onUpdate();
		screen.provide(transformer.projection);
		
		sceneRoot.drawScene(transformer);
	}
}
