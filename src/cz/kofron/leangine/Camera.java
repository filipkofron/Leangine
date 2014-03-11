package cz.kofron.leangine;
import android.opengl.*;

public class Camera
{
	public static float [] DEFAULTA =
	{0.0f, 0.0f, -5.0f,
	};
	
	private float eyeX, eyeY, eyeZ;
	private float ctrX, ctrY, ctrZ;
	private float upX, upY, upZ;
	
	public Camera(float eyeX, float eyeY, float eyeZ,
		float ctrX, float ctrY, float ctrZ,
		float upX, float upY, float upZ)
	{
		this.eyeX = eyeX;
		this.eyeY = eyeY;
		this.eyeZ = eyeZ;
		
		this.ctrX = ctrX;
		this.ctrY = ctrY;
		this.ctrZ = ctrZ;
		
		this.upX = upX;
		this.upY = upY;
		this.upZ = upZ;
	}
	
	public Camera()
	{
		
	}
	
	public void provideView(float [] mat)
	{
		Matrix.setLookAtM(mat, 0, eyeX, eyeY, eyeZ,
			ctrX, ctrY, ctrZ, upX, upY, upZ);
	}
}

