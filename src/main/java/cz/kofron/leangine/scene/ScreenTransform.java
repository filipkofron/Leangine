package cz.kofron.leangine.scene;
import android.opengl.*;

import cz.kofron.leangine.transform.Transform;

public class ScreenTransform extends Transform
{
	public final static float DEFAULT_FOV = 30.0f;
	public final static float DEFAULT_NEAR = 0.2f;
	public final static float DEFAULT_FAR = 100.0f;
	
	private float width = 1.0f;
	private float height = 1.0f;
	private float ratio = 1.0f;
	
	private float fov;
	private float near;
	private float far;

	private float [] tempMatrix = new float[16];
	
	public ScreenTransform(float fov, float near, float far)
	{
		this.fov = fov;
		this.near = near;
		this.far = far;
	}
	
	public ScreenTransform()
	{
		fov = DEFAULT_FOV;
		near = DEFAULT_NEAR;
		far = DEFAULT_FAR;
	}

	@Override
	protected void update()
	{
		Matrix.setIdentityM(matrix, 0);
		Matrix.perspectiveM(matrix, 0, fov, ratio, near, far);
	}

	public void provide(float [] mat)
	{
		Matrix.setIdentityM(tempMatrix, 0);
		Matrix.multiplyMM(mat, 0, matrix, 0, tempMatrix, 0);
	}

	public void update(float width, float height)
	{
		this.width = width;
		this.height = height;
		
		ratio = width / height;

		makeDirty();
	}
}
