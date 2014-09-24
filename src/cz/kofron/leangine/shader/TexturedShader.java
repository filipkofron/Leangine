package cz.kofron.leangine.shader;
import android.content.*;
import java.io.*;
import android.opengl.*;

import cz.kofron.leangine.util.Util;

public class TexturedShader extends SimpleShader
{
	public int a_uv;
	public int u_tex;
	
	@Override
	public void load(Context context) throws IOException, ShaderException
	{
		int vs = ShaderLoader.loadShader(GLES20.GL_VERTEX_SHADER, Util.loadFile(Util.getFile(context, "TexturedShader.vs")));
		Util.checkGLError("int vs = ShaderLoader.loadShader(GLES20.GL_VERTEX_SHADER, Util.loadFile(Util.getFile(context, \"TexturedShader.vs\")));");
		int fs = ShaderLoader.loadShader(GLES20.GL_FRAGMENT_SHADER, Util.loadFile(Util.getFile(context, "TexturedShader.fs")));
		Util.checkGLError("int fs = ShaderLoader.loadShader(GLES20.GL_FRAGMENT_SHADER, Util.loadFile(Util.getFile(context, \"TexturedShader.fs\")));");
		program = ShaderLoader.makeProgram(vs, fs);
		Util.checkGLError("program = ShaderLoader.makeProgram(vs, fs);");
	}

	@Override
	public void bindLocations()
	{
		super.bindLocations();

		a_uv = GLES20.glGetAttribLocation(program, "a_uv");
		Util.checkGLError("a_uv = GLES20.glGetAttribLocation(program, \"a_uv\");");
		u_tex = GLES20.glGetUniformLocation(program, "u_tex");
		Util.checkGLError("u_tex = GLES20.glGetUniformLocation(program, \"u_tex\");");
	}
}
