package cz.kofron.leangine.shader;
import android.content.*;
import java.io.*;
import android.opengl.*;

import cz.kofron.leangine.util.Util;

public class SimpleColorShader extends SimpleShader
{
	public int a_color;
	
	@Override
	public void load(Context context) throws IOException, ShaderException
	{
		int vs = ShaderLoader.loadShader(GLES20.GL_VERTEX_SHADER, Util.loadFile(Util.getFile(context, "SimpleColorShader.vs")));
		Util.checkGLError("int vs = ShaderLoader.loadShader(GLES20.GL_VERTEX_SHADER, Util.loadFile(Util.getFile(context, \"SimpleColorShader.vs\")));");
		int fs = ShaderLoader.loadShader(GLES20.GL_FRAGMENT_SHADER, Util.loadFile(Util.getFile(context, "SimpleColorShader.fs")));
		Util.checkGLError("int fs = ShaderLoader.loadShader(GLES20.GL_FRAGMENT_SHADER, Util.loadFile(Util.getFile(context, \"SimpleColorShader.fs\")));");
		program = ShaderLoader.makeProgram(vs, fs);
		Util.checkGLError("program = ShaderLoader.makeProgram(vs, fs);");
	}

	@Override
	public void bindLocations()
	{
		super.bindLocations();

		a_color = GLES20.glGetAttribLocation(program, "a_color");
		Util.checkGLError("a_color = GLES20.glGetAttribLocation(program, \"a_color\");");
	}
}
