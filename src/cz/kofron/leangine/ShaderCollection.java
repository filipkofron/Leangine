package cz.kofron.leangine;
import android.content.*;
import java.io.*;

public class ShaderCollection
{
	public static AbstractShader simpleShader;
	
	public static void loadShaders(Context context)
	{
		simpleShader = new SimpleShader();
		try
		{
			simpleShader.load(context);
		}
		catch (IOException e)
		{e.printStackTrace(System.err);}
		catch (ShaderException e)
		{e.printStackTrace(System.err);}
	}
}
