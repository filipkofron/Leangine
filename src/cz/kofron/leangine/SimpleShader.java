package cz.kofron.leangine;
import android.opengl.*;
import java.io.*;
import android.content.*;

public class SimpleShader extends AbstractShader
{
	public int a_pos;
	public int a_norm;
	
	@Override
	public void load(Context context) throws IOException, ShaderException
	{
		int vs = ShaderLoader.loadShader(GLES20.GL_VERTEX_SHADER, Util.loadFile(Util.getFile(context, "SimpleShader.vs")));
		Util.checkGLError("int vs = ShaderLoader.loadShader(GLES20.GL_VERTEX_SHADER, Util.loadFile(Util.getFile(context, \"SimpleShader.vs\")));");
		int fs = ShaderLoader.loadShader(GLES20.GL_FRAGMENT_SHADER, Util.loadFile(Util.getFile(context, "SimpleShader.fs")));
		Util.checkGLError("int fs = ShaderLoader.loadShader(GLES20.GL_FRAGMENT_SHADER, Util.loadFile(Util.getFile(context, \"SimpleShader.fs\")));");
		program = ShaderLoader.makeProgram(vs, fs);
		Util.checkGLError("program = ShaderLoader.makeProgram(vs, fs);");
	}
	
	@Override
	public void bindLocations()
	{
		super.bindLocations();
		
		a_pos = GLES20.glGetAttribLocation(program, "a_pos");
		Util.checkGLError("a_pos = GLES20.glGetAttribLocation(program, \"a_pos\");");
		a_norm = GLES20.glGetAttribLocation(program, "a_norm");
		Util.checkGLError("a_norm = GLES20.glGetAttribLocation(program, \"a_norm\");");
	}
}
