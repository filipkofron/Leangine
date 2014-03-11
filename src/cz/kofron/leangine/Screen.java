package cz.kofron.leangine;
import android.opengl.*;

public class Screen
{
	public final static float DEFAULT_FOV = 75.0f;
	public final static float DEFAULT_NEAR = 1.0f;
	public final static float DEFAULT_FAR = 100.0f;
	
	private float width = 1.0f;
	private float height = 1.0f;
	private float ratio = 1.0f;
	
	private float fov;
	private float near;
	private float far;
	
	public Screen(float fov, float near, float far)
	{
		this.fov = fov;
		this.near = near;
		this.far = far;
	}
	
	public Screen()
	{
		fov = DEFAULT_FOV;
		near = DEFAULT_NEAR;
		far = DEFAULT_FAR;
	}
	
	public void update(float width, float height)
	{
		this.width = width;
		this.height = height;
		
		ratio = width / height;
	}
	
	public void provideProj(float [] mat)
	{
		Matrix.perspectiveM(mat, 0, fov, ratio, near, far);
	}
}
