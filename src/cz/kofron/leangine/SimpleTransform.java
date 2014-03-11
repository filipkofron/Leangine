package cz.kofron.leangine;
import android.opengl.*;

public class SimpleTransform extends Transform
{
	public float translX;
	public float translY;
	public float translZ;
	
	public float rotX;
	public float rotY;
	public float rotZ;
	
	@Override
	protected void update()
	{
		Matrix.setIdentityM(matrix, 0);
		Matrix.translateM(matrix, 0, translX, translY, translZ);
		Matrix.rotateM(matrix, 0, rotX, 1.0f, 0.0f, 0.0f);
		Matrix.rotateM(matrix, 0, rotY, 0.0f, 1.0f, 0.0f);
		Matrix.rotateM(matrix, 0, rotZ, 0.0f, 0.0f, 1.0f);
	}

}
