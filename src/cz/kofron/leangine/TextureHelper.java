package cz.kofron.leangine;
import android.content.*;
import android.opengl.*;
import android.graphics.*;
import java.io.*;

public class TextureHelper
{
	public static Context context;
	
	public static int loadTexture(final String path)
	{
		try
		{
			InputStream is = context.getResources().getAssets().open(path);
			return loadBitmap(BitmapFactory.decodeStream(is));
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static int loadTexture(final int resourceId)
	{
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inScaled = false;   // No pre-scaling

		return loadBitmap(BitmapFactory.decodeResource(context.getResources(), resourceId, options));
	}
	
	private static int loadBitmap(Bitmap bitmap)
	{
		final int[] textureHandle = new int[1];

		GLES20.glGenTextures(1, textureHandle, 0);

		if (textureHandle[0] != 0)
		{

			// Bind to the texture in OpenGL
			GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[0]);

			// Set filtering
			GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
			GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);

			// Load the bitmap into the bound texture.
			GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

			// Recycle the bitmap, since its data has been loaded into OpenGL.
			bitmap.recycle();
		}

		if (textureHandle[0] == 0)
		{
			throw new RuntimeException("Error loading texture.");
		}

		return textureHandle[0];
	}
}
