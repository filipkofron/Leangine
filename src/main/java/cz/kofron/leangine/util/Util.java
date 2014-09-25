package cz.kofron.leangine.util;
import android.content.*;
import java.io.*;
import android.opengl.*;
import android.util.*;

public class Util
{
	public static InputStream getFile(Context context, String path) throws IOException {
		return context.getAssets().open(path);
	}

	public static String loadFile(InputStream is) {
		String res = "";
		try
		{
			InputStreamReader isr = new InputStreamReader(is);

			int c;
			while((c = isr.read()) != -1)
			{
				res += (char) c;
			}

			isr.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			is.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return res;
	}
	
	public static void checkGLError(String op) {
    	int error;
    	while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR)
		{
            Log.e("MyApp", op + ": glError " + error);
		}
    }
}
