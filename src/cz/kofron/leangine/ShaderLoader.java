package cz.kofron.leangine;
import android.opengl.*;
import android.util.*;
import android.content.*;

public class ShaderLoader
{
	public static int loadShader(int type, String source) throws ShaderException
	{
		int res = GLES20.glCreateShader(type);
		GLES20.glShaderSource(res, source);
		GLES20.glCompileShader(res);
		int[] compiled = new int[1];
		GLES20.glGetShaderiv(res, GLES20.GL_COMPILE_STATUS, compiled, 0);
		if (compiled[0] == 0)
		{
			throw new ShaderException("Could not compile shader " + type + ":" + GLES20.glGetShaderInfoLog(res));
		}
		return res;
	}
	
	public static int makeProgram(int vertexShader, int fragmentShader) throws ShaderException
	{
		int program = GLES20.glCreateProgram();

		GLES20.glAttachShader(program, vertexShader);
		GLES20.glAttachShader(program, fragmentShader);

		GLES20.glLinkProgram(program);
		
		int[] linked = new int[1];
		GLES20.glGetShaderiv(program, GLES20.GL_LINK_STATUS, linked, 0);
		if (linked[0] == 0)
		{
			throw new ShaderException("Could not link program:" + GLES20.glGetShaderInfoLog(program));
		}
		
		return program;
	}
}
