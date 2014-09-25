package cz.kofron.leangine.shader;
import android.opengl.*;
import android.content.*;
import java.io.*;

import cz.kofron.leangine.util.Util;

public abstract class AbstractShader
{
	public int program;
		
	public int u_model;
	public int u_view;
	public int u_proj;
	public int u_norm;
	
	public abstract void load(Context context) throws IOException, ShaderException;
	
	public void bindLocations()
	{
		u_model = GLES20.glGetUniformLocation(program, "u_model");
		Util.checkGLError("u_model = GLES20.glGetUniformLocation(program, \"u_model\");");
		u_view = GLES20.glGetUniformLocation(program, "u_view");
		Util.checkGLError("u_view = GLES20.glGetUniformLocation(program, \"u_view\");");
		u_proj = GLES20.glGetUniformLocation(program, "u_proj");
		Util.checkGLError("u_proj = GLES20.glGetUniformLocation(program, \"u_proj\");");
		u_norm = GLES20.glGetUniformLocation(program, "u_norm");
		Util.checkGLError("u_norm = GLES20.glGetUniformLocation(program, \"u_norm\");");
	}
}
