package cz.kofron.leangine;
import android.content.*;
import java.io.*;

public class ShaderCollection
{
	public static AbstractShader simpleShader;
	public static AbstractShader simpleColorShader;
	public static AbstractShader texturedShader;
	
	public static void loadShaders(Context context)
	{
		simpleShader = new SimpleShader();
		simpleColorShader = new SimpleColorShader();
		texturedShader = new TexturedShader();
		try
		{
			simpleShader.load(context);
			simpleShader.bindLocations();
			
			simpleColorShader.load(context);
			simpleColorShader.bindLocations();
			
			texturedShader.load(context);
			texturedShader.bindLocations();
		}
		catch (IOException e)
		{e.printStackTrace(System.err);}
		catch (ShaderException e)
		{e.printStackTrace(System.err);}
	}
}
