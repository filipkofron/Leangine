package cz.kofron.leangine;
import android.opengl.*;

public class SimpleShader extends AbstractShader
{
	public int a_pos;
	public int a_norm;
	
	@Override
	public void bindLocations()
	{
		super.bindLocations();
		
		a_pos = GLES20.glGetAttribLocation(program, "a_pos");
		a_norm = GLES20.glGetAttribLocation(program, "a_norm");
	}
}
