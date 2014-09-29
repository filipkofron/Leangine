package cz.kofron.leangine.scene;
import android.opengl.*;

import cz.kofron.leangine.transform.Transform;

public class CameraTransform extends Transform
{
	private float [] tempMatrix = new float[16];

	public static float [] DEFAULT =
	{
		0.0f, 0.0f,-2.0f,
	 	0.0f, 0.0f, 0.0f,
	 	0.0f, 1.0f, 0.0f,
	};
	
	private float eyeX, eyeY, eyeZ;
	private float ctrX, ctrY, ctrZ;
	private float upX, upY, upZ;
	
	public CameraTransform(float eyeX, float eyeY, float eyeZ,
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
	
	public CameraTransform()
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

	@Override
	protected void update()
	{
		Matrix.setIdentityM(matrix, 0);
		Matrix.setLookAtM(matrix, 0, eyeX, eyeY, eyeZ,
				ctrX, ctrY, ctrZ, upX, upY, upZ);
	}

	public void provide(float [] mat)
	{
		Matrix.setIdentityM(tempMatrix, 0);
		Matrix.multiplyMM(mat, 0, matrix, 0, tempMatrix, 0);
	}
}
