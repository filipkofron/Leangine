package cz.kofron.leangine;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

public class GLRenderer implements GLSurfaceView.Renderer
{
	private Leangine leangine;

	public GLRenderer(Leangine leangine)
	{
		this.leangine = leangine;
	}

	@Override
	public void onSurfaceCreated(GL10 unused, javax.microedition.khronos.egl.EGLConfig config)
	{
		leangine.onInitializeGLContext();
	}

	@Override
	public void onSurfaceChanged(GL10 unused, int w, int h)
	{
		leangine.onScreenResize(w, h);
	}

	@Override
	public void onDrawFrame(GL10 unused)
	{
		leangine.drawFrame();
	}
}
