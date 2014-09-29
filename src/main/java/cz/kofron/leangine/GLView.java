package cz.kofron.leangine;

import android.opengl.*;
import android.content.*;
import android.view.*;

public class GLView extends GLSurfaceView implements GestureDetector.OnGestureListener
{
	private GestureDetector gestureDetector = null;
	private GLRenderer renderer = null;
	private Leangine leangine;

	public GLView(Context context, Leangine leangine)
	{
		super(context);

		this.leangine = leangine;

		setEGLContextClientVersion(2);
		setDebugFlags(GLSurfaceView.DEBUG_CHECK_GL_ERROR | GLSurfaceView.DEBUG_LOG_GL_CALLS);

		renderer = new GLRenderer(leangine);
		setRenderer(renderer);

		gestureDetector = new GestureDetector(context, this);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		gestureDetector.onTouchEvent(event);
		return super.onTouchEvent(event);
	}


	@Override
	public boolean onDown(MotionEvent p1)
	{
		return false;
	}

	@Override
	public void onShowPress(MotionEvent p1)
	{
	}

	@Override
	public boolean onSingleTapUp(MotionEvent p1)
	{
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent p1, MotionEvent p2, float p3, float p4)
	{
		return true;
	}

	@Override
	public void onLongPress(MotionEvent p1)
	{

	}

	@Override
	public boolean onFling(MotionEvent p1, MotionEvent p2, float p3, float p4)
	{

		return false;
	}
}

