package cz.kofron.leangine;
import android.opengl.*;

public class Camera
{
	public static float [] DEFAULT =
	{
		0.0f, 0.0f,-2.0f,
	 	0.0f, 0.0f, 0.0f,
	 	0.0f, 1.0f, 0.0f,
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
		eyeX = DEFAULT[0];
		eyeY = DEFAULT[1];
		eyeZ = DEFAULT[2];
		
		ctrX = DEFAULT[3];
		ctrY = DEFAULT[4];
		ctrZ = DEFAULT[5];
		
		upX = DEFAULT[6];
		upY = DEFAULT[7];
		upZ = DEFAULT[8];
	}
	
	public void provideView(float [] mat)
	{
		Matrix.setLookAtM(mat, 0, eyeX, eyeY, eyeZ,
			ctrX, ctrY, ctrZ, upX, upY, upZ);
	}
}

